package com.visa.apply.web.rest;

import static com.visa.apply.domain.DFingersRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DFingersRow;
import com.visa.apply.repository.DFingersRowRepository;
import com.visa.apply.service.dto.DFingersRowDTO;
import com.visa.apply.service.mapper.DFingersRowMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link DFingersRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DFingersRowResourceIT {

    private static final LocalDate DEFAULT_FN_DATREG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FN_DATREG = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FN_DATREG = LocalDate.ofEpochDay(-1L);

    private static final Instant DEFAULT_FN_DATVAV = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FN_DATVAV = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FN_USERA = "AAAAAAAAAA";
    private static final String UPDATED_FN_USERA = "BBBBBBBBBB";

    private static final Integer DEFAULT_FN_SID = 1;
    private static final Integer UPDATED_FN_SID = 2;
    private static final Integer SMALLER_FN_SID = 1 - 1;

    private static final Integer DEFAULT_FN_PNR = 1;
    private static final Integer UPDATED_FN_PNR = 2;
    private static final Integer SMALLER_FN_PNR = 1 - 1;

    private static final String DEFAULT_FN_VIDMOL = "AAAAAAAAAA";
    private static final String UPDATED_FN_VIDMOL = "BBBBBBBBBB";

    private static final String DEFAULT_FN_DRUGI = "AAAAAAAAAA";
    private static final String UPDATED_FN_DRUGI = "BBBBBBBBBB";

    private static final Integer DEFAULT_FN_DEVICEID = 1;
    private static final Integer UPDATED_FN_DEVICEID = 2;
    private static final Integer SMALLER_FN_DEVICEID = 1 - 1;

    private static final Integer DEFAULT_FN_SCANRES = 1;
    private static final Integer UPDATED_FN_SCANRES = 2;
    private static final Integer SMALLER_FN_SCANRES = 1 - 1;

    private static final Integer DEFAULT_FN_WIDTH = 1;
    private static final Integer UPDATED_FN_WIDTH = 2;
    private static final Integer SMALLER_FN_WIDTH = 1 - 1;

    private static final Integer DEFAULT_FN_HEIGHT = 1;
    private static final Integer UPDATED_FN_HEIGHT = 2;
    private static final Integer SMALLER_FN_HEIGHT = 1 - 1;

    private static final Integer DEFAULT_FN_PIXELDEPTH = 1;
    private static final Integer UPDATED_FN_PIXELDEPTH = 2;
    private static final Integer SMALLER_FN_PIXELDEPTH = 1 - 1;

    private static final Integer DEFAULT_FN_COMPRESSALGO = 1;
    private static final Integer UPDATED_FN_COMPRESSALGO = 2;
    private static final Integer SMALLER_FN_COMPRESSALGO = 1 - 1;

    private static final String DEFAULT_FN_FINGERPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_FN_FINGERPOSITION = "BBBBBBBBBB";

    private static final Integer DEFAULT_FN_IMAGEQUALITY = 1;
    private static final Integer UPDATED_FN_IMAGEQUALITY = 2;
    private static final Integer SMALLER_FN_IMAGEQUALITY = 1 - 1;

    private static final String DEFAULT_FN_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_FN_IMAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FN_IMGLEN = 1;
    private static final Integer UPDATED_FN_IMGLEN = 2;
    private static final Integer SMALLER_FN_IMGLEN = 1 - 1;

    private static final String DEFAULT_FN_NOTTAKENREASON = "AAAAAAAAAA";
    private static final String UPDATED_FN_NOTTAKENREASON = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-fingers-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DFingersRowRepository dFingersRowRepository;

    @Autowired
    private DFingersRowMapper dFingersRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDFingersRowMockMvc;

    private DFingersRow dFingersRow;

    private DFingersRow insertedDFingersRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DFingersRow createEntity() {
        return new DFingersRow()
            .fnDatreg(DEFAULT_FN_DATREG)
            .fnDatvav(DEFAULT_FN_DATVAV)
            .fnUsera(DEFAULT_FN_USERA)
            .fnSid(DEFAULT_FN_SID)
            .fnPnr(DEFAULT_FN_PNR)
            .fnVidmol(DEFAULT_FN_VIDMOL)
            .fnDrugi(DEFAULT_FN_DRUGI)
            .fnDeviceid(DEFAULT_FN_DEVICEID)
            .fnScanres(DEFAULT_FN_SCANRES)
            .fnWidth(DEFAULT_FN_WIDTH)
            .fnHeight(DEFAULT_FN_HEIGHT)
            .fnPixeldepth(DEFAULT_FN_PIXELDEPTH)
            .fnCompressalgo(DEFAULT_FN_COMPRESSALGO)
            .fnFingerposition(DEFAULT_FN_FINGERPOSITION)
            .fnImagequality(DEFAULT_FN_IMAGEQUALITY)
            .fnImage(DEFAULT_FN_IMAGE)
            .fnImglen(DEFAULT_FN_IMGLEN)
            .fnNottakenreason(DEFAULT_FN_NOTTAKENREASON);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DFingersRow createUpdatedEntity() {
        return new DFingersRow()
            .fnDatreg(UPDATED_FN_DATREG)
            .fnDatvav(UPDATED_FN_DATVAV)
            .fnUsera(UPDATED_FN_USERA)
            .fnSid(UPDATED_FN_SID)
            .fnPnr(UPDATED_FN_PNR)
            .fnVidmol(UPDATED_FN_VIDMOL)
            .fnDrugi(UPDATED_FN_DRUGI)
            .fnDeviceid(UPDATED_FN_DEVICEID)
            .fnScanres(UPDATED_FN_SCANRES)
            .fnWidth(UPDATED_FN_WIDTH)
            .fnHeight(UPDATED_FN_HEIGHT)
            .fnPixeldepth(UPDATED_FN_PIXELDEPTH)
            .fnCompressalgo(UPDATED_FN_COMPRESSALGO)
            .fnFingerposition(UPDATED_FN_FINGERPOSITION)
            .fnImagequality(UPDATED_FN_IMAGEQUALITY)
            .fnImage(UPDATED_FN_IMAGE)
            .fnImglen(UPDATED_FN_IMGLEN)
            .fnNottakenreason(UPDATED_FN_NOTTAKENREASON);
    }

    @BeforeEach
    void initTest() {
        dFingersRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDFingersRow != null) {
            dFingersRowRepository.delete(insertedDFingersRow);
            insertedDFingersRow = null;
        }
    }

    @Test
    @Transactional
    void createDFingersRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);
        var returnedDFingersRowDTO = om.readValue(
            restDFingersRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DFingersRowDTO.class
        );

        // Validate the DFingersRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDFingersRow = dFingersRowMapper.toEntity(returnedDFingersRowDTO);
        assertDFingersRowUpdatableFieldsEquals(returnedDFingersRow, getPersistedDFingersRow(returnedDFingersRow));

        insertedDFingersRow = returnedDFingersRow;
    }

    @Test
    @Transactional
    void createDFingersRowWithExistingId() throws Exception {
        // Create the DFingersRow with an existing ID
        dFingersRow.setId(1L);
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFnDatregIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnDatreg(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFnDatvavIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnDatvav(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFnUseraIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnUsera(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFnVidmolIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnVidmol(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFnDrugiIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnDrugi(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFnFingerpositionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dFingersRow.setFnFingerposition(null);

        // Create the DFingersRow, which fails.
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        restDFingersRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDFingersRows() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFingersRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].fnDatreg").value(hasItem(DEFAULT_FN_DATREG.toString())))
            .andExpect(jsonPath("$.[*].fnDatvav").value(hasItem(DEFAULT_FN_DATVAV.toString())))
            .andExpect(jsonPath("$.[*].fnUsera").value(hasItem(DEFAULT_FN_USERA)))
            .andExpect(jsonPath("$.[*].fnSid").value(hasItem(DEFAULT_FN_SID)))
            .andExpect(jsonPath("$.[*].fnPnr").value(hasItem(DEFAULT_FN_PNR)))
            .andExpect(jsonPath("$.[*].fnVidmol").value(hasItem(DEFAULT_FN_VIDMOL)))
            .andExpect(jsonPath("$.[*].fnDrugi").value(hasItem(DEFAULT_FN_DRUGI)))
            .andExpect(jsonPath("$.[*].fnDeviceid").value(hasItem(DEFAULT_FN_DEVICEID)))
            .andExpect(jsonPath("$.[*].fnScanres").value(hasItem(DEFAULT_FN_SCANRES)))
            .andExpect(jsonPath("$.[*].fnWidth").value(hasItem(DEFAULT_FN_WIDTH)))
            .andExpect(jsonPath("$.[*].fnHeight").value(hasItem(DEFAULT_FN_HEIGHT)))
            .andExpect(jsonPath("$.[*].fnPixeldepth").value(hasItem(DEFAULT_FN_PIXELDEPTH)))
            .andExpect(jsonPath("$.[*].fnCompressalgo").value(hasItem(DEFAULT_FN_COMPRESSALGO)))
            .andExpect(jsonPath("$.[*].fnFingerposition").value(hasItem(DEFAULT_FN_FINGERPOSITION)))
            .andExpect(jsonPath("$.[*].fnImagequality").value(hasItem(DEFAULT_FN_IMAGEQUALITY)))
            .andExpect(jsonPath("$.[*].fnImage").value(hasItem(DEFAULT_FN_IMAGE)))
            .andExpect(jsonPath("$.[*].fnImglen").value(hasItem(DEFAULT_FN_IMGLEN)))
            .andExpect(jsonPath("$.[*].fnNottakenreason").value(hasItem(DEFAULT_FN_NOTTAKENREASON)));
    }

    @Test
    @Transactional
    void getDFingersRow() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get the dFingersRow
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dFingersRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dFingersRow.getId().intValue()))
            .andExpect(jsonPath("$.fnDatreg").value(DEFAULT_FN_DATREG.toString()))
            .andExpect(jsonPath("$.fnDatvav").value(DEFAULT_FN_DATVAV.toString()))
            .andExpect(jsonPath("$.fnUsera").value(DEFAULT_FN_USERA))
            .andExpect(jsonPath("$.fnSid").value(DEFAULT_FN_SID))
            .andExpect(jsonPath("$.fnPnr").value(DEFAULT_FN_PNR))
            .andExpect(jsonPath("$.fnVidmol").value(DEFAULT_FN_VIDMOL))
            .andExpect(jsonPath("$.fnDrugi").value(DEFAULT_FN_DRUGI))
            .andExpect(jsonPath("$.fnDeviceid").value(DEFAULT_FN_DEVICEID))
            .andExpect(jsonPath("$.fnScanres").value(DEFAULT_FN_SCANRES))
            .andExpect(jsonPath("$.fnWidth").value(DEFAULT_FN_WIDTH))
            .andExpect(jsonPath("$.fnHeight").value(DEFAULT_FN_HEIGHT))
            .andExpect(jsonPath("$.fnPixeldepth").value(DEFAULT_FN_PIXELDEPTH))
            .andExpect(jsonPath("$.fnCompressalgo").value(DEFAULT_FN_COMPRESSALGO))
            .andExpect(jsonPath("$.fnFingerposition").value(DEFAULT_FN_FINGERPOSITION))
            .andExpect(jsonPath("$.fnImagequality").value(DEFAULT_FN_IMAGEQUALITY))
            .andExpect(jsonPath("$.fnImage").value(DEFAULT_FN_IMAGE))
            .andExpect(jsonPath("$.fnImglen").value(DEFAULT_FN_IMGLEN))
            .andExpect(jsonPath("$.fnNottakenreason").value(DEFAULT_FN_NOTTAKENREASON));
    }

    @Test
    @Transactional
    void getDFingersRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        Long id = dFingersRow.getId();

        defaultDFingersRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDFingersRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDFingersRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg equals to
        defaultDFingersRowFiltering("fnDatreg.equals=" + DEFAULT_FN_DATREG, "fnDatreg.equals=" + UPDATED_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg in
        defaultDFingersRowFiltering("fnDatreg.in=" + DEFAULT_FN_DATREG + "," + UPDATED_FN_DATREG, "fnDatreg.in=" + UPDATED_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg is not null
        defaultDFingersRowFiltering("fnDatreg.specified=true", "fnDatreg.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg is greater than or equal to
        defaultDFingersRowFiltering("fnDatreg.greaterThanOrEqual=" + DEFAULT_FN_DATREG, "fnDatreg.greaterThanOrEqual=" + UPDATED_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg is less than or equal to
        defaultDFingersRowFiltering("fnDatreg.lessThanOrEqual=" + DEFAULT_FN_DATREG, "fnDatreg.lessThanOrEqual=" + SMALLER_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg is less than
        defaultDFingersRowFiltering("fnDatreg.lessThan=" + UPDATED_FN_DATREG, "fnDatreg.lessThan=" + DEFAULT_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatregIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatreg is greater than
        defaultDFingersRowFiltering("fnDatreg.greaterThan=" + SMALLER_FN_DATREG, "fnDatreg.greaterThan=" + DEFAULT_FN_DATREG);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatvavIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatvav equals to
        defaultDFingersRowFiltering("fnDatvav.equals=" + DEFAULT_FN_DATVAV, "fnDatvav.equals=" + UPDATED_FN_DATVAV);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatvavIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatvav in
        defaultDFingersRowFiltering("fnDatvav.in=" + DEFAULT_FN_DATVAV + "," + UPDATED_FN_DATVAV, "fnDatvav.in=" + UPDATED_FN_DATVAV);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDatvavIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDatvav is not null
        defaultDFingersRowFiltering("fnDatvav.specified=true", "fnDatvav.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnUseraIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnUsera equals to
        defaultDFingersRowFiltering("fnUsera.equals=" + DEFAULT_FN_USERA, "fnUsera.equals=" + UPDATED_FN_USERA);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnUseraIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnUsera in
        defaultDFingersRowFiltering("fnUsera.in=" + DEFAULT_FN_USERA + "," + UPDATED_FN_USERA, "fnUsera.in=" + UPDATED_FN_USERA);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnUseraIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnUsera is not null
        defaultDFingersRowFiltering("fnUsera.specified=true", "fnUsera.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnUseraContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnUsera contains
        defaultDFingersRowFiltering("fnUsera.contains=" + DEFAULT_FN_USERA, "fnUsera.contains=" + UPDATED_FN_USERA);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnUseraNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnUsera does not contain
        defaultDFingersRowFiltering("fnUsera.doesNotContain=" + UPDATED_FN_USERA, "fnUsera.doesNotContain=" + DEFAULT_FN_USERA);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid equals to
        defaultDFingersRowFiltering("fnSid.equals=" + DEFAULT_FN_SID, "fnSid.equals=" + UPDATED_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid in
        defaultDFingersRowFiltering("fnSid.in=" + DEFAULT_FN_SID + "," + UPDATED_FN_SID, "fnSid.in=" + UPDATED_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid is not null
        defaultDFingersRowFiltering("fnSid.specified=true", "fnSid.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid is greater than or equal to
        defaultDFingersRowFiltering("fnSid.greaterThanOrEqual=" + DEFAULT_FN_SID, "fnSid.greaterThanOrEqual=" + UPDATED_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid is less than or equal to
        defaultDFingersRowFiltering("fnSid.lessThanOrEqual=" + DEFAULT_FN_SID, "fnSid.lessThanOrEqual=" + SMALLER_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid is less than
        defaultDFingersRowFiltering("fnSid.lessThan=" + UPDATED_FN_SID, "fnSid.lessThan=" + DEFAULT_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnSidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnSid is greater than
        defaultDFingersRowFiltering("fnSid.greaterThan=" + SMALLER_FN_SID, "fnSid.greaterThan=" + DEFAULT_FN_SID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr equals to
        defaultDFingersRowFiltering("fnPnr.equals=" + DEFAULT_FN_PNR, "fnPnr.equals=" + UPDATED_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr in
        defaultDFingersRowFiltering("fnPnr.in=" + DEFAULT_FN_PNR + "," + UPDATED_FN_PNR, "fnPnr.in=" + UPDATED_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr is not null
        defaultDFingersRowFiltering("fnPnr.specified=true", "fnPnr.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr is greater than or equal to
        defaultDFingersRowFiltering("fnPnr.greaterThanOrEqual=" + DEFAULT_FN_PNR, "fnPnr.greaterThanOrEqual=" + UPDATED_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr is less than or equal to
        defaultDFingersRowFiltering("fnPnr.lessThanOrEqual=" + DEFAULT_FN_PNR, "fnPnr.lessThanOrEqual=" + SMALLER_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr is less than
        defaultDFingersRowFiltering("fnPnr.lessThan=" + UPDATED_FN_PNR, "fnPnr.lessThan=" + DEFAULT_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPnrIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPnr is greater than
        defaultDFingersRowFiltering("fnPnr.greaterThan=" + SMALLER_FN_PNR, "fnPnr.greaterThan=" + DEFAULT_FN_PNR);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnVidmolIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnVidmol equals to
        defaultDFingersRowFiltering("fnVidmol.equals=" + DEFAULT_FN_VIDMOL, "fnVidmol.equals=" + UPDATED_FN_VIDMOL);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnVidmolIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnVidmol in
        defaultDFingersRowFiltering("fnVidmol.in=" + DEFAULT_FN_VIDMOL + "," + UPDATED_FN_VIDMOL, "fnVidmol.in=" + UPDATED_FN_VIDMOL);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnVidmolIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnVidmol is not null
        defaultDFingersRowFiltering("fnVidmol.specified=true", "fnVidmol.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnVidmolContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnVidmol contains
        defaultDFingersRowFiltering("fnVidmol.contains=" + DEFAULT_FN_VIDMOL, "fnVidmol.contains=" + UPDATED_FN_VIDMOL);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnVidmolNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnVidmol does not contain
        defaultDFingersRowFiltering("fnVidmol.doesNotContain=" + UPDATED_FN_VIDMOL, "fnVidmol.doesNotContain=" + DEFAULT_FN_VIDMOL);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDrugiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDrugi equals to
        defaultDFingersRowFiltering("fnDrugi.equals=" + DEFAULT_FN_DRUGI, "fnDrugi.equals=" + UPDATED_FN_DRUGI);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDrugiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDrugi in
        defaultDFingersRowFiltering("fnDrugi.in=" + DEFAULT_FN_DRUGI + "," + UPDATED_FN_DRUGI, "fnDrugi.in=" + UPDATED_FN_DRUGI);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDrugiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDrugi is not null
        defaultDFingersRowFiltering("fnDrugi.specified=true", "fnDrugi.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDrugiContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDrugi contains
        defaultDFingersRowFiltering("fnDrugi.contains=" + DEFAULT_FN_DRUGI, "fnDrugi.contains=" + UPDATED_FN_DRUGI);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDrugiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDrugi does not contain
        defaultDFingersRowFiltering("fnDrugi.doesNotContain=" + UPDATED_FN_DRUGI, "fnDrugi.doesNotContain=" + DEFAULT_FN_DRUGI);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid equals to
        defaultDFingersRowFiltering("fnDeviceid.equals=" + DEFAULT_FN_DEVICEID, "fnDeviceid.equals=" + UPDATED_FN_DEVICEID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid in
        defaultDFingersRowFiltering(
            "fnDeviceid.in=" + DEFAULT_FN_DEVICEID + "," + UPDATED_FN_DEVICEID,
            "fnDeviceid.in=" + UPDATED_FN_DEVICEID
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid is not null
        defaultDFingersRowFiltering("fnDeviceid.specified=true", "fnDeviceid.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid is greater than or equal to
        defaultDFingersRowFiltering(
            "fnDeviceid.greaterThanOrEqual=" + DEFAULT_FN_DEVICEID,
            "fnDeviceid.greaterThanOrEqual=" + UPDATED_FN_DEVICEID
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid is less than or equal to
        defaultDFingersRowFiltering(
            "fnDeviceid.lessThanOrEqual=" + DEFAULT_FN_DEVICEID,
            "fnDeviceid.lessThanOrEqual=" + SMALLER_FN_DEVICEID
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid is less than
        defaultDFingersRowFiltering("fnDeviceid.lessThan=" + UPDATED_FN_DEVICEID, "fnDeviceid.lessThan=" + DEFAULT_FN_DEVICEID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnDeviceidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnDeviceid is greater than
        defaultDFingersRowFiltering("fnDeviceid.greaterThan=" + SMALLER_FN_DEVICEID, "fnDeviceid.greaterThan=" + DEFAULT_FN_DEVICEID);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres equals to
        defaultDFingersRowFiltering("fnScanres.equals=" + DEFAULT_FN_SCANRES, "fnScanres.equals=" + UPDATED_FN_SCANRES);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres in
        defaultDFingersRowFiltering("fnScanres.in=" + DEFAULT_FN_SCANRES + "," + UPDATED_FN_SCANRES, "fnScanres.in=" + UPDATED_FN_SCANRES);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres is not null
        defaultDFingersRowFiltering("fnScanres.specified=true", "fnScanres.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres is greater than or equal to
        defaultDFingersRowFiltering(
            "fnScanres.greaterThanOrEqual=" + DEFAULT_FN_SCANRES,
            "fnScanres.greaterThanOrEqual=" + UPDATED_FN_SCANRES
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres is less than or equal to
        defaultDFingersRowFiltering("fnScanres.lessThanOrEqual=" + DEFAULT_FN_SCANRES, "fnScanres.lessThanOrEqual=" + SMALLER_FN_SCANRES);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres is less than
        defaultDFingersRowFiltering("fnScanres.lessThan=" + UPDATED_FN_SCANRES, "fnScanres.lessThan=" + DEFAULT_FN_SCANRES);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnScanresIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnScanres is greater than
        defaultDFingersRowFiltering("fnScanres.greaterThan=" + SMALLER_FN_SCANRES, "fnScanres.greaterThan=" + DEFAULT_FN_SCANRES);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth equals to
        defaultDFingersRowFiltering("fnWidth.equals=" + DEFAULT_FN_WIDTH, "fnWidth.equals=" + UPDATED_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth in
        defaultDFingersRowFiltering("fnWidth.in=" + DEFAULT_FN_WIDTH + "," + UPDATED_FN_WIDTH, "fnWidth.in=" + UPDATED_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth is not null
        defaultDFingersRowFiltering("fnWidth.specified=true", "fnWidth.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth is greater than or equal to
        defaultDFingersRowFiltering("fnWidth.greaterThanOrEqual=" + DEFAULT_FN_WIDTH, "fnWidth.greaterThanOrEqual=" + UPDATED_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth is less than or equal to
        defaultDFingersRowFiltering("fnWidth.lessThanOrEqual=" + DEFAULT_FN_WIDTH, "fnWidth.lessThanOrEqual=" + SMALLER_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth is less than
        defaultDFingersRowFiltering("fnWidth.lessThan=" + UPDATED_FN_WIDTH, "fnWidth.lessThan=" + DEFAULT_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnWidthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnWidth is greater than
        defaultDFingersRowFiltering("fnWidth.greaterThan=" + SMALLER_FN_WIDTH, "fnWidth.greaterThan=" + DEFAULT_FN_WIDTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight equals to
        defaultDFingersRowFiltering("fnHeight.equals=" + DEFAULT_FN_HEIGHT, "fnHeight.equals=" + UPDATED_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight in
        defaultDFingersRowFiltering("fnHeight.in=" + DEFAULT_FN_HEIGHT + "," + UPDATED_FN_HEIGHT, "fnHeight.in=" + UPDATED_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight is not null
        defaultDFingersRowFiltering("fnHeight.specified=true", "fnHeight.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight is greater than or equal to
        defaultDFingersRowFiltering("fnHeight.greaterThanOrEqual=" + DEFAULT_FN_HEIGHT, "fnHeight.greaterThanOrEqual=" + UPDATED_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight is less than or equal to
        defaultDFingersRowFiltering("fnHeight.lessThanOrEqual=" + DEFAULT_FN_HEIGHT, "fnHeight.lessThanOrEqual=" + SMALLER_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight is less than
        defaultDFingersRowFiltering("fnHeight.lessThan=" + UPDATED_FN_HEIGHT, "fnHeight.lessThan=" + DEFAULT_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnHeightIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnHeight is greater than
        defaultDFingersRowFiltering("fnHeight.greaterThan=" + SMALLER_FN_HEIGHT, "fnHeight.greaterThan=" + DEFAULT_FN_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth equals to
        defaultDFingersRowFiltering("fnPixeldepth.equals=" + DEFAULT_FN_PIXELDEPTH, "fnPixeldepth.equals=" + UPDATED_FN_PIXELDEPTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth in
        defaultDFingersRowFiltering(
            "fnPixeldepth.in=" + DEFAULT_FN_PIXELDEPTH + "," + UPDATED_FN_PIXELDEPTH,
            "fnPixeldepth.in=" + UPDATED_FN_PIXELDEPTH
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth is not null
        defaultDFingersRowFiltering("fnPixeldepth.specified=true", "fnPixeldepth.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth is greater than or equal to
        defaultDFingersRowFiltering(
            "fnPixeldepth.greaterThanOrEqual=" + DEFAULT_FN_PIXELDEPTH,
            "fnPixeldepth.greaterThanOrEqual=" + UPDATED_FN_PIXELDEPTH
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth is less than or equal to
        defaultDFingersRowFiltering(
            "fnPixeldepth.lessThanOrEqual=" + DEFAULT_FN_PIXELDEPTH,
            "fnPixeldepth.lessThanOrEqual=" + SMALLER_FN_PIXELDEPTH
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth is less than
        defaultDFingersRowFiltering("fnPixeldepth.lessThan=" + UPDATED_FN_PIXELDEPTH, "fnPixeldepth.lessThan=" + DEFAULT_FN_PIXELDEPTH);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnPixeldepthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnPixeldepth is greater than
        defaultDFingersRowFiltering(
            "fnPixeldepth.greaterThan=" + SMALLER_FN_PIXELDEPTH,
            "fnPixeldepth.greaterThan=" + DEFAULT_FN_PIXELDEPTH
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo equals to
        defaultDFingersRowFiltering("fnCompressalgo.equals=" + DEFAULT_FN_COMPRESSALGO, "fnCompressalgo.equals=" + UPDATED_FN_COMPRESSALGO);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo in
        defaultDFingersRowFiltering(
            "fnCompressalgo.in=" + DEFAULT_FN_COMPRESSALGO + "," + UPDATED_FN_COMPRESSALGO,
            "fnCompressalgo.in=" + UPDATED_FN_COMPRESSALGO
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo is not null
        defaultDFingersRowFiltering("fnCompressalgo.specified=true", "fnCompressalgo.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo is greater than or equal to
        defaultDFingersRowFiltering(
            "fnCompressalgo.greaterThanOrEqual=" + DEFAULT_FN_COMPRESSALGO,
            "fnCompressalgo.greaterThanOrEqual=" + UPDATED_FN_COMPRESSALGO
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo is less than or equal to
        defaultDFingersRowFiltering(
            "fnCompressalgo.lessThanOrEqual=" + DEFAULT_FN_COMPRESSALGO,
            "fnCompressalgo.lessThanOrEqual=" + SMALLER_FN_COMPRESSALGO
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo is less than
        defaultDFingersRowFiltering(
            "fnCompressalgo.lessThan=" + UPDATED_FN_COMPRESSALGO,
            "fnCompressalgo.lessThan=" + DEFAULT_FN_COMPRESSALGO
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnCompressalgoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnCompressalgo is greater than
        defaultDFingersRowFiltering(
            "fnCompressalgo.greaterThan=" + SMALLER_FN_COMPRESSALGO,
            "fnCompressalgo.greaterThan=" + DEFAULT_FN_COMPRESSALGO
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnFingerpositionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnFingerposition equals to
        defaultDFingersRowFiltering(
            "fnFingerposition.equals=" + DEFAULT_FN_FINGERPOSITION,
            "fnFingerposition.equals=" + UPDATED_FN_FINGERPOSITION
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnFingerpositionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnFingerposition in
        defaultDFingersRowFiltering(
            "fnFingerposition.in=" + DEFAULT_FN_FINGERPOSITION + "," + UPDATED_FN_FINGERPOSITION,
            "fnFingerposition.in=" + UPDATED_FN_FINGERPOSITION
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnFingerpositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnFingerposition is not null
        defaultDFingersRowFiltering("fnFingerposition.specified=true", "fnFingerposition.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnFingerpositionContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnFingerposition contains
        defaultDFingersRowFiltering(
            "fnFingerposition.contains=" + DEFAULT_FN_FINGERPOSITION,
            "fnFingerposition.contains=" + UPDATED_FN_FINGERPOSITION
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnFingerpositionNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnFingerposition does not contain
        defaultDFingersRowFiltering(
            "fnFingerposition.doesNotContain=" + UPDATED_FN_FINGERPOSITION,
            "fnFingerposition.doesNotContain=" + DEFAULT_FN_FINGERPOSITION
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality equals to
        defaultDFingersRowFiltering("fnImagequality.equals=" + DEFAULT_FN_IMAGEQUALITY, "fnImagequality.equals=" + UPDATED_FN_IMAGEQUALITY);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality in
        defaultDFingersRowFiltering(
            "fnImagequality.in=" + DEFAULT_FN_IMAGEQUALITY + "," + UPDATED_FN_IMAGEQUALITY,
            "fnImagequality.in=" + UPDATED_FN_IMAGEQUALITY
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality is not null
        defaultDFingersRowFiltering("fnImagequality.specified=true", "fnImagequality.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality is greater than or equal to
        defaultDFingersRowFiltering(
            "fnImagequality.greaterThanOrEqual=" + DEFAULT_FN_IMAGEQUALITY,
            "fnImagequality.greaterThanOrEqual=" + UPDATED_FN_IMAGEQUALITY
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality is less than or equal to
        defaultDFingersRowFiltering(
            "fnImagequality.lessThanOrEqual=" + DEFAULT_FN_IMAGEQUALITY,
            "fnImagequality.lessThanOrEqual=" + SMALLER_FN_IMAGEQUALITY
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality is less than
        defaultDFingersRowFiltering(
            "fnImagequality.lessThan=" + UPDATED_FN_IMAGEQUALITY,
            "fnImagequality.lessThan=" + DEFAULT_FN_IMAGEQUALITY
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImagequalityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImagequality is greater than
        defaultDFingersRowFiltering(
            "fnImagequality.greaterThan=" + SMALLER_FN_IMAGEQUALITY,
            "fnImagequality.greaterThan=" + DEFAULT_FN_IMAGEQUALITY
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImageIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImage equals to
        defaultDFingersRowFiltering("fnImage.equals=" + DEFAULT_FN_IMAGE, "fnImage.equals=" + UPDATED_FN_IMAGE);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImageIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImage in
        defaultDFingersRowFiltering("fnImage.in=" + DEFAULT_FN_IMAGE + "," + UPDATED_FN_IMAGE, "fnImage.in=" + UPDATED_FN_IMAGE);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImageIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImage is not null
        defaultDFingersRowFiltering("fnImage.specified=true", "fnImage.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImageContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImage contains
        defaultDFingersRowFiltering("fnImage.contains=" + DEFAULT_FN_IMAGE, "fnImage.contains=" + UPDATED_FN_IMAGE);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImageNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImage does not contain
        defaultDFingersRowFiltering("fnImage.doesNotContain=" + UPDATED_FN_IMAGE, "fnImage.doesNotContain=" + DEFAULT_FN_IMAGE);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen equals to
        defaultDFingersRowFiltering("fnImglen.equals=" + DEFAULT_FN_IMGLEN, "fnImglen.equals=" + UPDATED_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen in
        defaultDFingersRowFiltering("fnImglen.in=" + DEFAULT_FN_IMGLEN + "," + UPDATED_FN_IMGLEN, "fnImglen.in=" + UPDATED_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen is not null
        defaultDFingersRowFiltering("fnImglen.specified=true", "fnImglen.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen is greater than or equal to
        defaultDFingersRowFiltering("fnImglen.greaterThanOrEqual=" + DEFAULT_FN_IMGLEN, "fnImglen.greaterThanOrEqual=" + UPDATED_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen is less than or equal to
        defaultDFingersRowFiltering("fnImglen.lessThanOrEqual=" + DEFAULT_FN_IMGLEN, "fnImglen.lessThanOrEqual=" + SMALLER_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen is less than
        defaultDFingersRowFiltering("fnImglen.lessThan=" + UPDATED_FN_IMGLEN, "fnImglen.lessThan=" + DEFAULT_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnImglenIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnImglen is greater than
        defaultDFingersRowFiltering("fnImglen.greaterThan=" + SMALLER_FN_IMGLEN, "fnImglen.greaterThan=" + DEFAULT_FN_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnNottakenreasonIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnNottakenreason equals to
        defaultDFingersRowFiltering(
            "fnNottakenreason.equals=" + DEFAULT_FN_NOTTAKENREASON,
            "fnNottakenreason.equals=" + UPDATED_FN_NOTTAKENREASON
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnNottakenreasonIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnNottakenreason in
        defaultDFingersRowFiltering(
            "fnNottakenreason.in=" + DEFAULT_FN_NOTTAKENREASON + "," + UPDATED_FN_NOTTAKENREASON,
            "fnNottakenreason.in=" + UPDATED_FN_NOTTAKENREASON
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnNottakenreasonIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnNottakenreason is not null
        defaultDFingersRowFiltering("fnNottakenreason.specified=true", "fnNottakenreason.specified=false");
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnNottakenreasonContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnNottakenreason contains
        defaultDFingersRowFiltering(
            "fnNottakenreason.contains=" + DEFAULT_FN_NOTTAKENREASON,
            "fnNottakenreason.contains=" + UPDATED_FN_NOTTAKENREASON
        );
    }

    @Test
    @Transactional
    void getAllDFingersRowsByFnNottakenreasonNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        // Get all the dFingersRowList where fnNottakenreason does not contain
        defaultDFingersRowFiltering(
            "fnNottakenreason.doesNotContain=" + UPDATED_FN_NOTTAKENREASON,
            "fnNottakenreason.doesNotContain=" + DEFAULT_FN_NOTTAKENREASON
        );
    }

    private void defaultDFingersRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDFingersRowShouldBeFound(shouldBeFound);
        defaultDFingersRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDFingersRowShouldBeFound(String filter) throws Exception {
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFingersRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].fnDatreg").value(hasItem(DEFAULT_FN_DATREG.toString())))
            .andExpect(jsonPath("$.[*].fnDatvav").value(hasItem(DEFAULT_FN_DATVAV.toString())))
            .andExpect(jsonPath("$.[*].fnUsera").value(hasItem(DEFAULT_FN_USERA)))
            .andExpect(jsonPath("$.[*].fnSid").value(hasItem(DEFAULT_FN_SID)))
            .andExpect(jsonPath("$.[*].fnPnr").value(hasItem(DEFAULT_FN_PNR)))
            .andExpect(jsonPath("$.[*].fnVidmol").value(hasItem(DEFAULT_FN_VIDMOL)))
            .andExpect(jsonPath("$.[*].fnDrugi").value(hasItem(DEFAULT_FN_DRUGI)))
            .andExpect(jsonPath("$.[*].fnDeviceid").value(hasItem(DEFAULT_FN_DEVICEID)))
            .andExpect(jsonPath("$.[*].fnScanres").value(hasItem(DEFAULT_FN_SCANRES)))
            .andExpect(jsonPath("$.[*].fnWidth").value(hasItem(DEFAULT_FN_WIDTH)))
            .andExpect(jsonPath("$.[*].fnHeight").value(hasItem(DEFAULT_FN_HEIGHT)))
            .andExpect(jsonPath("$.[*].fnPixeldepth").value(hasItem(DEFAULT_FN_PIXELDEPTH)))
            .andExpect(jsonPath("$.[*].fnCompressalgo").value(hasItem(DEFAULT_FN_COMPRESSALGO)))
            .andExpect(jsonPath("$.[*].fnFingerposition").value(hasItem(DEFAULT_FN_FINGERPOSITION)))
            .andExpect(jsonPath("$.[*].fnImagequality").value(hasItem(DEFAULT_FN_IMAGEQUALITY)))
            .andExpect(jsonPath("$.[*].fnImage").value(hasItem(DEFAULT_FN_IMAGE)))
            .andExpect(jsonPath("$.[*].fnImglen").value(hasItem(DEFAULT_FN_IMGLEN)))
            .andExpect(jsonPath("$.[*].fnNottakenreason").value(hasItem(DEFAULT_FN_NOTTAKENREASON)));

        // Check, that the count call also returns 1
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDFingersRowShouldNotBeFound(String filter) throws Exception {
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDFingersRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDFingersRow() throws Exception {
        // Get the dFingersRow
        restDFingersRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDFingersRow() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dFingersRow
        DFingersRow updatedDFingersRow = dFingersRowRepository.findById(dFingersRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDFingersRow are not directly saved in db
        em.detach(updatedDFingersRow);
        updatedDFingersRow
            .fnDatreg(UPDATED_FN_DATREG)
            .fnDatvav(UPDATED_FN_DATVAV)
            .fnUsera(UPDATED_FN_USERA)
            .fnSid(UPDATED_FN_SID)
            .fnPnr(UPDATED_FN_PNR)
            .fnVidmol(UPDATED_FN_VIDMOL)
            .fnDrugi(UPDATED_FN_DRUGI)
            .fnDeviceid(UPDATED_FN_DEVICEID)
            .fnScanres(UPDATED_FN_SCANRES)
            .fnWidth(UPDATED_FN_WIDTH)
            .fnHeight(UPDATED_FN_HEIGHT)
            .fnPixeldepth(UPDATED_FN_PIXELDEPTH)
            .fnCompressalgo(UPDATED_FN_COMPRESSALGO)
            .fnFingerposition(UPDATED_FN_FINGERPOSITION)
            .fnImagequality(UPDATED_FN_IMAGEQUALITY)
            .fnImage(UPDATED_FN_IMAGE)
            .fnImglen(UPDATED_FN_IMGLEN)
            .fnNottakenreason(UPDATED_FN_NOTTAKENREASON);
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(updatedDFingersRow);

        restDFingersRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dFingersRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dFingersRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDFingersRowToMatchAllProperties(updatedDFingersRow);
    }

    @Test
    @Transactional
    void putNonExistingDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dFingersRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dFingersRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dFingersRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDFingersRowWithPatch() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dFingersRow using partial update
        DFingersRow partialUpdatedDFingersRow = new DFingersRow();
        partialUpdatedDFingersRow.setId(dFingersRow.getId());

        partialUpdatedDFingersRow
            .fnDatreg(UPDATED_FN_DATREG)
            .fnUsera(UPDATED_FN_USERA)
            .fnPnr(UPDATED_FN_PNR)
            .fnDrugi(UPDATED_FN_DRUGI)
            .fnScanres(UPDATED_FN_SCANRES)
            .fnWidth(UPDATED_FN_WIDTH)
            .fnFingerposition(UPDATED_FN_FINGERPOSITION)
            .fnImagequality(UPDATED_FN_IMAGEQUALITY)
            .fnNottakenreason(UPDATED_FN_NOTTAKENREASON);

        restDFingersRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDFingersRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDFingersRow))
            )
            .andExpect(status().isOk());

        // Validate the DFingersRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDFingersRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDFingersRow, dFingersRow),
            getPersistedDFingersRow(dFingersRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDFingersRowWithPatch() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dFingersRow using partial update
        DFingersRow partialUpdatedDFingersRow = new DFingersRow();
        partialUpdatedDFingersRow.setId(dFingersRow.getId());

        partialUpdatedDFingersRow
            .fnDatreg(UPDATED_FN_DATREG)
            .fnDatvav(UPDATED_FN_DATVAV)
            .fnUsera(UPDATED_FN_USERA)
            .fnSid(UPDATED_FN_SID)
            .fnPnr(UPDATED_FN_PNR)
            .fnVidmol(UPDATED_FN_VIDMOL)
            .fnDrugi(UPDATED_FN_DRUGI)
            .fnDeviceid(UPDATED_FN_DEVICEID)
            .fnScanres(UPDATED_FN_SCANRES)
            .fnWidth(UPDATED_FN_WIDTH)
            .fnHeight(UPDATED_FN_HEIGHT)
            .fnPixeldepth(UPDATED_FN_PIXELDEPTH)
            .fnCompressalgo(UPDATED_FN_COMPRESSALGO)
            .fnFingerposition(UPDATED_FN_FINGERPOSITION)
            .fnImagequality(UPDATED_FN_IMAGEQUALITY)
            .fnImage(UPDATED_FN_IMAGE)
            .fnImglen(UPDATED_FN_IMGLEN)
            .fnNottakenreason(UPDATED_FN_NOTTAKENREASON);

        restDFingersRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDFingersRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDFingersRow))
            )
            .andExpect(status().isOk());

        // Validate the DFingersRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDFingersRowUpdatableFieldsEquals(partialUpdatedDFingersRow, getPersistedDFingersRow(partialUpdatedDFingersRow));
    }

    @Test
    @Transactional
    void patchNonExistingDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dFingersRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dFingersRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dFingersRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDFingersRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dFingersRow.setId(longCount.incrementAndGet());

        // Create the DFingersRow
        DFingersRowDTO dFingersRowDTO = dFingersRowMapper.toDto(dFingersRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDFingersRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dFingersRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DFingersRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDFingersRow() throws Exception {
        // Initialize the database
        insertedDFingersRow = dFingersRowRepository.saveAndFlush(dFingersRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dFingersRow
        restDFingersRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dFingersRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dFingersRowRepository.count();
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

    protected DFingersRow getPersistedDFingersRow(DFingersRow dFingersRow) {
        return dFingersRowRepository.findById(dFingersRow.getId()).orElseThrow();
    }

    protected void assertPersistedDFingersRowToMatchAllProperties(DFingersRow expectedDFingersRow) {
        assertDFingersRowAllPropertiesEquals(expectedDFingersRow, getPersistedDFingersRow(expectedDFingersRow));
    }

    protected void assertPersistedDFingersRowToMatchUpdatableProperties(DFingersRow expectedDFingersRow) {
        assertDFingersRowAllUpdatablePropertiesEquals(expectedDFingersRow, getPersistedDFingersRow(expectedDFingersRow));
    }
}
