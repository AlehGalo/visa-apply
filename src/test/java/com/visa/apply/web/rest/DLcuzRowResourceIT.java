package com.visa.apply.web.rest;

import static com.visa.apply.domain.DLcuzRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DLcuzRow;
import com.visa.apply.repository.DLcuzRowRepository;
import com.visa.apply.service.dto.DLcuzRowDTO;
import com.visa.apply.service.mapper.DLcuzRowMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link DLcuzRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DLcuzRowResourceIT {

    private static final String DEFAULT_VID_ZP = "AAAAAAAAAA";
    private static final String UPDATED_VID_ZP = "BBBBBBBBBB";

    private static final String DEFAULT_NAC_BEL = "AAAAAAAAAA";
    private static final String UPDATED_NAC_BEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NAC_PASP = 1;
    private static final Integer UPDATED_NAC_PASP = 2;
    private static final Integer SMALLER_NAC_PASP = 1 - 1;

    private static final LocalDate DEFAULT_PASP_VAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PASP_VAL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_PASP_VAL = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_GRAJ = "AAAAAAAAAA";
    private static final String UPDATED_GRAJ = "BBBBBBBBBB";

    private static final String DEFAULT_FAMIL = "AAAAAAAAAA";
    private static final String UPDATED_FAMIL = "BBBBBBBBBB";

    private static final String DEFAULT_IMENA = "AAAAAAAAAA";
    private static final String UPDATED_IMENA = "BBBBBBBBBB";

    private static final String DEFAULT_DAT_RAJ = "AAAAAAAAAA";
    private static final String UPDATED_DAT_RAJ = "BBBBBBBBBB";

    private static final String DEFAULT_POL = "AAAAAAAAAA";
    private static final String UPDATED_POL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_IZD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_IZD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DAT_IZD = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/d-lcuz-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DLcuzRowRepository dLcuzRowRepository;

    @Autowired
    private DLcuzRowMapper dLcuzRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDLcuzRowMockMvc;

    private DLcuzRow dLcuzRow;

    private DLcuzRow insertedDLcuzRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLcuzRow createEntity() {
        return new DLcuzRow()
            .vidZp(DEFAULT_VID_ZP)
            .nacBel(DEFAULT_NAC_BEL)
            .nacPasp(DEFAULT_NAC_PASP)
            .paspVal(DEFAULT_PASP_VAL)
            .graj(DEFAULT_GRAJ)
            .famil(DEFAULT_FAMIL)
            .imena(DEFAULT_IMENA)
            .datRaj(DEFAULT_DAT_RAJ)
            .pol(DEFAULT_POL)
            .datIzd(DEFAULT_DAT_IZD);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLcuzRow createUpdatedEntity() {
        return new DLcuzRow()
            .vidZp(UPDATED_VID_ZP)
            .nacBel(UPDATED_NAC_BEL)
            .nacPasp(UPDATED_NAC_PASP)
            .paspVal(UPDATED_PASP_VAL)
            .graj(UPDATED_GRAJ)
            .famil(UPDATED_FAMIL)
            .imena(UPDATED_IMENA)
            .datRaj(UPDATED_DAT_RAJ)
            .pol(UPDATED_POL)
            .datIzd(UPDATED_DAT_IZD);
    }

    @BeforeEach
    void initTest() {
        dLcuzRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDLcuzRow != null) {
            dLcuzRowRepository.delete(insertedDLcuzRow);
            insertedDLcuzRow = null;
        }
    }

    @Test
    @Transactional
    void createDLcuzRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);
        var returnedDLcuzRowDTO = om.readValue(
            restDLcuzRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DLcuzRowDTO.class
        );

        // Validate the DLcuzRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDLcuzRow = dLcuzRowMapper.toEntity(returnedDLcuzRowDTO);
        assertDLcuzRowUpdatableFieldsEquals(returnedDLcuzRow, getPersistedDLcuzRow(returnedDLcuzRow));

        insertedDLcuzRow = returnedDLcuzRow;
    }

    @Test
    @Transactional
    void createDLcuzRowWithExistingId() throws Exception {
        // Create the DLcuzRow with an existing ID
        dLcuzRow.setId(1L);
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVidZpIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setVidZp(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNacBelIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setNacBel(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaspValIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setPaspVal(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGrajIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setGraj(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFamilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setFamil(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkImenaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setImena(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDatRajIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setDatRaj(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPolIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setPol(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDatIzdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcuzRow.setDatIzd(null);

        // Create the DLcuzRow, which fails.
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        restDLcuzRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDLcuzRows() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLcuzRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].vidZp").value(hasItem(DEFAULT_VID_ZP)))
            .andExpect(jsonPath("$.[*].nacBel").value(hasItem(DEFAULT_NAC_BEL)))
            .andExpect(jsonPath("$.[*].nacPasp").value(hasItem(DEFAULT_NAC_PASP)))
            .andExpect(jsonPath("$.[*].paspVal").value(hasItem(DEFAULT_PASP_VAL.toString())))
            .andExpect(jsonPath("$.[*].graj").value(hasItem(DEFAULT_GRAJ)))
            .andExpect(jsonPath("$.[*].famil").value(hasItem(DEFAULT_FAMIL)))
            .andExpect(jsonPath("$.[*].imena").value(hasItem(DEFAULT_IMENA)))
            .andExpect(jsonPath("$.[*].datRaj").value(hasItem(DEFAULT_DAT_RAJ)))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL)))
            .andExpect(jsonPath("$.[*].datIzd").value(hasItem(DEFAULT_DAT_IZD.toString())));
    }

    @Test
    @Transactional
    void getDLcuzRow() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get the dLcuzRow
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dLcuzRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dLcuzRow.getId().intValue()))
            .andExpect(jsonPath("$.vidZp").value(DEFAULT_VID_ZP))
            .andExpect(jsonPath("$.nacBel").value(DEFAULT_NAC_BEL))
            .andExpect(jsonPath("$.nacPasp").value(DEFAULT_NAC_PASP))
            .andExpect(jsonPath("$.paspVal").value(DEFAULT_PASP_VAL.toString()))
            .andExpect(jsonPath("$.graj").value(DEFAULT_GRAJ))
            .andExpect(jsonPath("$.famil").value(DEFAULT_FAMIL))
            .andExpect(jsonPath("$.imena").value(DEFAULT_IMENA))
            .andExpect(jsonPath("$.datRaj").value(DEFAULT_DAT_RAJ))
            .andExpect(jsonPath("$.pol").value(DEFAULT_POL))
            .andExpect(jsonPath("$.datIzd").value(DEFAULT_DAT_IZD.toString()));
    }

    @Test
    @Transactional
    void getDLcuzRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        Long id = dLcuzRow.getId();

        defaultDLcuzRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDLcuzRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDLcuzRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByVidZpIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where vidZp equals to
        defaultDLcuzRowFiltering("vidZp.equals=" + DEFAULT_VID_ZP, "vidZp.equals=" + UPDATED_VID_ZP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByVidZpIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where vidZp in
        defaultDLcuzRowFiltering("vidZp.in=" + DEFAULT_VID_ZP + "," + UPDATED_VID_ZP, "vidZp.in=" + UPDATED_VID_ZP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByVidZpIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where vidZp is not null
        defaultDLcuzRowFiltering("vidZp.specified=true", "vidZp.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByVidZpContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where vidZp contains
        defaultDLcuzRowFiltering("vidZp.contains=" + DEFAULT_VID_ZP, "vidZp.contains=" + UPDATED_VID_ZP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByVidZpNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where vidZp does not contain
        defaultDLcuzRowFiltering("vidZp.doesNotContain=" + UPDATED_VID_ZP, "vidZp.doesNotContain=" + DEFAULT_VID_ZP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacBelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacBel equals to
        defaultDLcuzRowFiltering("nacBel.equals=" + DEFAULT_NAC_BEL, "nacBel.equals=" + UPDATED_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacBelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacBel in
        defaultDLcuzRowFiltering("nacBel.in=" + DEFAULT_NAC_BEL + "," + UPDATED_NAC_BEL, "nacBel.in=" + UPDATED_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacBelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacBel is not null
        defaultDLcuzRowFiltering("nacBel.specified=true", "nacBel.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacBelContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacBel contains
        defaultDLcuzRowFiltering("nacBel.contains=" + DEFAULT_NAC_BEL, "nacBel.contains=" + UPDATED_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacBelNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacBel does not contain
        defaultDLcuzRowFiltering("nacBel.doesNotContain=" + UPDATED_NAC_BEL, "nacBel.doesNotContain=" + DEFAULT_NAC_BEL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp equals to
        defaultDLcuzRowFiltering("nacPasp.equals=" + DEFAULT_NAC_PASP, "nacPasp.equals=" + UPDATED_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp in
        defaultDLcuzRowFiltering("nacPasp.in=" + DEFAULT_NAC_PASP + "," + UPDATED_NAC_PASP, "nacPasp.in=" + UPDATED_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp is not null
        defaultDLcuzRowFiltering("nacPasp.specified=true", "nacPasp.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp is greater than or equal to
        defaultDLcuzRowFiltering("nacPasp.greaterThanOrEqual=" + DEFAULT_NAC_PASP, "nacPasp.greaterThanOrEqual=" + UPDATED_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp is less than or equal to
        defaultDLcuzRowFiltering("nacPasp.lessThanOrEqual=" + DEFAULT_NAC_PASP, "nacPasp.lessThanOrEqual=" + SMALLER_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp is less than
        defaultDLcuzRowFiltering("nacPasp.lessThan=" + UPDATED_NAC_PASP, "nacPasp.lessThan=" + DEFAULT_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByNacPaspIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where nacPasp is greater than
        defaultDLcuzRowFiltering("nacPasp.greaterThan=" + SMALLER_NAC_PASP, "nacPasp.greaterThan=" + DEFAULT_NAC_PASP);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal equals to
        defaultDLcuzRowFiltering("paspVal.equals=" + DEFAULT_PASP_VAL, "paspVal.equals=" + UPDATED_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal in
        defaultDLcuzRowFiltering("paspVal.in=" + DEFAULT_PASP_VAL + "," + UPDATED_PASP_VAL, "paspVal.in=" + UPDATED_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal is not null
        defaultDLcuzRowFiltering("paspVal.specified=true", "paspVal.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal is greater than or equal to
        defaultDLcuzRowFiltering("paspVal.greaterThanOrEqual=" + DEFAULT_PASP_VAL, "paspVal.greaterThanOrEqual=" + UPDATED_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal is less than or equal to
        defaultDLcuzRowFiltering("paspVal.lessThanOrEqual=" + DEFAULT_PASP_VAL, "paspVal.lessThanOrEqual=" + SMALLER_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal is less than
        defaultDLcuzRowFiltering("paspVal.lessThan=" + UPDATED_PASP_VAL, "paspVal.lessThan=" + DEFAULT_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPaspValIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where paspVal is greater than
        defaultDLcuzRowFiltering("paspVal.greaterThan=" + SMALLER_PASP_VAL, "paspVal.greaterThan=" + DEFAULT_PASP_VAL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByGrajIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where graj equals to
        defaultDLcuzRowFiltering("graj.equals=" + DEFAULT_GRAJ, "graj.equals=" + UPDATED_GRAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByGrajIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where graj in
        defaultDLcuzRowFiltering("graj.in=" + DEFAULT_GRAJ + "," + UPDATED_GRAJ, "graj.in=" + UPDATED_GRAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByGrajIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where graj is not null
        defaultDLcuzRowFiltering("graj.specified=true", "graj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByGrajContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where graj contains
        defaultDLcuzRowFiltering("graj.contains=" + DEFAULT_GRAJ, "graj.contains=" + UPDATED_GRAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByGrajNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where graj does not contain
        defaultDLcuzRowFiltering("graj.doesNotContain=" + UPDATED_GRAJ, "graj.doesNotContain=" + DEFAULT_GRAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByFamilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where famil equals to
        defaultDLcuzRowFiltering("famil.equals=" + DEFAULT_FAMIL, "famil.equals=" + UPDATED_FAMIL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByFamilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where famil in
        defaultDLcuzRowFiltering("famil.in=" + DEFAULT_FAMIL + "," + UPDATED_FAMIL, "famil.in=" + UPDATED_FAMIL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByFamilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where famil is not null
        defaultDLcuzRowFiltering("famil.specified=true", "famil.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByFamilContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where famil contains
        defaultDLcuzRowFiltering("famil.contains=" + DEFAULT_FAMIL, "famil.contains=" + UPDATED_FAMIL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByFamilNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where famil does not contain
        defaultDLcuzRowFiltering("famil.doesNotContain=" + UPDATED_FAMIL, "famil.doesNotContain=" + DEFAULT_FAMIL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByImenaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where imena equals to
        defaultDLcuzRowFiltering("imena.equals=" + DEFAULT_IMENA, "imena.equals=" + UPDATED_IMENA);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByImenaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where imena in
        defaultDLcuzRowFiltering("imena.in=" + DEFAULT_IMENA + "," + UPDATED_IMENA, "imena.in=" + UPDATED_IMENA);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByImenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where imena is not null
        defaultDLcuzRowFiltering("imena.specified=true", "imena.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByImenaContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where imena contains
        defaultDLcuzRowFiltering("imena.contains=" + DEFAULT_IMENA, "imena.contains=" + UPDATED_IMENA);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByImenaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where imena does not contain
        defaultDLcuzRowFiltering("imena.doesNotContain=" + UPDATED_IMENA, "imena.doesNotContain=" + DEFAULT_IMENA);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatRajIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datRaj equals to
        defaultDLcuzRowFiltering("datRaj.equals=" + DEFAULT_DAT_RAJ, "datRaj.equals=" + UPDATED_DAT_RAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatRajIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datRaj in
        defaultDLcuzRowFiltering("datRaj.in=" + DEFAULT_DAT_RAJ + "," + UPDATED_DAT_RAJ, "datRaj.in=" + UPDATED_DAT_RAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatRajIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datRaj is not null
        defaultDLcuzRowFiltering("datRaj.specified=true", "datRaj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatRajContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datRaj contains
        defaultDLcuzRowFiltering("datRaj.contains=" + DEFAULT_DAT_RAJ, "datRaj.contains=" + UPDATED_DAT_RAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatRajNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datRaj does not contain
        defaultDLcuzRowFiltering("datRaj.doesNotContain=" + UPDATED_DAT_RAJ, "datRaj.doesNotContain=" + DEFAULT_DAT_RAJ);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPolIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where pol equals to
        defaultDLcuzRowFiltering("pol.equals=" + DEFAULT_POL, "pol.equals=" + UPDATED_POL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPolIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where pol in
        defaultDLcuzRowFiltering("pol.in=" + DEFAULT_POL + "," + UPDATED_POL, "pol.in=" + UPDATED_POL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPolIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where pol is not null
        defaultDLcuzRowFiltering("pol.specified=true", "pol.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPolContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where pol contains
        defaultDLcuzRowFiltering("pol.contains=" + DEFAULT_POL, "pol.contains=" + UPDATED_POL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByPolNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where pol does not contain
        defaultDLcuzRowFiltering("pol.doesNotContain=" + UPDATED_POL, "pol.doesNotContain=" + DEFAULT_POL);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd equals to
        defaultDLcuzRowFiltering("datIzd.equals=" + DEFAULT_DAT_IZD, "datIzd.equals=" + UPDATED_DAT_IZD);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd in
        defaultDLcuzRowFiltering("datIzd.in=" + DEFAULT_DAT_IZD + "," + UPDATED_DAT_IZD, "datIzd.in=" + UPDATED_DAT_IZD);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd is not null
        defaultDLcuzRowFiltering("datIzd.specified=true", "datIzd.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd is greater than or equal to
        defaultDLcuzRowFiltering("datIzd.greaterThanOrEqual=" + DEFAULT_DAT_IZD, "datIzd.greaterThanOrEqual=" + UPDATED_DAT_IZD);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd is less than or equal to
        defaultDLcuzRowFiltering("datIzd.lessThanOrEqual=" + DEFAULT_DAT_IZD, "datIzd.lessThanOrEqual=" + SMALLER_DAT_IZD);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd is less than
        defaultDLcuzRowFiltering("datIzd.lessThan=" + UPDATED_DAT_IZD, "datIzd.lessThan=" + DEFAULT_DAT_IZD);
    }

    @Test
    @Transactional
    void getAllDLcuzRowsByDatIzdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        // Get all the dLcuzRowList where datIzd is greater than
        defaultDLcuzRowFiltering("datIzd.greaterThan=" + SMALLER_DAT_IZD, "datIzd.greaterThan=" + DEFAULT_DAT_IZD);
    }

    private void defaultDLcuzRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDLcuzRowShouldBeFound(shouldBeFound);
        defaultDLcuzRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDLcuzRowShouldBeFound(String filter) throws Exception {
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLcuzRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].vidZp").value(hasItem(DEFAULT_VID_ZP)))
            .andExpect(jsonPath("$.[*].nacBel").value(hasItem(DEFAULT_NAC_BEL)))
            .andExpect(jsonPath("$.[*].nacPasp").value(hasItem(DEFAULT_NAC_PASP)))
            .andExpect(jsonPath("$.[*].paspVal").value(hasItem(DEFAULT_PASP_VAL.toString())))
            .andExpect(jsonPath("$.[*].graj").value(hasItem(DEFAULT_GRAJ)))
            .andExpect(jsonPath("$.[*].famil").value(hasItem(DEFAULT_FAMIL)))
            .andExpect(jsonPath("$.[*].imena").value(hasItem(DEFAULT_IMENA)))
            .andExpect(jsonPath("$.[*].datRaj").value(hasItem(DEFAULT_DAT_RAJ)))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL)))
            .andExpect(jsonPath("$.[*].datIzd").value(hasItem(DEFAULT_DAT_IZD.toString())));

        // Check, that the count call also returns 1
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDLcuzRowShouldNotBeFound(String filter) throws Exception {
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDLcuzRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDLcuzRow() throws Exception {
        // Get the dLcuzRow
        restDLcuzRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDLcuzRow() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcuzRow
        DLcuzRow updatedDLcuzRow = dLcuzRowRepository.findById(dLcuzRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDLcuzRow are not directly saved in db
        em.detach(updatedDLcuzRow);
        updatedDLcuzRow
            .vidZp(UPDATED_VID_ZP)
            .nacBel(UPDATED_NAC_BEL)
            .nacPasp(UPDATED_NAC_PASP)
            .paspVal(UPDATED_PASP_VAL)
            .graj(UPDATED_GRAJ)
            .famil(UPDATED_FAMIL)
            .imena(UPDATED_IMENA)
            .datRaj(UPDATED_DAT_RAJ)
            .pol(UPDATED_POL)
            .datIzd(UPDATED_DAT_IZD);
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(updatedDLcuzRow);

        restDLcuzRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLcuzRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcuzRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDLcuzRowToMatchAllProperties(updatedDLcuzRow);
    }

    @Test
    @Transactional
    void putNonExistingDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLcuzRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcuzRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcuzRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDLcuzRowWithPatch() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcuzRow using partial update
        DLcuzRow partialUpdatedDLcuzRow = new DLcuzRow();
        partialUpdatedDLcuzRow.setId(dLcuzRow.getId());

        partialUpdatedDLcuzRow
            .vidZp(UPDATED_VID_ZP)
            .nacBel(UPDATED_NAC_BEL)
            .famil(UPDATED_FAMIL)
            .datRaj(UPDATED_DAT_RAJ)
            .pol(UPDATED_POL)
            .datIzd(UPDATED_DAT_IZD);

        restDLcuzRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLcuzRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLcuzRow))
            )
            .andExpect(status().isOk());

        // Validate the DLcuzRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLcuzRowUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDLcuzRow, dLcuzRow), getPersistedDLcuzRow(dLcuzRow));
    }

    @Test
    @Transactional
    void fullUpdateDLcuzRowWithPatch() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcuzRow using partial update
        DLcuzRow partialUpdatedDLcuzRow = new DLcuzRow();
        partialUpdatedDLcuzRow.setId(dLcuzRow.getId());

        partialUpdatedDLcuzRow
            .vidZp(UPDATED_VID_ZP)
            .nacBel(UPDATED_NAC_BEL)
            .nacPasp(UPDATED_NAC_PASP)
            .paspVal(UPDATED_PASP_VAL)
            .graj(UPDATED_GRAJ)
            .famil(UPDATED_FAMIL)
            .imena(UPDATED_IMENA)
            .datRaj(UPDATED_DAT_RAJ)
            .pol(UPDATED_POL)
            .datIzd(UPDATED_DAT_IZD);

        restDLcuzRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLcuzRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLcuzRow))
            )
            .andExpect(status().isOk());

        // Validate the DLcuzRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLcuzRowUpdatableFieldsEquals(partialUpdatedDLcuzRow, getPersistedDLcuzRow(partialUpdatedDLcuzRow));
    }

    @Test
    @Transactional
    void patchNonExistingDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dLcuzRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLcuzRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLcuzRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDLcuzRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcuzRow.setId(longCount.incrementAndGet());

        // Create the DLcuzRow
        DLcuzRowDTO dLcuzRowDTO = dLcuzRowMapper.toDto(dLcuzRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcuzRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dLcuzRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLcuzRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDLcuzRow() throws Exception {
        // Initialize the database
        insertedDLcuzRow = dLcuzRowRepository.saveAndFlush(dLcuzRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dLcuzRow
        restDLcuzRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dLcuzRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dLcuzRowRepository.count();
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

    protected DLcuzRow getPersistedDLcuzRow(DLcuzRow dLcuzRow) {
        return dLcuzRowRepository.findById(dLcuzRow.getId()).orElseThrow();
    }

    protected void assertPersistedDLcuzRowToMatchAllProperties(DLcuzRow expectedDLcuzRow) {
        assertDLcuzRowAllPropertiesEquals(expectedDLcuzRow, getPersistedDLcuzRow(expectedDLcuzRow));
    }

    protected void assertPersistedDLcuzRowToMatchUpdatableProperties(DLcuzRow expectedDLcuzRow) {
        assertDLcuzRowAllUpdatablePropertiesEquals(expectedDLcuzRow, getPersistedDLcuzRow(expectedDLcuzRow));
    }
}
