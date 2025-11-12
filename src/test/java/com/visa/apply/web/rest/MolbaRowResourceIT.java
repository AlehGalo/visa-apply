package com.visa.apply.web.rest;

import static com.visa.apply.domain.MolbaRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.MolbaRow;
import com.visa.apply.repository.MolbaRowRepository;
import com.visa.apply.service.dto.MolbaRowDTO;
import com.visa.apply.service.mapper.MolbaRowMapper;
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
 * Integration tests for the {@link MolbaRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MolbaRowResourceIT {

    private static final LocalDate DEFAULT_DAT_VLI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_VLI = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DAT_VLI = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DAT_IZL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_IZL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DAT_IZL = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_VIDVIS = "AAAAAAAAAA";
    private static final String UPDATED_VIDVIS = "BBBBBBBBBB";

    private static final Integer DEFAULT_BRVL = 1;
    private static final Integer UPDATED_BRVL = 2;
    private static final Integer SMALLER_BRVL = 1 - 1;

    private static final String DEFAULT_VIDUS = "AAAAAAAAAA";
    private static final String UPDATED_VIDUS = "BBBBBBBBBB";

    private static final String DEFAULT_VALVIS = "AAAAAAAAAA";
    private static final String UPDATED_VALVIS = "BBBBBBBBBB";

    private static final Integer DEFAULT_BRDNI = 1;
    private static final Integer UPDATED_BRDNI = 2;
    private static final Integer SMALLER_BRDNI = 1 - 1;

    private static final Integer DEFAULT_CEL = 1;
    private static final Integer UPDATED_CEL = 2;
    private static final Integer SMALLER_CEL = 1 - 1;

    private static final Instant DEFAULT_MOL_DAT_VAV = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MOL_DAT_VAV = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GRATIS = "AAAAAAAAAA";
    private static final String UPDATED_GRATIS = "BBBBBBBBBB";

    private static final String DEFAULT_IMAVISA = "AAAAAAAAAA";
    private static final String UPDATED_IMAVISA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CENAMOL = 1;
    private static final Integer UPDATED_CENAMOL = 2;
    private static final Integer SMALLER_CENAMOL = 1 - 1;

    private static final String DEFAULT_CENACURR = "AAAAAAAAAA";
    private static final String UPDATED_CENACURR = "BBBBBBBBBB";

    private static final String DEFAULT_MAINDEST = "AAAAAAAAAA";
    private static final String UPDATED_MAINDEST = "BBBBBBBBBB";

    private static final String DEFAULT_MAINDESTNM = "AAAAAAAAAA";
    private static final String UPDATED_MAINDESTNM = "BBBBBBBBBB";

    private static final String DEFAULT_GKPP_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_GKPP_DARJ = "BBBBBBBBBB";

    private static final String DEFAULT_GKPP_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_GKPP_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT_INI = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_INI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/molba-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MolbaRowRepository molbaRowRepository;

    @Autowired
    private MolbaRowMapper molbaRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMolbaRowMockMvc;

    private MolbaRow molbaRow;

    private MolbaRow insertedMolbaRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MolbaRow createEntity() {
        return new MolbaRow()
            .datVli(DEFAULT_DAT_VLI)
            .datIzl(DEFAULT_DAT_IZL)
            .vidvis(DEFAULT_VIDVIS)
            .brvl(DEFAULT_BRVL)
            .vidus(DEFAULT_VIDUS)
            .valvis(DEFAULT_VALVIS)
            .brdni(DEFAULT_BRDNI)
            .cel(DEFAULT_CEL)
            .molDatVav(DEFAULT_MOL_DAT_VAV)
            .gratis(DEFAULT_GRATIS)
            .imavisa(DEFAULT_IMAVISA)
            .cenamol(DEFAULT_CENAMOL)
            .cenacurr(DEFAULT_CENACURR)
            .maindest(DEFAULT_MAINDEST)
            .maindestnm(DEFAULT_MAINDESTNM)
            .gkppDarj(DEFAULT_GKPP_DARJ)
            .gkppText(DEFAULT_GKPP_TEXT)
            .textIni(DEFAULT_TEXT_INI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MolbaRow createUpdatedEntity() {
        return new MolbaRow()
            .datVli(UPDATED_DAT_VLI)
            .datIzl(UPDATED_DAT_IZL)
            .vidvis(UPDATED_VIDVIS)
            .brvl(UPDATED_BRVL)
            .vidus(UPDATED_VIDUS)
            .valvis(UPDATED_VALVIS)
            .brdni(UPDATED_BRDNI)
            .cel(UPDATED_CEL)
            .molDatVav(UPDATED_MOL_DAT_VAV)
            .gratis(UPDATED_GRATIS)
            .imavisa(UPDATED_IMAVISA)
            .cenamol(UPDATED_CENAMOL)
            .cenacurr(UPDATED_CENACURR)
            .maindest(UPDATED_MAINDEST)
            .maindestnm(UPDATED_MAINDESTNM)
            .gkppDarj(UPDATED_GKPP_DARJ)
            .gkppText(UPDATED_GKPP_TEXT)
            .textIni(UPDATED_TEXT_INI);
    }

    @BeforeEach
    void initTest() {
        molbaRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedMolbaRow != null) {
            molbaRowRepository.delete(insertedMolbaRow);
            insertedMolbaRow = null;
        }
    }

    @Test
    @Transactional
    void createMolbaRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);
        var returnedMolbaRowDTO = om.readValue(
            restMolbaRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MolbaRowDTO.class
        );

        // Validate the MolbaRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedMolbaRow = molbaRowMapper.toEntity(returnedMolbaRowDTO);
        assertMolbaRowUpdatableFieldsEquals(returnedMolbaRow, getPersistedMolbaRow(returnedMolbaRow));

        insertedMolbaRow = returnedMolbaRow;
    }

    @Test
    @Transactional
    void createMolbaRowWithExistingId() throws Exception {
        // Create the MolbaRow with an existing ID
        molbaRow.setId(1L);
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDatVliIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setDatVli(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDatIzlIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setDatIzl(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVidvisIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setVidvis(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVidusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setVidus(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValvisIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setValvis(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMolDatVavIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setMolDatVav(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGratisIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setGratis(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkImavisaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setImavisa(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCenacurrIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setCenacurr(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaindestIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setMaindest(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaindestnmIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setMaindestnm(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGkppDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setGkppDarj(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGkppTextIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setGkppText(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTextIniIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        molbaRow.setTextIni(null);

        // Create the MolbaRow, which fails.
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        restMolbaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMolbaRows() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(molbaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].datVli").value(hasItem(DEFAULT_DAT_VLI.toString())))
            .andExpect(jsonPath("$.[*].datIzl").value(hasItem(DEFAULT_DAT_IZL.toString())))
            .andExpect(jsonPath("$.[*].vidvis").value(hasItem(DEFAULT_VIDVIS)))
            .andExpect(jsonPath("$.[*].brvl").value(hasItem(DEFAULT_BRVL)))
            .andExpect(jsonPath("$.[*].vidus").value(hasItem(DEFAULT_VIDUS)))
            .andExpect(jsonPath("$.[*].valvis").value(hasItem(DEFAULT_VALVIS)))
            .andExpect(jsonPath("$.[*].brdni").value(hasItem(DEFAULT_BRDNI)))
            .andExpect(jsonPath("$.[*].cel").value(hasItem(DEFAULT_CEL)))
            .andExpect(jsonPath("$.[*].molDatVav").value(hasItem(DEFAULT_MOL_DAT_VAV.toString())))
            .andExpect(jsonPath("$.[*].gratis").value(hasItem(DEFAULT_GRATIS)))
            .andExpect(jsonPath("$.[*].imavisa").value(hasItem(DEFAULT_IMAVISA)))
            .andExpect(jsonPath("$.[*].cenamol").value(hasItem(DEFAULT_CENAMOL)))
            .andExpect(jsonPath("$.[*].cenacurr").value(hasItem(DEFAULT_CENACURR)))
            .andExpect(jsonPath("$.[*].maindest").value(hasItem(DEFAULT_MAINDEST)))
            .andExpect(jsonPath("$.[*].maindestnm").value(hasItem(DEFAULT_MAINDESTNM)))
            .andExpect(jsonPath("$.[*].gkppDarj").value(hasItem(DEFAULT_GKPP_DARJ)))
            .andExpect(jsonPath("$.[*].gkppText").value(hasItem(DEFAULT_GKPP_TEXT)))
            .andExpect(jsonPath("$.[*].textIni").value(hasItem(DEFAULT_TEXT_INI)));
    }

    @Test
    @Transactional
    void getMolbaRow() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get the molbaRow
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL_ID, molbaRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(molbaRow.getId().intValue()))
            .andExpect(jsonPath("$.datVli").value(DEFAULT_DAT_VLI.toString()))
            .andExpect(jsonPath("$.datIzl").value(DEFAULT_DAT_IZL.toString()))
            .andExpect(jsonPath("$.vidvis").value(DEFAULT_VIDVIS))
            .andExpect(jsonPath("$.brvl").value(DEFAULT_BRVL))
            .andExpect(jsonPath("$.vidus").value(DEFAULT_VIDUS))
            .andExpect(jsonPath("$.valvis").value(DEFAULT_VALVIS))
            .andExpect(jsonPath("$.brdni").value(DEFAULT_BRDNI))
            .andExpect(jsonPath("$.cel").value(DEFAULT_CEL))
            .andExpect(jsonPath("$.molDatVav").value(DEFAULT_MOL_DAT_VAV.toString()))
            .andExpect(jsonPath("$.gratis").value(DEFAULT_GRATIS))
            .andExpect(jsonPath("$.imavisa").value(DEFAULT_IMAVISA))
            .andExpect(jsonPath("$.cenamol").value(DEFAULT_CENAMOL))
            .andExpect(jsonPath("$.cenacurr").value(DEFAULT_CENACURR))
            .andExpect(jsonPath("$.maindest").value(DEFAULT_MAINDEST))
            .andExpect(jsonPath("$.maindestnm").value(DEFAULT_MAINDESTNM))
            .andExpect(jsonPath("$.gkppDarj").value(DEFAULT_GKPP_DARJ))
            .andExpect(jsonPath("$.gkppText").value(DEFAULT_GKPP_TEXT))
            .andExpect(jsonPath("$.textIni").value(DEFAULT_TEXT_INI));
    }

    @Test
    @Transactional
    void getMolbaRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        Long id = molbaRow.getId();

        defaultMolbaRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultMolbaRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultMolbaRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli equals to
        defaultMolbaRowFiltering("datVli.equals=" + DEFAULT_DAT_VLI, "datVli.equals=" + UPDATED_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli in
        defaultMolbaRowFiltering("datVli.in=" + DEFAULT_DAT_VLI + "," + UPDATED_DAT_VLI, "datVli.in=" + UPDATED_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli is not null
        defaultMolbaRowFiltering("datVli.specified=true", "datVli.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli is greater than or equal to
        defaultMolbaRowFiltering("datVli.greaterThanOrEqual=" + DEFAULT_DAT_VLI, "datVli.greaterThanOrEqual=" + UPDATED_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli is less than or equal to
        defaultMolbaRowFiltering("datVli.lessThanOrEqual=" + DEFAULT_DAT_VLI, "datVli.lessThanOrEqual=" + SMALLER_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli is less than
        defaultMolbaRowFiltering("datVli.lessThan=" + UPDATED_DAT_VLI, "datVli.lessThan=" + DEFAULT_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatVliIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datVli is greater than
        defaultMolbaRowFiltering("datVli.greaterThan=" + SMALLER_DAT_VLI, "datVli.greaterThan=" + DEFAULT_DAT_VLI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl equals to
        defaultMolbaRowFiltering("datIzl.equals=" + DEFAULT_DAT_IZL, "datIzl.equals=" + UPDATED_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl in
        defaultMolbaRowFiltering("datIzl.in=" + DEFAULT_DAT_IZL + "," + UPDATED_DAT_IZL, "datIzl.in=" + UPDATED_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl is not null
        defaultMolbaRowFiltering("datIzl.specified=true", "datIzl.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl is greater than or equal to
        defaultMolbaRowFiltering("datIzl.greaterThanOrEqual=" + DEFAULT_DAT_IZL, "datIzl.greaterThanOrEqual=" + UPDATED_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl is less than or equal to
        defaultMolbaRowFiltering("datIzl.lessThanOrEqual=" + DEFAULT_DAT_IZL, "datIzl.lessThanOrEqual=" + SMALLER_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl is less than
        defaultMolbaRowFiltering("datIzl.lessThan=" + UPDATED_DAT_IZL, "datIzl.lessThan=" + DEFAULT_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByDatIzlIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where datIzl is greater than
        defaultMolbaRowFiltering("datIzl.greaterThan=" + SMALLER_DAT_IZL, "datIzl.greaterThan=" + DEFAULT_DAT_IZL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidvisIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidvis equals to
        defaultMolbaRowFiltering("vidvis.equals=" + DEFAULT_VIDVIS, "vidvis.equals=" + UPDATED_VIDVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidvisIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidvis in
        defaultMolbaRowFiltering("vidvis.in=" + DEFAULT_VIDVIS + "," + UPDATED_VIDVIS, "vidvis.in=" + UPDATED_VIDVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidvisIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidvis is not null
        defaultMolbaRowFiltering("vidvis.specified=true", "vidvis.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidvisContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidvis contains
        defaultMolbaRowFiltering("vidvis.contains=" + DEFAULT_VIDVIS, "vidvis.contains=" + UPDATED_VIDVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidvisNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidvis does not contain
        defaultMolbaRowFiltering("vidvis.doesNotContain=" + UPDATED_VIDVIS, "vidvis.doesNotContain=" + DEFAULT_VIDVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl equals to
        defaultMolbaRowFiltering("brvl.equals=" + DEFAULT_BRVL, "brvl.equals=" + UPDATED_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl in
        defaultMolbaRowFiltering("brvl.in=" + DEFAULT_BRVL + "," + UPDATED_BRVL, "brvl.in=" + UPDATED_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl is not null
        defaultMolbaRowFiltering("brvl.specified=true", "brvl.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl is greater than or equal to
        defaultMolbaRowFiltering("brvl.greaterThanOrEqual=" + DEFAULT_BRVL, "brvl.greaterThanOrEqual=" + UPDATED_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl is less than or equal to
        defaultMolbaRowFiltering("brvl.lessThanOrEqual=" + DEFAULT_BRVL, "brvl.lessThanOrEqual=" + SMALLER_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl is less than
        defaultMolbaRowFiltering("brvl.lessThan=" + UPDATED_BRVL, "brvl.lessThan=" + DEFAULT_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrvlIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brvl is greater than
        defaultMolbaRowFiltering("brvl.greaterThan=" + SMALLER_BRVL, "brvl.greaterThan=" + DEFAULT_BRVL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidus equals to
        defaultMolbaRowFiltering("vidus.equals=" + DEFAULT_VIDUS, "vidus.equals=" + UPDATED_VIDUS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidus in
        defaultMolbaRowFiltering("vidus.in=" + DEFAULT_VIDUS + "," + UPDATED_VIDUS, "vidus.in=" + UPDATED_VIDUS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidus is not null
        defaultMolbaRowFiltering("vidus.specified=true", "vidus.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidusContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidus contains
        defaultMolbaRowFiltering("vidus.contains=" + DEFAULT_VIDUS, "vidus.contains=" + UPDATED_VIDUS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByVidusNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where vidus does not contain
        defaultMolbaRowFiltering("vidus.doesNotContain=" + UPDATED_VIDUS, "vidus.doesNotContain=" + DEFAULT_VIDUS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByValvisIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where valvis equals to
        defaultMolbaRowFiltering("valvis.equals=" + DEFAULT_VALVIS, "valvis.equals=" + UPDATED_VALVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByValvisIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where valvis in
        defaultMolbaRowFiltering("valvis.in=" + DEFAULT_VALVIS + "," + UPDATED_VALVIS, "valvis.in=" + UPDATED_VALVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByValvisIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where valvis is not null
        defaultMolbaRowFiltering("valvis.specified=true", "valvis.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByValvisContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where valvis contains
        defaultMolbaRowFiltering("valvis.contains=" + DEFAULT_VALVIS, "valvis.contains=" + UPDATED_VALVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByValvisNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where valvis does not contain
        defaultMolbaRowFiltering("valvis.doesNotContain=" + UPDATED_VALVIS, "valvis.doesNotContain=" + DEFAULT_VALVIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni equals to
        defaultMolbaRowFiltering("brdni.equals=" + DEFAULT_BRDNI, "brdni.equals=" + UPDATED_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni in
        defaultMolbaRowFiltering("brdni.in=" + DEFAULT_BRDNI + "," + UPDATED_BRDNI, "brdni.in=" + UPDATED_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni is not null
        defaultMolbaRowFiltering("brdni.specified=true", "brdni.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni is greater than or equal to
        defaultMolbaRowFiltering("brdni.greaterThanOrEqual=" + DEFAULT_BRDNI, "brdni.greaterThanOrEqual=" + UPDATED_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni is less than or equal to
        defaultMolbaRowFiltering("brdni.lessThanOrEqual=" + DEFAULT_BRDNI, "brdni.lessThanOrEqual=" + SMALLER_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni is less than
        defaultMolbaRowFiltering("brdni.lessThan=" + UPDATED_BRDNI, "brdni.lessThan=" + DEFAULT_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByBrdniIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where brdni is greater than
        defaultMolbaRowFiltering("brdni.greaterThan=" + SMALLER_BRDNI, "brdni.greaterThan=" + DEFAULT_BRDNI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel equals to
        defaultMolbaRowFiltering("cel.equals=" + DEFAULT_CEL, "cel.equals=" + UPDATED_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel in
        defaultMolbaRowFiltering("cel.in=" + DEFAULT_CEL + "," + UPDATED_CEL, "cel.in=" + UPDATED_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel is not null
        defaultMolbaRowFiltering("cel.specified=true", "cel.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel is greater than or equal to
        defaultMolbaRowFiltering("cel.greaterThanOrEqual=" + DEFAULT_CEL, "cel.greaterThanOrEqual=" + UPDATED_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel is less than or equal to
        defaultMolbaRowFiltering("cel.lessThanOrEqual=" + DEFAULT_CEL, "cel.lessThanOrEqual=" + SMALLER_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel is less than
        defaultMolbaRowFiltering("cel.lessThan=" + UPDATED_CEL, "cel.lessThan=" + DEFAULT_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cel is greater than
        defaultMolbaRowFiltering("cel.greaterThan=" + SMALLER_CEL, "cel.greaterThan=" + DEFAULT_CEL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMolDatVavIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where molDatVav equals to
        defaultMolbaRowFiltering("molDatVav.equals=" + DEFAULT_MOL_DAT_VAV, "molDatVav.equals=" + UPDATED_MOL_DAT_VAV);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMolDatVavIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where molDatVav in
        defaultMolbaRowFiltering("molDatVav.in=" + DEFAULT_MOL_DAT_VAV + "," + UPDATED_MOL_DAT_VAV, "molDatVav.in=" + UPDATED_MOL_DAT_VAV);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMolDatVavIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where molDatVav is not null
        defaultMolbaRowFiltering("molDatVav.specified=true", "molDatVav.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGratisIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gratis equals to
        defaultMolbaRowFiltering("gratis.equals=" + DEFAULT_GRATIS, "gratis.equals=" + UPDATED_GRATIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGratisIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gratis in
        defaultMolbaRowFiltering("gratis.in=" + DEFAULT_GRATIS + "," + UPDATED_GRATIS, "gratis.in=" + UPDATED_GRATIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGratisIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gratis is not null
        defaultMolbaRowFiltering("gratis.specified=true", "gratis.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGratisContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gratis contains
        defaultMolbaRowFiltering("gratis.contains=" + DEFAULT_GRATIS, "gratis.contains=" + UPDATED_GRATIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGratisNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gratis does not contain
        defaultMolbaRowFiltering("gratis.doesNotContain=" + UPDATED_GRATIS, "gratis.doesNotContain=" + DEFAULT_GRATIS);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByImavisaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where imavisa equals to
        defaultMolbaRowFiltering("imavisa.equals=" + DEFAULT_IMAVISA, "imavisa.equals=" + UPDATED_IMAVISA);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByImavisaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where imavisa in
        defaultMolbaRowFiltering("imavisa.in=" + DEFAULT_IMAVISA + "," + UPDATED_IMAVISA, "imavisa.in=" + UPDATED_IMAVISA);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByImavisaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where imavisa is not null
        defaultMolbaRowFiltering("imavisa.specified=true", "imavisa.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByImavisaContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where imavisa contains
        defaultMolbaRowFiltering("imavisa.contains=" + DEFAULT_IMAVISA, "imavisa.contains=" + UPDATED_IMAVISA);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByImavisaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where imavisa does not contain
        defaultMolbaRowFiltering("imavisa.doesNotContain=" + UPDATED_IMAVISA, "imavisa.doesNotContain=" + DEFAULT_IMAVISA);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol equals to
        defaultMolbaRowFiltering("cenamol.equals=" + DEFAULT_CENAMOL, "cenamol.equals=" + UPDATED_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol in
        defaultMolbaRowFiltering("cenamol.in=" + DEFAULT_CENAMOL + "," + UPDATED_CENAMOL, "cenamol.in=" + UPDATED_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol is not null
        defaultMolbaRowFiltering("cenamol.specified=true", "cenamol.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol is greater than or equal to
        defaultMolbaRowFiltering("cenamol.greaterThanOrEqual=" + DEFAULT_CENAMOL, "cenamol.greaterThanOrEqual=" + UPDATED_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol is less than or equal to
        defaultMolbaRowFiltering("cenamol.lessThanOrEqual=" + DEFAULT_CENAMOL, "cenamol.lessThanOrEqual=" + SMALLER_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol is less than
        defaultMolbaRowFiltering("cenamol.lessThan=" + UPDATED_CENAMOL, "cenamol.lessThan=" + DEFAULT_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenamolIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenamol is greater than
        defaultMolbaRowFiltering("cenamol.greaterThan=" + SMALLER_CENAMOL, "cenamol.greaterThan=" + DEFAULT_CENAMOL);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenacurrIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenacurr equals to
        defaultMolbaRowFiltering("cenacurr.equals=" + DEFAULT_CENACURR, "cenacurr.equals=" + UPDATED_CENACURR);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenacurrIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenacurr in
        defaultMolbaRowFiltering("cenacurr.in=" + DEFAULT_CENACURR + "," + UPDATED_CENACURR, "cenacurr.in=" + UPDATED_CENACURR);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenacurrIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenacurr is not null
        defaultMolbaRowFiltering("cenacurr.specified=true", "cenacurr.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenacurrContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenacurr contains
        defaultMolbaRowFiltering("cenacurr.contains=" + DEFAULT_CENACURR, "cenacurr.contains=" + UPDATED_CENACURR);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByCenacurrNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where cenacurr does not contain
        defaultMolbaRowFiltering("cenacurr.doesNotContain=" + UPDATED_CENACURR, "cenacurr.doesNotContain=" + DEFAULT_CENACURR);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindest equals to
        defaultMolbaRowFiltering("maindest.equals=" + DEFAULT_MAINDEST, "maindest.equals=" + UPDATED_MAINDEST);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindest in
        defaultMolbaRowFiltering("maindest.in=" + DEFAULT_MAINDEST + "," + UPDATED_MAINDEST, "maindest.in=" + UPDATED_MAINDEST);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindest is not null
        defaultMolbaRowFiltering("maindest.specified=true", "maindest.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindest contains
        defaultMolbaRowFiltering("maindest.contains=" + DEFAULT_MAINDEST, "maindest.contains=" + UPDATED_MAINDEST);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindest does not contain
        defaultMolbaRowFiltering("maindest.doesNotContain=" + UPDATED_MAINDEST, "maindest.doesNotContain=" + DEFAULT_MAINDEST);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestnmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindestnm equals to
        defaultMolbaRowFiltering("maindestnm.equals=" + DEFAULT_MAINDESTNM, "maindestnm.equals=" + UPDATED_MAINDESTNM);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestnmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindestnm in
        defaultMolbaRowFiltering("maindestnm.in=" + DEFAULT_MAINDESTNM + "," + UPDATED_MAINDESTNM, "maindestnm.in=" + UPDATED_MAINDESTNM);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestnmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindestnm is not null
        defaultMolbaRowFiltering("maindestnm.specified=true", "maindestnm.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestnmContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindestnm contains
        defaultMolbaRowFiltering("maindestnm.contains=" + DEFAULT_MAINDESTNM, "maindestnm.contains=" + UPDATED_MAINDESTNM);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByMaindestnmNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where maindestnm does not contain
        defaultMolbaRowFiltering("maindestnm.doesNotContain=" + UPDATED_MAINDESTNM, "maindestnm.doesNotContain=" + DEFAULT_MAINDESTNM);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppDarj equals to
        defaultMolbaRowFiltering("gkppDarj.equals=" + DEFAULT_GKPP_DARJ, "gkppDarj.equals=" + UPDATED_GKPP_DARJ);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppDarj in
        defaultMolbaRowFiltering("gkppDarj.in=" + DEFAULT_GKPP_DARJ + "," + UPDATED_GKPP_DARJ, "gkppDarj.in=" + UPDATED_GKPP_DARJ);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppDarj is not null
        defaultMolbaRowFiltering("gkppDarj.specified=true", "gkppDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppDarj contains
        defaultMolbaRowFiltering("gkppDarj.contains=" + DEFAULT_GKPP_DARJ, "gkppDarj.contains=" + UPDATED_GKPP_DARJ);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppDarj does not contain
        defaultMolbaRowFiltering("gkppDarj.doesNotContain=" + UPDATED_GKPP_DARJ, "gkppDarj.doesNotContain=" + DEFAULT_GKPP_DARJ);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppTextIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppText equals to
        defaultMolbaRowFiltering("gkppText.equals=" + DEFAULT_GKPP_TEXT, "gkppText.equals=" + UPDATED_GKPP_TEXT);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppTextIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppText in
        defaultMolbaRowFiltering("gkppText.in=" + DEFAULT_GKPP_TEXT + "," + UPDATED_GKPP_TEXT, "gkppText.in=" + UPDATED_GKPP_TEXT);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppTextIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppText is not null
        defaultMolbaRowFiltering("gkppText.specified=true", "gkppText.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppTextContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppText contains
        defaultMolbaRowFiltering("gkppText.contains=" + DEFAULT_GKPP_TEXT, "gkppText.contains=" + UPDATED_GKPP_TEXT);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByGkppTextNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where gkppText does not contain
        defaultMolbaRowFiltering("gkppText.doesNotContain=" + UPDATED_GKPP_TEXT, "gkppText.doesNotContain=" + DEFAULT_GKPP_TEXT);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByTextIniIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where textIni equals to
        defaultMolbaRowFiltering("textIni.equals=" + DEFAULT_TEXT_INI, "textIni.equals=" + UPDATED_TEXT_INI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByTextIniIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where textIni in
        defaultMolbaRowFiltering("textIni.in=" + DEFAULT_TEXT_INI + "," + UPDATED_TEXT_INI, "textIni.in=" + UPDATED_TEXT_INI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByTextIniIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where textIni is not null
        defaultMolbaRowFiltering("textIni.specified=true", "textIni.specified=false");
    }

    @Test
    @Transactional
    void getAllMolbaRowsByTextIniContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where textIni contains
        defaultMolbaRowFiltering("textIni.contains=" + DEFAULT_TEXT_INI, "textIni.contains=" + UPDATED_TEXT_INI);
    }

    @Test
    @Transactional
    void getAllMolbaRowsByTextIniNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        // Get all the molbaRowList where textIni does not contain
        defaultMolbaRowFiltering("textIni.doesNotContain=" + UPDATED_TEXT_INI, "textIni.doesNotContain=" + DEFAULT_TEXT_INI);
    }

    private void defaultMolbaRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultMolbaRowShouldBeFound(shouldBeFound);
        defaultMolbaRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMolbaRowShouldBeFound(String filter) throws Exception {
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(molbaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].datVli").value(hasItem(DEFAULT_DAT_VLI.toString())))
            .andExpect(jsonPath("$.[*].datIzl").value(hasItem(DEFAULT_DAT_IZL.toString())))
            .andExpect(jsonPath("$.[*].vidvis").value(hasItem(DEFAULT_VIDVIS)))
            .andExpect(jsonPath("$.[*].brvl").value(hasItem(DEFAULT_BRVL)))
            .andExpect(jsonPath("$.[*].vidus").value(hasItem(DEFAULT_VIDUS)))
            .andExpect(jsonPath("$.[*].valvis").value(hasItem(DEFAULT_VALVIS)))
            .andExpect(jsonPath("$.[*].brdni").value(hasItem(DEFAULT_BRDNI)))
            .andExpect(jsonPath("$.[*].cel").value(hasItem(DEFAULT_CEL)))
            .andExpect(jsonPath("$.[*].molDatVav").value(hasItem(DEFAULT_MOL_DAT_VAV.toString())))
            .andExpect(jsonPath("$.[*].gratis").value(hasItem(DEFAULT_GRATIS)))
            .andExpect(jsonPath("$.[*].imavisa").value(hasItem(DEFAULT_IMAVISA)))
            .andExpect(jsonPath("$.[*].cenamol").value(hasItem(DEFAULT_CENAMOL)))
            .andExpect(jsonPath("$.[*].cenacurr").value(hasItem(DEFAULT_CENACURR)))
            .andExpect(jsonPath("$.[*].maindest").value(hasItem(DEFAULT_MAINDEST)))
            .andExpect(jsonPath("$.[*].maindestnm").value(hasItem(DEFAULT_MAINDESTNM)))
            .andExpect(jsonPath("$.[*].gkppDarj").value(hasItem(DEFAULT_GKPP_DARJ)))
            .andExpect(jsonPath("$.[*].gkppText").value(hasItem(DEFAULT_GKPP_TEXT)))
            .andExpect(jsonPath("$.[*].textIni").value(hasItem(DEFAULT_TEXT_INI)));

        // Check, that the count call also returns 1
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMolbaRowShouldNotBeFound(String filter) throws Exception {
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMolbaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingMolbaRow() throws Exception {
        // Get the molbaRow
        restMolbaRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMolbaRow() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the molbaRow
        MolbaRow updatedMolbaRow = molbaRowRepository.findById(molbaRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMolbaRow are not directly saved in db
        em.detach(updatedMolbaRow);
        updatedMolbaRow
            .datVli(UPDATED_DAT_VLI)
            .datIzl(UPDATED_DAT_IZL)
            .vidvis(UPDATED_VIDVIS)
            .brvl(UPDATED_BRVL)
            .vidus(UPDATED_VIDUS)
            .valvis(UPDATED_VALVIS)
            .brdni(UPDATED_BRDNI)
            .cel(UPDATED_CEL)
            .molDatVav(UPDATED_MOL_DAT_VAV)
            .gratis(UPDATED_GRATIS)
            .imavisa(UPDATED_IMAVISA)
            .cenamol(UPDATED_CENAMOL)
            .cenacurr(UPDATED_CENACURR)
            .maindest(UPDATED_MAINDEST)
            .maindestnm(UPDATED_MAINDESTNM)
            .gkppDarj(UPDATED_GKPP_DARJ)
            .gkppText(UPDATED_GKPP_TEXT)
            .textIni(UPDATED_TEXT_INI);
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(updatedMolbaRow);

        restMolbaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, molbaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(molbaRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMolbaRowToMatchAllProperties(updatedMolbaRow);
    }

    @Test
    @Transactional
    void putNonExistingMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, molbaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(molbaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(molbaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMolbaRowWithPatch() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the molbaRow using partial update
        MolbaRow partialUpdatedMolbaRow = new MolbaRow();
        partialUpdatedMolbaRow.setId(molbaRow.getId());

        partialUpdatedMolbaRow
            .brvl(UPDATED_BRVL)
            .brdni(UPDATED_BRDNI)
            .cel(UPDATED_CEL)
            .molDatVav(UPDATED_MOL_DAT_VAV)
            .imavisa(UPDATED_IMAVISA)
            .cenamol(UPDATED_CENAMOL)
            .maindest(UPDATED_MAINDEST)
            .gkppDarj(UPDATED_GKPP_DARJ)
            .textIni(UPDATED_TEXT_INI);

        restMolbaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMolbaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMolbaRow))
            )
            .andExpect(status().isOk());

        // Validate the MolbaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMolbaRowUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedMolbaRow, molbaRow), getPersistedMolbaRow(molbaRow));
    }

    @Test
    @Transactional
    void fullUpdateMolbaRowWithPatch() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the molbaRow using partial update
        MolbaRow partialUpdatedMolbaRow = new MolbaRow();
        partialUpdatedMolbaRow.setId(molbaRow.getId());

        partialUpdatedMolbaRow
            .datVli(UPDATED_DAT_VLI)
            .datIzl(UPDATED_DAT_IZL)
            .vidvis(UPDATED_VIDVIS)
            .brvl(UPDATED_BRVL)
            .vidus(UPDATED_VIDUS)
            .valvis(UPDATED_VALVIS)
            .brdni(UPDATED_BRDNI)
            .cel(UPDATED_CEL)
            .molDatVav(UPDATED_MOL_DAT_VAV)
            .gratis(UPDATED_GRATIS)
            .imavisa(UPDATED_IMAVISA)
            .cenamol(UPDATED_CENAMOL)
            .cenacurr(UPDATED_CENACURR)
            .maindest(UPDATED_MAINDEST)
            .maindestnm(UPDATED_MAINDESTNM)
            .gkppDarj(UPDATED_GKPP_DARJ)
            .gkppText(UPDATED_GKPP_TEXT)
            .textIni(UPDATED_TEXT_INI);

        restMolbaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMolbaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMolbaRow))
            )
            .andExpect(status().isOk());

        // Validate the MolbaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMolbaRowUpdatableFieldsEquals(partialUpdatedMolbaRow, getPersistedMolbaRow(partialUpdatedMolbaRow));
    }

    @Test
    @Transactional
    void patchNonExistingMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, molbaRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(molbaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(molbaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMolbaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        molbaRow.setId(longCount.incrementAndGet());

        // Create the MolbaRow
        MolbaRowDTO molbaRowDTO = molbaRowMapper.toDto(molbaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMolbaRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(molbaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MolbaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMolbaRow() throws Exception {
        // Initialize the database
        insertedMolbaRow = molbaRowRepository.saveAndFlush(molbaRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the molbaRow
        restMolbaRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, molbaRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return molbaRowRepository.count();
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

    protected MolbaRow getPersistedMolbaRow(MolbaRow molbaRow) {
        return molbaRowRepository.findById(molbaRow.getId()).orElseThrow();
    }

    protected void assertPersistedMolbaRowToMatchAllProperties(MolbaRow expectedMolbaRow) {
        assertMolbaRowAllPropertiesEquals(expectedMolbaRow, getPersistedMolbaRow(expectedMolbaRow));
    }

    protected void assertPersistedMolbaRowToMatchUpdatableProperties(MolbaRow expectedMolbaRow) {
        assertMolbaRowAllUpdatablePropertiesEquals(expectedMolbaRow, getPersistedMolbaRow(expectedMolbaRow));
    }
}
