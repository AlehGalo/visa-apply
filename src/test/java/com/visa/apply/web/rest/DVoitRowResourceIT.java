package com.visa.apply.web.rest;

import static com.visa.apply.domain.DVoitRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DVoitRow;
import com.visa.apply.repository.DVoitRowRepository;
import com.visa.apply.service.dto.DVoitRowDTO;
import com.visa.apply.service.mapper.DVoitRowMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DVoitRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DVoitRowResourceIT {

    private static final String DEFAULT_VNOM = "AAAAAAAAAA";
    private static final String UPDATED_VNOM = "BBBBBBBBBB";

    private static final String DEFAULT_VIME = "AAAAAAAAAA";
    private static final String UPDATED_VIME = "BBBBBBBBBB";

    private static final String DEFAULT_BGIME = "AAAAAAAAAA";
    private static final String UPDATED_BGIME = "BBBBBBBBBB";

    private static final String DEFAULT_BGADRES = "AAAAAAAAAA";
    private static final String UPDATED_BGADRES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-voit-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DVoitRowRepository dVoitRowRepository;

    @Autowired
    private DVoitRowMapper dVoitRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDVoitRowMockMvc;

    private DVoitRow dVoitRow;

    private DVoitRow insertedDVoitRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DVoitRow createEntity() {
        return new DVoitRow().vnom(DEFAULT_VNOM).vime(DEFAULT_VIME).bgime(DEFAULT_BGIME).bgadres(DEFAULT_BGADRES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DVoitRow createUpdatedEntity() {
        return new DVoitRow().vnom(UPDATED_VNOM).vime(UPDATED_VIME).bgime(UPDATED_BGIME).bgadres(UPDATED_BGADRES);
    }

    @BeforeEach
    void initTest() {
        dVoitRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDVoitRow != null) {
            dVoitRowRepository.delete(insertedDVoitRow);
            insertedDVoitRow = null;
        }
    }

    @Test
    @Transactional
    void createDVoitRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);
        var returnedDVoitRowDTO = om.readValue(
            restDVoitRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DVoitRowDTO.class
        );

        // Validate the DVoitRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDVoitRow = dVoitRowMapper.toEntity(returnedDVoitRowDTO);
        assertDVoitRowUpdatableFieldsEquals(returnedDVoitRow, getPersistedDVoitRow(returnedDVoitRow));

        insertedDVoitRow = returnedDVoitRow;
    }

    @Test
    @Transactional
    void createDVoitRowWithExistingId() throws Exception {
        // Create the DVoitRow with an existing ID
        dVoitRow.setId(1L);
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDVoitRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVnomIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dVoitRow.setVnom(null);

        // Create the DVoitRow, which fails.
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        restDVoitRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVimeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dVoitRow.setVime(null);

        // Create the DVoitRow, which fails.
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        restDVoitRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBgimeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dVoitRow.setBgime(null);

        // Create the DVoitRow, which fails.
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        restDVoitRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBgadresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dVoitRow.setBgadres(null);

        // Create the DVoitRow, which fails.
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        restDVoitRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDVoitRows() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dVoitRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].vnom").value(hasItem(DEFAULT_VNOM)))
            .andExpect(jsonPath("$.[*].vime").value(hasItem(DEFAULT_VIME)))
            .andExpect(jsonPath("$.[*].bgime").value(hasItem(DEFAULT_BGIME)))
            .andExpect(jsonPath("$.[*].bgadres").value(hasItem(DEFAULT_BGADRES)));
    }

    @Test
    @Transactional
    void getDVoitRow() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get the dVoitRow
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dVoitRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dVoitRow.getId().intValue()))
            .andExpect(jsonPath("$.vnom").value(DEFAULT_VNOM))
            .andExpect(jsonPath("$.vime").value(DEFAULT_VIME))
            .andExpect(jsonPath("$.bgime").value(DEFAULT_BGIME))
            .andExpect(jsonPath("$.bgadres").value(DEFAULT_BGADRES));
    }

    @Test
    @Transactional
    void getDVoitRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        Long id = dVoitRow.getId();

        defaultDVoitRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDVoitRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDVoitRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVnomIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vnom equals to
        defaultDVoitRowFiltering("vnom.equals=" + DEFAULT_VNOM, "vnom.equals=" + UPDATED_VNOM);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVnomIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vnom in
        defaultDVoitRowFiltering("vnom.in=" + DEFAULT_VNOM + "," + UPDATED_VNOM, "vnom.in=" + UPDATED_VNOM);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVnomIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vnom is not null
        defaultDVoitRowFiltering("vnom.specified=true", "vnom.specified=false");
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVnomContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vnom contains
        defaultDVoitRowFiltering("vnom.contains=" + DEFAULT_VNOM, "vnom.contains=" + UPDATED_VNOM);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVnomNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vnom does not contain
        defaultDVoitRowFiltering("vnom.doesNotContain=" + UPDATED_VNOM, "vnom.doesNotContain=" + DEFAULT_VNOM);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVimeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vime equals to
        defaultDVoitRowFiltering("vime.equals=" + DEFAULT_VIME, "vime.equals=" + UPDATED_VIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVimeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vime in
        defaultDVoitRowFiltering("vime.in=" + DEFAULT_VIME + "," + UPDATED_VIME, "vime.in=" + UPDATED_VIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vime is not null
        defaultDVoitRowFiltering("vime.specified=true", "vime.specified=false");
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVimeContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vime contains
        defaultDVoitRowFiltering("vime.contains=" + DEFAULT_VIME, "vime.contains=" + UPDATED_VIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByVimeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where vime does not contain
        defaultDVoitRowFiltering("vime.doesNotContain=" + UPDATED_VIME, "vime.doesNotContain=" + DEFAULT_VIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgimeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgime equals to
        defaultDVoitRowFiltering("bgime.equals=" + DEFAULT_BGIME, "bgime.equals=" + UPDATED_BGIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgimeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgime in
        defaultDVoitRowFiltering("bgime.in=" + DEFAULT_BGIME + "," + UPDATED_BGIME, "bgime.in=" + UPDATED_BGIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgime is not null
        defaultDVoitRowFiltering("bgime.specified=true", "bgime.specified=false");
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgimeContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgime contains
        defaultDVoitRowFiltering("bgime.contains=" + DEFAULT_BGIME, "bgime.contains=" + UPDATED_BGIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgimeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgime does not contain
        defaultDVoitRowFiltering("bgime.doesNotContain=" + UPDATED_BGIME, "bgime.doesNotContain=" + DEFAULT_BGIME);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgadresIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgadres equals to
        defaultDVoitRowFiltering("bgadres.equals=" + DEFAULT_BGADRES, "bgadres.equals=" + UPDATED_BGADRES);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgadresIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgadres in
        defaultDVoitRowFiltering("bgadres.in=" + DEFAULT_BGADRES + "," + UPDATED_BGADRES, "bgadres.in=" + UPDATED_BGADRES);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgadresIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgadres is not null
        defaultDVoitRowFiltering("bgadres.specified=true", "bgadres.specified=false");
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgadresContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgadres contains
        defaultDVoitRowFiltering("bgadres.contains=" + DEFAULT_BGADRES, "bgadres.contains=" + UPDATED_BGADRES);
    }

    @Test
    @Transactional
    void getAllDVoitRowsByBgadresNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        // Get all the dVoitRowList where bgadres does not contain
        defaultDVoitRowFiltering("bgadres.doesNotContain=" + UPDATED_BGADRES, "bgadres.doesNotContain=" + DEFAULT_BGADRES);
    }

    private void defaultDVoitRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDVoitRowShouldBeFound(shouldBeFound);
        defaultDVoitRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDVoitRowShouldBeFound(String filter) throws Exception {
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dVoitRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].vnom").value(hasItem(DEFAULT_VNOM)))
            .andExpect(jsonPath("$.[*].vime").value(hasItem(DEFAULT_VIME)))
            .andExpect(jsonPath("$.[*].bgime").value(hasItem(DEFAULT_BGIME)))
            .andExpect(jsonPath("$.[*].bgadres").value(hasItem(DEFAULT_BGADRES)));

        // Check, that the count call also returns 1
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDVoitRowShouldNotBeFound(String filter) throws Exception {
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDVoitRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDVoitRow() throws Exception {
        // Get the dVoitRow
        restDVoitRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDVoitRow() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dVoitRow
        DVoitRow updatedDVoitRow = dVoitRowRepository.findById(dVoitRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDVoitRow are not directly saved in db
        em.detach(updatedDVoitRow);
        updatedDVoitRow.vnom(UPDATED_VNOM).vime(UPDATED_VIME).bgime(UPDATED_BGIME).bgadres(UPDATED_BGADRES);
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(updatedDVoitRow);

        restDVoitRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dVoitRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dVoitRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDVoitRowToMatchAllProperties(updatedDVoitRow);
    }

    @Test
    @Transactional
    void putNonExistingDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dVoitRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dVoitRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dVoitRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDVoitRowWithPatch() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dVoitRow using partial update
        DVoitRow partialUpdatedDVoitRow = new DVoitRow();
        partialUpdatedDVoitRow.setId(dVoitRow.getId());

        partialUpdatedDVoitRow.vnom(UPDATED_VNOM).vime(UPDATED_VIME).bgime(UPDATED_BGIME);

        restDVoitRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDVoitRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDVoitRow))
            )
            .andExpect(status().isOk());

        // Validate the DVoitRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDVoitRowUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDVoitRow, dVoitRow), getPersistedDVoitRow(dVoitRow));
    }

    @Test
    @Transactional
    void fullUpdateDVoitRowWithPatch() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dVoitRow using partial update
        DVoitRow partialUpdatedDVoitRow = new DVoitRow();
        partialUpdatedDVoitRow.setId(dVoitRow.getId());

        partialUpdatedDVoitRow.vnom(UPDATED_VNOM).vime(UPDATED_VIME).bgime(UPDATED_BGIME).bgadres(UPDATED_BGADRES);

        restDVoitRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDVoitRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDVoitRow))
            )
            .andExpect(status().isOk());

        // Validate the DVoitRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDVoitRowUpdatableFieldsEquals(partialUpdatedDVoitRow, getPersistedDVoitRow(partialUpdatedDVoitRow));
    }

    @Test
    @Transactional
    void patchNonExistingDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dVoitRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dVoitRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dVoitRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDVoitRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dVoitRow.setId(longCount.incrementAndGet());

        // Create the DVoitRow
        DVoitRowDTO dVoitRowDTO = dVoitRowMapper.toDto(dVoitRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDVoitRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dVoitRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DVoitRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDVoitRow() throws Exception {
        // Initialize the database
        insertedDVoitRow = dVoitRowRepository.saveAndFlush(dVoitRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dVoitRow
        restDVoitRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dVoitRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dVoitRowRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected DVoitRow getPersistedDVoitRow(DVoitRow dVoitRow) {
        return dVoitRowRepository.findById(dVoitRow.getId()).orElseThrow();
    }

    protected void assertPersistedDVoitRowToMatchAllProperties(DVoitRow expectedDVoitRow) {
        assertDVoitRowAllPropertiesEquals(expectedDVoitRow, getPersistedDVoitRow(expectedDVoitRow));
    }

    protected void assertPersistedDVoitRowToMatchUpdatableProperties(DVoitRow expectedDVoitRow) {
        assertDVoitRowAllUpdatablePropertiesEquals(expectedDVoitRow, getPersistedDVoitRow(expectedDVoitRow));
    }
}
