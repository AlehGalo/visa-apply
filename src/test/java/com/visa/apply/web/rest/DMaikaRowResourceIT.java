package com.visa.apply.web.rest;

import static com.visa.apply.domain.DMaikaRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.repository.DMaikaRowRepository;
import com.visa.apply.service.dto.DMaikaRowDTO;
import com.visa.apply.service.mapper.DMaikaRowMapper;
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
 * Integration tests for the {@link DMaikaRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DMaikaRowResourceIT {

    private static final String DEFAULT_DC_FAMIL = "AAAAAAAAAA";
    private static final String UPDATED_DC_FAMIL = "BBBBBBBBBB";

    private static final String DEFAULT_DC_IMENA = "AAAAAAAAAA";
    private static final String UPDATED_DC_IMENA = "BBBBBBBBBB";

    private static final String DEFAULT_DC_POL = "AAAAAAAAAA";
    private static final String UPDATED_DC_POL = "BBBBBBBBBB";

    private static final String DEFAULT_DC_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_DC_DARJ = "BBBBBBBBBB";

    private static final String DEFAULT_DC_GRAD = "AAAAAAAAAA";
    private static final String UPDATED_DC_GRAD = "BBBBBBBBBB";

    private static final String DEFAULT_DC_ULICA = "AAAAAAAAAA";
    private static final String UPDATED_DC_ULICA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-maika-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DMaikaRowRepository dMaikaRowRepository;

    @Autowired
    private DMaikaRowMapper dMaikaRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDMaikaRowMockMvc;

    private DMaikaRow dMaikaRow;

    private DMaikaRow insertedDMaikaRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DMaikaRow createEntity() {
        return new DMaikaRow()
            .dcFamil(DEFAULT_DC_FAMIL)
            .dcImena(DEFAULT_DC_IMENA)
            .dcPol(DEFAULT_DC_POL)
            .dcDarj(DEFAULT_DC_DARJ)
            .dcGrad(DEFAULT_DC_GRAD)
            .dcUlica(DEFAULT_DC_ULICA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DMaikaRow createUpdatedEntity() {
        return new DMaikaRow()
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcDarj(UPDATED_DC_DARJ)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);
    }

    @BeforeEach
    void initTest() {
        dMaikaRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDMaikaRow != null) {
            dMaikaRowRepository.delete(insertedDMaikaRow);
            insertedDMaikaRow = null;
        }
    }

    @Test
    @Transactional
    void createDMaikaRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);
        var returnedDMaikaRowDTO = om.readValue(
            restDMaikaRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DMaikaRowDTO.class
        );

        // Validate the DMaikaRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDMaikaRow = dMaikaRowMapper.toEntity(returnedDMaikaRowDTO);
        assertDMaikaRowUpdatableFieldsEquals(returnedDMaikaRow, getPersistedDMaikaRow(returnedDMaikaRow));

        insertedDMaikaRow = returnedDMaikaRow;
    }

    @Test
    @Transactional
    void createDMaikaRowWithExistingId() throws Exception {
        // Create the DMaikaRow with an existing ID
        dMaikaRow.setId(1L);
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDcFamilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcFamil(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcImenaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcImena(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcPolIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcPol(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcDarj(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcGradIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcGrad(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDcUlicaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMaikaRow.setDcUlica(null);

        // Create the DMaikaRow, which fails.
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        restDMaikaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDMaikaRows() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dMaikaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dcFamil").value(hasItem(DEFAULT_DC_FAMIL)))
            .andExpect(jsonPath("$.[*].dcImena").value(hasItem(DEFAULT_DC_IMENA)))
            .andExpect(jsonPath("$.[*].dcPol").value(hasItem(DEFAULT_DC_POL)))
            .andExpect(jsonPath("$.[*].dcDarj").value(hasItem(DEFAULT_DC_DARJ)))
            .andExpect(jsonPath("$.[*].dcGrad").value(hasItem(DEFAULT_DC_GRAD)))
            .andExpect(jsonPath("$.[*].dcUlica").value(hasItem(DEFAULT_DC_ULICA)));
    }

    @Test
    @Transactional
    void getDMaikaRow() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get the dMaikaRow
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dMaikaRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dMaikaRow.getId().intValue()))
            .andExpect(jsonPath("$.dcFamil").value(DEFAULT_DC_FAMIL))
            .andExpect(jsonPath("$.dcImena").value(DEFAULT_DC_IMENA))
            .andExpect(jsonPath("$.dcPol").value(DEFAULT_DC_POL))
            .andExpect(jsonPath("$.dcDarj").value(DEFAULT_DC_DARJ))
            .andExpect(jsonPath("$.dcGrad").value(DEFAULT_DC_GRAD))
            .andExpect(jsonPath("$.dcUlica").value(DEFAULT_DC_ULICA));
    }

    @Test
    @Transactional
    void getDMaikaRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        Long id = dMaikaRow.getId();

        defaultDMaikaRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDMaikaRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDMaikaRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcFamilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcFamil equals to
        defaultDMaikaRowFiltering("dcFamil.equals=" + DEFAULT_DC_FAMIL, "dcFamil.equals=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcFamilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcFamil in
        defaultDMaikaRowFiltering("dcFamil.in=" + DEFAULT_DC_FAMIL + "," + UPDATED_DC_FAMIL, "dcFamil.in=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcFamilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcFamil is not null
        defaultDMaikaRowFiltering("dcFamil.specified=true", "dcFamil.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcFamilContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcFamil contains
        defaultDMaikaRowFiltering("dcFamil.contains=" + DEFAULT_DC_FAMIL, "dcFamil.contains=" + UPDATED_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcFamilNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcFamil does not contain
        defaultDMaikaRowFiltering("dcFamil.doesNotContain=" + UPDATED_DC_FAMIL, "dcFamil.doesNotContain=" + DEFAULT_DC_FAMIL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcImenaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcImena equals to
        defaultDMaikaRowFiltering("dcImena.equals=" + DEFAULT_DC_IMENA, "dcImena.equals=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcImenaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcImena in
        defaultDMaikaRowFiltering("dcImena.in=" + DEFAULT_DC_IMENA + "," + UPDATED_DC_IMENA, "dcImena.in=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcImenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcImena is not null
        defaultDMaikaRowFiltering("dcImena.specified=true", "dcImena.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcImenaContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcImena contains
        defaultDMaikaRowFiltering("dcImena.contains=" + DEFAULT_DC_IMENA, "dcImena.contains=" + UPDATED_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcImenaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcImena does not contain
        defaultDMaikaRowFiltering("dcImena.doesNotContain=" + UPDATED_DC_IMENA, "dcImena.doesNotContain=" + DEFAULT_DC_IMENA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcPolIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcPol equals to
        defaultDMaikaRowFiltering("dcPol.equals=" + DEFAULT_DC_POL, "dcPol.equals=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcPolIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcPol in
        defaultDMaikaRowFiltering("dcPol.in=" + DEFAULT_DC_POL + "," + UPDATED_DC_POL, "dcPol.in=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcPolIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcPol is not null
        defaultDMaikaRowFiltering("dcPol.specified=true", "dcPol.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcPolContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcPol contains
        defaultDMaikaRowFiltering("dcPol.contains=" + DEFAULT_DC_POL, "dcPol.contains=" + UPDATED_DC_POL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcPolNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcPol does not contain
        defaultDMaikaRowFiltering("dcPol.doesNotContain=" + UPDATED_DC_POL, "dcPol.doesNotContain=" + DEFAULT_DC_POL);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcDarj equals to
        defaultDMaikaRowFiltering("dcDarj.equals=" + DEFAULT_DC_DARJ, "dcDarj.equals=" + UPDATED_DC_DARJ);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcDarj in
        defaultDMaikaRowFiltering("dcDarj.in=" + DEFAULT_DC_DARJ + "," + UPDATED_DC_DARJ, "dcDarj.in=" + UPDATED_DC_DARJ);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcDarj is not null
        defaultDMaikaRowFiltering("dcDarj.specified=true", "dcDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcDarj contains
        defaultDMaikaRowFiltering("dcDarj.contains=" + DEFAULT_DC_DARJ, "dcDarj.contains=" + UPDATED_DC_DARJ);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcDarj does not contain
        defaultDMaikaRowFiltering("dcDarj.doesNotContain=" + UPDATED_DC_DARJ, "dcDarj.doesNotContain=" + DEFAULT_DC_DARJ);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcGradIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcGrad equals to
        defaultDMaikaRowFiltering("dcGrad.equals=" + DEFAULT_DC_GRAD, "dcGrad.equals=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcGradIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcGrad in
        defaultDMaikaRowFiltering("dcGrad.in=" + DEFAULT_DC_GRAD + "," + UPDATED_DC_GRAD, "dcGrad.in=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcGradIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcGrad is not null
        defaultDMaikaRowFiltering("dcGrad.specified=true", "dcGrad.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcGradContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcGrad contains
        defaultDMaikaRowFiltering("dcGrad.contains=" + DEFAULT_DC_GRAD, "dcGrad.contains=" + UPDATED_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcGradNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcGrad does not contain
        defaultDMaikaRowFiltering("dcGrad.doesNotContain=" + UPDATED_DC_GRAD, "dcGrad.doesNotContain=" + DEFAULT_DC_GRAD);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcUlicaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcUlica equals to
        defaultDMaikaRowFiltering("dcUlica.equals=" + DEFAULT_DC_ULICA, "dcUlica.equals=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcUlicaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcUlica in
        defaultDMaikaRowFiltering("dcUlica.in=" + DEFAULT_DC_ULICA + "," + UPDATED_DC_ULICA, "dcUlica.in=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcUlicaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcUlica is not null
        defaultDMaikaRowFiltering("dcUlica.specified=true", "dcUlica.specified=false");
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcUlicaContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcUlica contains
        defaultDMaikaRowFiltering("dcUlica.contains=" + DEFAULT_DC_ULICA, "dcUlica.contains=" + UPDATED_DC_ULICA);
    }

    @Test
    @Transactional
    void getAllDMaikaRowsByDcUlicaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        // Get all the dMaikaRowList where dcUlica does not contain
        defaultDMaikaRowFiltering("dcUlica.doesNotContain=" + UPDATED_DC_ULICA, "dcUlica.doesNotContain=" + DEFAULT_DC_ULICA);
    }

    private void defaultDMaikaRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDMaikaRowShouldBeFound(shouldBeFound);
        defaultDMaikaRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDMaikaRowShouldBeFound(String filter) throws Exception {
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dMaikaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dcFamil").value(hasItem(DEFAULT_DC_FAMIL)))
            .andExpect(jsonPath("$.[*].dcImena").value(hasItem(DEFAULT_DC_IMENA)))
            .andExpect(jsonPath("$.[*].dcPol").value(hasItem(DEFAULT_DC_POL)))
            .andExpect(jsonPath("$.[*].dcDarj").value(hasItem(DEFAULT_DC_DARJ)))
            .andExpect(jsonPath("$.[*].dcGrad").value(hasItem(DEFAULT_DC_GRAD)))
            .andExpect(jsonPath("$.[*].dcUlica").value(hasItem(DEFAULT_DC_ULICA)));

        // Check, that the count call also returns 1
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDMaikaRowShouldNotBeFound(String filter) throws Exception {
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDMaikaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDMaikaRow() throws Exception {
        // Get the dMaikaRow
        restDMaikaRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDMaikaRow() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMaikaRow
        DMaikaRow updatedDMaikaRow = dMaikaRowRepository.findById(dMaikaRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDMaikaRow are not directly saved in db
        em.detach(updatedDMaikaRow);
        updatedDMaikaRow
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcDarj(UPDATED_DC_DARJ)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(updatedDMaikaRow);

        restDMaikaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dMaikaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMaikaRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDMaikaRowToMatchAllProperties(updatedDMaikaRow);
    }

    @Test
    @Transactional
    void putNonExistingDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dMaikaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMaikaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMaikaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDMaikaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMaikaRow using partial update
        DMaikaRow partialUpdatedDMaikaRow = new DMaikaRow();
        partialUpdatedDMaikaRow.setId(dMaikaRow.getId());

        partialUpdatedDMaikaRow.dcFamil(UPDATED_DC_FAMIL).dcImena(UPDATED_DC_IMENA).dcGrad(UPDATED_DC_GRAD).dcUlica(UPDATED_DC_ULICA);

        restDMaikaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDMaikaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDMaikaRow))
            )
            .andExpect(status().isOk());

        // Validate the DMaikaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDMaikaRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDMaikaRow, dMaikaRow),
            getPersistedDMaikaRow(dMaikaRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDMaikaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMaikaRow using partial update
        DMaikaRow partialUpdatedDMaikaRow = new DMaikaRow();
        partialUpdatedDMaikaRow.setId(dMaikaRow.getId());

        partialUpdatedDMaikaRow
            .dcFamil(UPDATED_DC_FAMIL)
            .dcImena(UPDATED_DC_IMENA)
            .dcPol(UPDATED_DC_POL)
            .dcDarj(UPDATED_DC_DARJ)
            .dcGrad(UPDATED_DC_GRAD)
            .dcUlica(UPDATED_DC_ULICA);

        restDMaikaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDMaikaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDMaikaRow))
            )
            .andExpect(status().isOk());

        // Validate the DMaikaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDMaikaRowUpdatableFieldsEquals(partialUpdatedDMaikaRow, getPersistedDMaikaRow(partialUpdatedDMaikaRow));
    }

    @Test
    @Transactional
    void patchNonExistingDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dMaikaRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dMaikaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dMaikaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDMaikaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMaikaRow.setId(longCount.incrementAndGet());

        // Create the DMaikaRow
        DMaikaRowDTO dMaikaRowDTO = dMaikaRowMapper.toDto(dMaikaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMaikaRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dMaikaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DMaikaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDMaikaRow() throws Exception {
        // Initialize the database
        insertedDMaikaRow = dMaikaRowRepository.saveAndFlush(dMaikaRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dMaikaRow
        restDMaikaRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dMaikaRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dMaikaRowRepository.count();
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

    protected DMaikaRow getPersistedDMaikaRow(DMaikaRow dMaikaRow) {
        return dMaikaRowRepository.findById(dMaikaRow.getId()).orElseThrow();
    }

    protected void assertPersistedDMaikaRowToMatchAllProperties(DMaikaRow expectedDMaikaRow) {
        assertDMaikaRowAllPropertiesEquals(expectedDMaikaRow, getPersistedDMaikaRow(expectedDMaikaRow));
    }

    protected void assertPersistedDMaikaRowToMatchUpdatableProperties(DMaikaRow expectedDMaikaRow) {
        assertDMaikaRowAllUpdatablePropertiesEquals(expectedDMaikaRow, getPersistedDMaikaRow(expectedDMaikaRow));
    }
}
