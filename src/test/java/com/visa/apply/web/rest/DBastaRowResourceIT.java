package com.visa.apply.web.rest;

import static com.visa.apply.domain.DBastaRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DBastaRow;
import com.visa.apply.repository.DBastaRowRepository;
import com.visa.apply.service.dto.DBastaRowDTO;
import com.visa.apply.service.mapper.DBastaRowMapper;
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
 * Integration tests for the {@link DBastaRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DBastaRowResourceIT {

    private static final String DEFAULT_DC_FAMIL = "AAAAAAAAAA";
    private static final String UPDATED_DC_FAMIL = "BBBBBBBBBB";

    private static final String DEFAULT_DC_IMENA = "AAAAAAAAAA";
    private static final String UPDATED_DC_IMENA = "BBBBBBBBBB";

    private static final String DEFAULT_DC_POL = "AAAAAAAAAA";
    private static final String UPDATED_DC_POL = "BBBBBBBBBB";

    private static final String DEFAULT_DC_GRAD = "AAAAAAAAAA";
    private static final String UPDATED_DC_GRAD = "BBBBBBBBBB";

    private static final String DEFAULT_DC_ULICA = "AAAAAAAAAA";
    private static final String UPDATED_DC_ULICA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-basta-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DBastaRowRepository dBastaRowRepository;

    @Autowired
    private DBastaRowMapper dBastaRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDBastaRowMockMvc;

    private DBastaRow dBastaRow;

    private DBastaRow insertedDBastaRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DBastaRow createEntity() {
        return new DBastaRow()
            .dcFamil(DEFAULT_DC_FAMIL)
            .dcImena(DEFAULT_DC_IMENA)
            .dcPol(DEFAULT_DC_POL)
            .dcGrad(DEFAULT_DC_GRAD)
            .dcUlica(DEFAULT_DC_ULICA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DBastaRow createUpdatedEntity() {
        return new DBastaRow()
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);
    }

    @BeforeEach
    void initTest() {
        dBastaRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDBastaRow != null) {
            dBastaRowRepository.delete(insertedDBastaRow);
            insertedDBastaRow = null;
        }
    }

    @Test
    @Transactional
    void createDBastaRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);
        var returnedDBastaRowDTO = om.readValue(
            restDBastaRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DBastaRowDTO.class
        );

        // Validate the DBastaRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDBastaRow = dBastaRowMapper.toEntity(returnedDBastaRowDTO);
        assertDBastaRowUpdatableFieldsEquals(returnedDBastaRow, getPersistedDBastaRow(returnedDBastaRow));

        insertedDBastaRow = returnedDBastaRow;
    }

    @Test
    @Transactional
    void createDBastaRowWithExistingId() throws Exception {
        // Create the DBastaRow with an existing ID
        dBastaRow.setId(1L);
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDcFamilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dBastaRow.setDcFamil(null);

        // Create the DBastaRow, which fails.
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcImenaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dBastaRow.setDcImena(null);

        // Create the DBastaRow, which fails.
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcPolIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dBastaRow.setDcPol(null);

        // Create the DBastaRow, which fails.
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcGradIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dBastaRow.setDcGrad(null);

        // Create the DBastaRow, which fails.
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcUlicaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dBastaRow.setDcUlica(null);

        // Create the DBastaRow, which fails.
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        restDBastaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDBastaRows() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dBastaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dcFamil").value(hasItem(DEFAULT_DC_FAMIL)))
            .andExpect(jsonPath("$.[*].dcImena").value(hasItem(DEFAULT_DC_IMENA)))
            .andExpect(jsonPath("$.[*].dcPol").value(hasItem(DEFAULT_DC_POL)))
            .andExpect(jsonPath("$.[*].dcGrad").value(hasItem(DEFAULT_DC_GRAD)))
            .andExpect(jsonPath("$.[*].dcUlica").value(hasItem(DEFAULT_DC_ULICA)));
    }

    @Test
    @Transactional
    void getDBastaRow() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get the dBastaRow
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dBastaRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dBastaRow.getId().intValue()))
            .andExpect(jsonPath("$.dcFamil").value(DEFAULT_DC_FAMIL))
            .andExpect(jsonPath("$.dcImena").value(DEFAULT_DC_IMENA))
            .andExpect(jsonPath("$.dcPol").value(DEFAULT_DC_POL))
            .andExpect(jsonPath("$.dcGrad").value(DEFAULT_DC_GRAD))
            .andExpect(jsonPath("$.dcUlica").value(DEFAULT_DC_ULICA));
    }

    @Test
    @Transactional
    void getDBastaRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        Long id = dBastaRow.getId();

        defaultDBastaRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDBastaRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDBastaRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcFamilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcFamil equals to
        defaultDBastaRowFiltering("dcFamil.equals=" + DEFAULT_DC_FAMIL, "dcFamil.equals=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcFamilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcFamil in
        defaultDBastaRowFiltering("dcFamil.in=" + DEFAULT_DC_FAMIL + "," + UPDATED_DC_FAMIL, "dcFamil.in=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcFamilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcFamil is not null
        defaultDBastaRowFiltering("dcFamil.specified=true", "dcFamil.specified=false");
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcFamilContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcFamil contains
        defaultDBastaRowFiltering("dcFamil.contains=" + DEFAULT_DC_FAMIL, "dcFamil.contains=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcFamilNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcFamil does not contain
        defaultDBastaRowFiltering("dcFamil.doesNotContain=" + UPDATED_DC_FAMIL, "dcFamil.doesNotContain=" + DEFAULT_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcImenaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcImena equals to
        defaultDBastaRowFiltering("dcImena.equals=" + DEFAULT_DC_IMENA, "dcImena.equals=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcImenaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcImena in
        defaultDBastaRowFiltering("dcImena.in=" + DEFAULT_DC_IMENA + "," + UPDATED_DC_IMENA, "dcImena.in=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcImenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcImena is not null
        defaultDBastaRowFiltering("dcImena.specified=true", "dcImena.specified=false");
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcImenaContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcImena contains
        defaultDBastaRowFiltering("dcImena.contains=" + DEFAULT_DC_IMENA, "dcImena.contains=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcImenaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcImena does not contain
        defaultDBastaRowFiltering("dcImena.doesNotContain=" + UPDATED_DC_IMENA, "dcImena.doesNotContain=" + DEFAULT_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcPolIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcPol equals to
        defaultDBastaRowFiltering("dcPol.equals=" + DEFAULT_DC_POL, "dcPol.equals=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcPolIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcPol in
        defaultDBastaRowFiltering("dcPol.in=" + DEFAULT_DC_POL + "," + UPDATED_DC_POL, "dcPol.in=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcPolIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcPol is not null
        defaultDBastaRowFiltering("dcPol.specified=true", "dcPol.specified=false");
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcPolContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcPol contains
        defaultDBastaRowFiltering("dcPol.contains=" + DEFAULT_DC_POL, "dcPol.contains=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcPolNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcPol does not contain
        defaultDBastaRowFiltering("dcPol.doesNotContain=" + UPDATED_DC_POL, "dcPol.doesNotContain=" + DEFAULT_DC_POL);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcGradIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcGrad equals to
        defaultDBastaRowFiltering("dcGrad.equals=" + DEFAULT_DC_GRAD, "dcGrad.equals=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcGradIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcGrad in
        defaultDBastaRowFiltering("dcGrad.in=" + DEFAULT_DC_GRAD + "," + UPDATED_DC_GRAD, "dcGrad.in=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcGradIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcGrad is not null
        defaultDBastaRowFiltering("dcGrad.specified=true", "dcGrad.specified=false");
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcGradContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcGrad contains
        defaultDBastaRowFiltering("dcGrad.contains=" + DEFAULT_DC_GRAD, "dcGrad.contains=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcGradNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcGrad does not contain
        defaultDBastaRowFiltering("dcGrad.doesNotContain=" + UPDATED_DC_GRAD, "dcGrad.doesNotContain=" + DEFAULT_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcUlicaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcUlica equals to
        defaultDBastaRowFiltering("dcUlica.equals=" + DEFAULT_DC_ULICA, "dcUlica.equals=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcUlicaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcUlica in
        defaultDBastaRowFiltering("dcUlica.in=" + DEFAULT_DC_ULICA + "," + UPDATED_DC_ULICA, "dcUlica.in=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcUlicaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcUlica is not null
        defaultDBastaRowFiltering("dcUlica.specified=true", "dcUlica.specified=false");
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcUlicaContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcUlica contains
        defaultDBastaRowFiltering("dcUlica.contains=" + DEFAULT_DC_ULICA, "dcUlica.contains=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDBastaRowsByDcUlicaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        // Get all the dBastaRowList where dcUlica does not contain
        defaultDBastaRowFiltering("dcUlica.doesNotContain=" + UPDATED_DC_ULICA, "dcUlica.doesNotContain=" + DEFAULT_DC_ULICA);
    }

    private void defaultDBastaRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDBastaRowShouldBeFound(shouldBeFound);
        defaultDBastaRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDBastaRowShouldBeFound(String filter) throws Exception {
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dBastaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dcFamil").value(hasItem(DEFAULT_DC_FAMIL)))
            .andExpect(jsonPath("$.[*].dcImena").value(hasItem(DEFAULT_DC_IMENA)))
            .andExpect(jsonPath("$.[*].dcPol").value(hasItem(DEFAULT_DC_POL)))
            .andExpect(jsonPath("$.[*].dcGrad").value(hasItem(DEFAULT_DC_GRAD)))
            .andExpect(jsonPath("$.[*].dcUlica").value(hasItem(DEFAULT_DC_ULICA)));

        // Check, that the count call also returns 1
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDBastaRowShouldNotBeFound(String filter) throws Exception {
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDBastaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDBastaRow() throws Exception {
        // Get the dBastaRow
        restDBastaRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDBastaRow() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dBastaRow
        DBastaRow updatedDBastaRow = dBastaRowRepository.findById(dBastaRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDBastaRow are not directly saved in db
        em.detach(updatedDBastaRow);
        updatedDBastaRow
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(updatedDBastaRow);

        restDBastaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dBastaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dBastaRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDBastaRowToMatchAllProperties(updatedDBastaRow);
    }

    @Test
    @Transactional
    void putNonExistingDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dBastaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dBastaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dBastaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDBastaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dBastaRow using partial update
        DBastaRow partialUpdatedDBastaRow = new DBastaRow();
        partialUpdatedDBastaRow.setId(dBastaRow.getId());

        partialUpdatedDBastaRow.dcFamil(UPDATED_DC_FAMIL).dcPol(UPDATED_DC_POL);

        restDBastaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDBastaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDBastaRow))
            )
            .andExpect(status().isOk());

        // Validate the DBastaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDBastaRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDBastaRow, dBastaRow),
            getPersistedDBastaRow(dBastaRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDBastaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dBastaRow using partial update
        DBastaRow partialUpdatedDBastaRow = new DBastaRow();
        partialUpdatedDBastaRow.setId(dBastaRow.getId());

        partialUpdatedDBastaRow
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);

        restDBastaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDBastaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDBastaRow))
            )
            .andExpect(status().isOk());

        // Validate the DBastaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDBastaRowUpdatableFieldsEquals(partialUpdatedDBastaRow, getPersistedDBastaRow(partialUpdatedDBastaRow));
    }

    @Test
    @Transactional
    void patchNonExistingDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dBastaRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dBastaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dBastaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDBastaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dBastaRow.setId(longCount.incrementAndGet());

        // Create the DBastaRow
        DBastaRowDTO dBastaRowDTO = dBastaRowMapper.toDto(dBastaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDBastaRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dBastaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DBastaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDBastaRow() throws Exception {
        // Initialize the database
        insertedDBastaRow = dBastaRowRepository.saveAndFlush(dBastaRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dBastaRow
        restDBastaRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dBastaRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dBastaRowRepository.count();
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

    protected DBastaRow getPersistedDBastaRow(DBastaRow dBastaRow) {
        return dBastaRowRepository.findById(dBastaRow.getId()).orElseThrow();
    }

    protected void assertPersistedDBastaRowToMatchAllProperties(DBastaRow expectedDBastaRow) {
        assertDBastaRowAllPropertiesEquals(expectedDBastaRow, getPersistedDBastaRow(expectedDBastaRow));
    }

    protected void assertPersistedDBastaRowToMatchUpdatableProperties(DBastaRow expectedDBastaRow) {
        assertDBastaRowAllUpdatablePropertiesEquals(expectedDBastaRow, getPersistedDBastaRow(expectedDBastaRow));
    }
}
