package com.visa.apply.web.rest;

import static com.visa.apply.domain.DEurodaRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DEurodaRow;
import com.visa.apply.repository.DEurodaRowRepository;
import com.visa.apply.service.dto.DEurodaRowDTO;
import com.visa.apply.service.mapper.DEurodaRowMapper;
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
 * Integration tests for the {@link DEurodaRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DEurodaRowResourceIT {

    private static final String DEFAULT_EU_FAMIL = "AAAAAAAAAA";
    private static final String UPDATED_EU_FAMIL = "BBBBBBBBBB";

    private static final String DEFAULT_EU_IMENA = "AAAAAAAAAA";
    private static final String UPDATED_EU_IMENA = "BBBBBBBBBB";

    private static final String DEFAULT_EU_NAC_BEL = "AAAAAAAAAA";
    private static final String UPDATED_EU_NAC_BEL = "BBBBBBBBBB";

    private static final String DEFAULT_EU_RODSTVO = "AAAAAAAAAA";
    private static final String UPDATED_EU_RODSTVO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-euroda-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DEurodaRowRepository dEurodaRowRepository;

    @Autowired
    private DEurodaRowMapper dEurodaRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDEurodaRowMockMvc;

    private DEurodaRow dEurodaRow;

    private DEurodaRow insertedDEurodaRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DEurodaRow createEntity() {
        return new DEurodaRow()
            .euFamil(DEFAULT_EU_FAMIL)
            .euImena(DEFAULT_EU_IMENA)
            .euNacBel(DEFAULT_EU_NAC_BEL)
            .euRodstvo(DEFAULT_EU_RODSTVO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DEurodaRow createUpdatedEntity() {
        return new DEurodaRow()
            .euFamil(UPDATED_EU_FAMIL)
            .euImena(UPDATED_EU_IMENA)
            .euNacBel(UPDATED_EU_NAC_BEL)
            .euRodstvo(UPDATED_EU_RODSTVO);
    }

    @BeforeEach
    void initTest() {
        dEurodaRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDEurodaRow != null) {
            dEurodaRowRepository.delete(insertedDEurodaRow);
            insertedDEurodaRow = null;
        }
    }

    @Test
    @Transactional
    void createDEurodaRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);
        var returnedDEurodaRowDTO = om.readValue(
            restDEurodaRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DEurodaRowDTO.class
        );

        // Validate the DEurodaRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDEurodaRow = dEurodaRowMapper.toEntity(returnedDEurodaRowDTO);
        assertDEurodaRowUpdatableFieldsEquals(returnedDEurodaRow, getPersistedDEurodaRow(returnedDEurodaRow));

        insertedDEurodaRow = returnedDEurodaRow;
    }

    @Test
    @Transactional
    void createDEurodaRowWithExistingId() throws Exception {
        // Create the DEurodaRow with an existing ID
        dEurodaRow.setId(1L);
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDEurodaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEuFamilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dEurodaRow.setEuFamil(null);

        // Create the DEurodaRow, which fails.
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        restDEurodaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEuImenaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dEurodaRow.setEuImena(null);

        // Create the DEurodaRow, which fails.
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        restDEurodaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEuNacBelIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dEurodaRow.setEuNacBel(null);

        // Create the DEurodaRow, which fails.
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        restDEurodaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEuRodstvoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dEurodaRow.setEuRodstvo(null);

        // Create the DEurodaRow, which fails.
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        restDEurodaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDEurodaRows() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dEurodaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].euFamil").value(hasItem(DEFAULT_EU_FAMIL)))
            .andExpect(jsonPath("$.[*].euImena").value(hasItem(DEFAULT_EU_IMENA)))
            .andExpect(jsonPath("$.[*].euNacBel").value(hasItem(DEFAULT_EU_NAC_BEL)))
            .andExpect(jsonPath("$.[*].euRodstvo").value(hasItem(DEFAULT_EU_RODSTVO)));
    }

    @Test
    @Transactional
    void getDEurodaRow() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get the dEurodaRow
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dEurodaRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dEurodaRow.getId().intValue()))
            .andExpect(jsonPath("$.euFamil").value(DEFAULT_EU_FAMIL))
            .andExpect(jsonPath("$.euImena").value(DEFAULT_EU_IMENA))
            .andExpect(jsonPath("$.euNacBel").value(DEFAULT_EU_NAC_BEL))
            .andExpect(jsonPath("$.euRodstvo").value(DEFAULT_EU_RODSTVO));
    }

    @Test
    @Transactional
    void getDEurodaRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        Long id = dEurodaRow.getId();

        defaultDEurodaRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDEurodaRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDEurodaRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuFamilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euFamil equals to
        defaultDEurodaRowFiltering("euFamil.equals=" + DEFAULT_EU_FAMIL, "euFamil.equals=" + UPDATED_EU_FAMIL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuFamilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euFamil in
        defaultDEurodaRowFiltering("euFamil.in=" + DEFAULT_EU_FAMIL + "," + UPDATED_EU_FAMIL, "euFamil.in=" + UPDATED_EU_FAMIL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuFamilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euFamil is not null
        defaultDEurodaRowFiltering("euFamil.specified=true", "euFamil.specified=false");
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuFamilContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euFamil contains
        defaultDEurodaRowFiltering("euFamil.contains=" + DEFAULT_EU_FAMIL, "euFamil.contains=" + UPDATED_EU_FAMIL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuFamilNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euFamil does not contain
        defaultDEurodaRowFiltering("euFamil.doesNotContain=" + UPDATED_EU_FAMIL, "euFamil.doesNotContain=" + DEFAULT_EU_FAMIL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuImenaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euImena equals to
        defaultDEurodaRowFiltering("euImena.equals=" + DEFAULT_EU_IMENA, "euImena.equals=" + UPDATED_EU_IMENA);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuImenaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euImena in
        defaultDEurodaRowFiltering("euImena.in=" + DEFAULT_EU_IMENA + "," + UPDATED_EU_IMENA, "euImena.in=" + UPDATED_EU_IMENA);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuImenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euImena is not null
        defaultDEurodaRowFiltering("euImena.specified=true", "euImena.specified=false");
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuImenaContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euImena contains
        defaultDEurodaRowFiltering("euImena.contains=" + DEFAULT_EU_IMENA, "euImena.contains=" + UPDATED_EU_IMENA);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuImenaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euImena does not contain
        defaultDEurodaRowFiltering("euImena.doesNotContain=" + UPDATED_EU_IMENA, "euImena.doesNotContain=" + DEFAULT_EU_IMENA);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuNacBelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euNacBel equals to
        defaultDEurodaRowFiltering("euNacBel.equals=" + DEFAULT_EU_NAC_BEL, "euNacBel.equals=" + UPDATED_EU_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuNacBelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euNacBel in
        defaultDEurodaRowFiltering("euNacBel.in=" + DEFAULT_EU_NAC_BEL + "," + UPDATED_EU_NAC_BEL, "euNacBel.in=" + UPDATED_EU_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuNacBelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euNacBel is not null
        defaultDEurodaRowFiltering("euNacBel.specified=true", "euNacBel.specified=false");
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuNacBelContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euNacBel contains
        defaultDEurodaRowFiltering("euNacBel.contains=" + DEFAULT_EU_NAC_BEL, "euNacBel.contains=" + UPDATED_EU_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuNacBelNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euNacBel does not contain
        defaultDEurodaRowFiltering("euNacBel.doesNotContain=" + UPDATED_EU_NAC_BEL, "euNacBel.doesNotContain=" + DEFAULT_EU_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuRodstvoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euRodstvo equals to
        defaultDEurodaRowFiltering("euRodstvo.equals=" + DEFAULT_EU_RODSTVO, "euRodstvo.equals=" + UPDATED_EU_RODSTVO);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuRodstvoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euRodstvo in
        defaultDEurodaRowFiltering("euRodstvo.in=" + DEFAULT_EU_RODSTVO + "," + UPDATED_EU_RODSTVO, "euRodstvo.in=" + UPDATED_EU_RODSTVO);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuRodstvoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euRodstvo is not null
        defaultDEurodaRowFiltering("euRodstvo.specified=true", "euRodstvo.specified=false");
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuRodstvoContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euRodstvo contains
        defaultDEurodaRowFiltering("euRodstvo.contains=" + DEFAULT_EU_RODSTVO, "euRodstvo.contains=" + UPDATED_EU_RODSTVO);
    }

    @Test
    @Transactional
    void getAllDEurodaRowsByEuRodstvoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        // Get all the dEurodaRowList where euRodstvo does not contain
        defaultDEurodaRowFiltering("euRodstvo.doesNotContain=" + UPDATED_EU_RODSTVO, "euRodstvo.doesNotContain=" + DEFAULT_EU_RODSTVO);
    }

    private void defaultDEurodaRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDEurodaRowShouldBeFound(shouldBeFound);
        defaultDEurodaRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDEurodaRowShouldBeFound(String filter) throws Exception {
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dEurodaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].euFamil").value(hasItem(DEFAULT_EU_FAMIL)))
            .andExpect(jsonPath("$.[*].euImena").value(hasItem(DEFAULT_EU_IMENA)))
            .andExpect(jsonPath("$.[*].euNacBel").value(hasItem(DEFAULT_EU_NAC_BEL)))
            .andExpect(jsonPath("$.[*].euRodstvo").value(hasItem(DEFAULT_EU_RODSTVO)));

        // Check, that the count call also returns 1
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDEurodaRowShouldNotBeFound(String filter) throws Exception {
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDEurodaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDEurodaRow() throws Exception {
        // Get the dEurodaRow
        restDEurodaRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDEurodaRow() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dEurodaRow
        DEurodaRow updatedDEurodaRow = dEurodaRowRepository.findById(dEurodaRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDEurodaRow are not directly saved in db
        em.detach(updatedDEurodaRow);
        updatedDEurodaRow.euFamil(UPDATED_EU_FAMIL).euImena(UPDATED_EU_IMENA).euNacBel(UPDATED_EU_NAC_BEL).euRodstvo(UPDATED_EU_RODSTVO);
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(updatedDEurodaRow);

        restDEurodaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dEurodaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dEurodaRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDEurodaRowToMatchAllProperties(updatedDEurodaRow);
    }

    @Test
    @Transactional
    void putNonExistingDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dEurodaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dEurodaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dEurodaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDEurodaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dEurodaRow using partial update
        DEurodaRow partialUpdatedDEurodaRow = new DEurodaRow();
        partialUpdatedDEurodaRow.setId(dEurodaRow.getId());

        partialUpdatedDEurodaRow.euImena(UPDATED_EU_IMENA);

        restDEurodaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDEurodaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDEurodaRow))
            )
            .andExpect(status().isOk());

        // Validate the DEurodaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDEurodaRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDEurodaRow, dEurodaRow),
            getPersistedDEurodaRow(dEurodaRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDEurodaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dEurodaRow using partial update
        DEurodaRow partialUpdatedDEurodaRow = new DEurodaRow();
        partialUpdatedDEurodaRow.setId(dEurodaRow.getId());

        partialUpdatedDEurodaRow
            .euFamil(UPDATED_EU_FAMIL)
            .euImena(UPDATED_EU_IMENA)
            .euNacBel(UPDATED_EU_NAC_BEL)
            .euRodstvo(UPDATED_EU_RODSTVO);

        restDEurodaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDEurodaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDEurodaRow))
            )
            .andExpect(status().isOk());

        // Validate the DEurodaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDEurodaRowUpdatableFieldsEquals(partialUpdatedDEurodaRow, getPersistedDEurodaRow(partialUpdatedDEurodaRow));
    }

    @Test
    @Transactional
    void patchNonExistingDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dEurodaRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dEurodaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dEurodaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDEurodaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dEurodaRow.setId(longCount.incrementAndGet());

        // Create the DEurodaRow
        DEurodaRowDTO dEurodaRowDTO = dEurodaRowMapper.toDto(dEurodaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDEurodaRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dEurodaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DEurodaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDEurodaRow() throws Exception {
        // Initialize the database
        insertedDEurodaRow = dEurodaRowRepository.saveAndFlush(dEurodaRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dEurodaRow
        restDEurodaRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dEurodaRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dEurodaRowRepository.count();
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

    protected DEurodaRow getPersistedDEurodaRow(DEurodaRow dEurodaRow) {
        return dEurodaRowRepository.findById(dEurodaRow.getId()).orElseThrow();
    }

    protected void assertPersistedDEurodaRowToMatchAllProperties(DEurodaRow expectedDEurodaRow) {
        assertDEurodaRowAllPropertiesEquals(expectedDEurodaRow, getPersistedDEurodaRow(expectedDEurodaRow));
    }

    protected void assertPersistedDEurodaRowToMatchUpdatableProperties(DEurodaRow expectedDEurodaRow) {
        assertDEurodaRowAllUpdatablePropertiesEquals(expectedDEurodaRow, getPersistedDEurodaRow(expectedDEurodaRow));
    }
}
