package com.visa.apply.web.rest;

import static com.visa.apply.domain.DDomakinRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DDomakinRow;
import com.visa.apply.repository.DDomakinRowRepository;
import com.visa.apply.service.dto.DDomakinRowDTO;
import com.visa.apply.service.mapper.DDomakinRowMapper;
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
 * Integration tests for the {@link DDomakinRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DDomakinRowResourceIT {

    private static final String DEFAULT_DM_VID = "AAAAAAAAAA";
    private static final String UPDATED_DM_VID = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_POK = "AAAAAAAAAA";
    private static final String UPDATED_NOM_POK = "BBBBBBBBBB";

    private static final String DEFAULT_DOM_GRAJ = "AAAAAAAAAA";
    private static final String UPDATED_DOM_GRAJ = "BBBBBBBBBB";

    private static final String DEFAULT_DOM_FAMIL = "AAAAAAAAAA";
    private static final String UPDATED_DOM_FAMIL = "BBBBBBBBBB";

    private static final String DEFAULT_DOM_IME = "AAAAAAAAAA";
    private static final String UPDATED_DOM_IME = "BBBBBBBBBB";

    private static final String DEFAULT_DOM_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_DOM_DARJ = "BBBBBBBBBB";

    private static final Integer DEFAULT_DOM_NM = 1;
    private static final Integer UPDATED_DOM_NM = 2;
    private static final Integer SMALLER_DOM_NM = 1 - 1;

    private static final String DEFAULT_DOM_ADRES = "AAAAAAAAAA";
    private static final String UPDATED_DOM_ADRES = "BBBBBBBBBB";

    private static final String DEFAULT_VED_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_VED_DARJ = "BBBBBBBBBB";

    private static final String DEFAULT_VED_NM = "AAAAAAAAAA";
    private static final String UPDATED_VED_NM = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-domakin-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DDomakinRowRepository dDomakinRowRepository;

    @Autowired
    private DDomakinRowMapper dDomakinRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDDomakinRowMockMvc;

    private DDomakinRow dDomakinRow;

    private DDomakinRow insertedDDomakinRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DDomakinRow createEntity() {
        return new DDomakinRow()
            .dmVid(DEFAULT_DM_VID)
            .nomPok(DEFAULT_NOM_POK)
            .domGraj(DEFAULT_DOM_GRAJ)
            .domFamil(DEFAULT_DOM_FAMIL)
            .domIme(DEFAULT_DOM_IME)
            .domDarj(DEFAULT_DOM_DARJ)
            .domNm(DEFAULT_DOM_NM)
            .domAdres(DEFAULT_DOM_ADRES)
            .vedDarj(DEFAULT_VED_DARJ)
            .vedNm(DEFAULT_VED_NM);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DDomakinRow createUpdatedEntity() {
        return new DDomakinRow()
            .dmVid(UPDATED_DM_VID)
            .nomPok(UPDATED_NOM_POK)
            .domGraj(UPDATED_DOM_GRAJ)
            .domFamil(UPDATED_DOM_FAMIL)
            .domIme(UPDATED_DOM_IME)
            .domDarj(UPDATED_DOM_DARJ)
            .domNm(UPDATED_DOM_NM)
            .domAdres(UPDATED_DOM_ADRES)
            .vedDarj(UPDATED_VED_DARJ)
            .vedNm(UPDATED_VED_NM);
    }

    @BeforeEach
    void initTest() {
        dDomakinRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDDomakinRow != null) {
            dDomakinRowRepository.delete(insertedDDomakinRow);
            insertedDDomakinRow = null;
        }
    }

    @Test
    @Transactional
    void createDDomakinRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);
        var returnedDDomakinRowDTO = om.readValue(
            restDDomakinRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DDomakinRowDTO.class
        );

        // Validate the DDomakinRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDDomakinRow = dDomakinRowMapper.toEntity(returnedDDomakinRowDTO);
        assertDDomakinRowUpdatableFieldsEquals(returnedDDomakinRow, getPersistedDDomakinRow(returnedDDomakinRow));

        insertedDDomakinRow = returnedDDomakinRow;
    }

    @Test
    @Transactional
    void createDDomakinRowWithExistingId() throws Exception {
        // Create the DDomakinRow with an existing ID
        dDomakinRow.setId(1L);
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDmVidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDmVid(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomPokIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setNomPok(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDomGrajIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDomGraj(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDomFamilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDomFamil(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDomImeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDomIme(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDomDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDomDarj(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDomAdresIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setDomAdres(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVedDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setVedDarj(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVedNmIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dDomakinRow.setVedNm(null);

        // Create the DDomakinRow, which fails.
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        restDDomakinRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDDomakinRows() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dDomakinRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dmVid").value(hasItem(DEFAULT_DM_VID)))
            .andExpect(jsonPath("$.[*].nomPok").value(hasItem(DEFAULT_NOM_POK)))
            .andExpect(jsonPath("$.[*].domGraj").value(hasItem(DEFAULT_DOM_GRAJ)))
            .andExpect(jsonPath("$.[*].domFamil").value(hasItem(DEFAULT_DOM_FAMIL)))
            .andExpect(jsonPath("$.[*].domIme").value(hasItem(DEFAULT_DOM_IME)))
            .andExpect(jsonPath("$.[*].domDarj").value(hasItem(DEFAULT_DOM_DARJ)))
            .andExpect(jsonPath("$.[*].domNm").value(hasItem(DEFAULT_DOM_NM)))
            .andExpect(jsonPath("$.[*].domAdres").value(hasItem(DEFAULT_DOM_ADRES)))
            .andExpect(jsonPath("$.[*].vedDarj").value(hasItem(DEFAULT_VED_DARJ)))
            .andExpect(jsonPath("$.[*].vedNm").value(hasItem(DEFAULT_VED_NM)));
    }

    @Test
    @Transactional
    void getDDomakinRow() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get the dDomakinRow
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dDomakinRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dDomakinRow.getId().intValue()))
            .andExpect(jsonPath("$.dmVid").value(DEFAULT_DM_VID))
            .andExpect(jsonPath("$.nomPok").value(DEFAULT_NOM_POK))
            .andExpect(jsonPath("$.domGraj").value(DEFAULT_DOM_GRAJ))
            .andExpect(jsonPath("$.domFamil").value(DEFAULT_DOM_FAMIL))
            .andExpect(jsonPath("$.domIme").value(DEFAULT_DOM_IME))
            .andExpect(jsonPath("$.domDarj").value(DEFAULT_DOM_DARJ))
            .andExpect(jsonPath("$.domNm").value(DEFAULT_DOM_NM))
            .andExpect(jsonPath("$.domAdres").value(DEFAULT_DOM_ADRES))
            .andExpect(jsonPath("$.vedDarj").value(DEFAULT_VED_DARJ))
            .andExpect(jsonPath("$.vedNm").value(DEFAULT_VED_NM));
    }

    @Test
    @Transactional
    void getDDomakinRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        Long id = dDomakinRow.getId();

        defaultDDomakinRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDDomakinRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDDomakinRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDmVidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where dmVid equals to
        defaultDDomakinRowFiltering("dmVid.equals=" + DEFAULT_DM_VID, "dmVid.equals=" + UPDATED_DM_VID);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDmVidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where dmVid in
        defaultDDomakinRowFiltering("dmVid.in=" + DEFAULT_DM_VID + "," + UPDATED_DM_VID, "dmVid.in=" + UPDATED_DM_VID);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDmVidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where dmVid is not null
        defaultDDomakinRowFiltering("dmVid.specified=true", "dmVid.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDmVidContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where dmVid contains
        defaultDDomakinRowFiltering("dmVid.contains=" + DEFAULT_DM_VID, "dmVid.contains=" + UPDATED_DM_VID);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDmVidNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where dmVid does not contain
        defaultDDomakinRowFiltering("dmVid.doesNotContain=" + UPDATED_DM_VID, "dmVid.doesNotContain=" + DEFAULT_DM_VID);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByNomPokIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where nomPok equals to
        defaultDDomakinRowFiltering("nomPok.equals=" + DEFAULT_NOM_POK, "nomPok.equals=" + UPDATED_NOM_POK);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByNomPokIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where nomPok in
        defaultDDomakinRowFiltering("nomPok.in=" + DEFAULT_NOM_POK + "," + UPDATED_NOM_POK, "nomPok.in=" + UPDATED_NOM_POK);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByNomPokIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where nomPok is not null
        defaultDDomakinRowFiltering("nomPok.specified=true", "nomPok.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByNomPokContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where nomPok contains
        defaultDDomakinRowFiltering("nomPok.contains=" + DEFAULT_NOM_POK, "nomPok.contains=" + UPDATED_NOM_POK);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByNomPokNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where nomPok does not contain
        defaultDDomakinRowFiltering("nomPok.doesNotContain=" + UPDATED_NOM_POK, "nomPok.doesNotContain=" + DEFAULT_NOM_POK);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomGrajIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domGraj equals to
        defaultDDomakinRowFiltering("domGraj.equals=" + DEFAULT_DOM_GRAJ, "domGraj.equals=" + UPDATED_DOM_GRAJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomGrajIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domGraj in
        defaultDDomakinRowFiltering("domGraj.in=" + DEFAULT_DOM_GRAJ + "," + UPDATED_DOM_GRAJ, "domGraj.in=" + UPDATED_DOM_GRAJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomGrajIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domGraj is not null
        defaultDDomakinRowFiltering("domGraj.specified=true", "domGraj.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomGrajContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domGraj contains
        defaultDDomakinRowFiltering("domGraj.contains=" + DEFAULT_DOM_GRAJ, "domGraj.contains=" + UPDATED_DOM_GRAJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomGrajNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domGraj does not contain
        defaultDDomakinRowFiltering("domGraj.doesNotContain=" + UPDATED_DOM_GRAJ, "domGraj.doesNotContain=" + DEFAULT_DOM_GRAJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomFamilIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domFamil equals to
        defaultDDomakinRowFiltering("domFamil.equals=" + DEFAULT_DOM_FAMIL, "domFamil.equals=" + UPDATED_DOM_FAMIL);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomFamilIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domFamil in
        defaultDDomakinRowFiltering("domFamil.in=" + DEFAULT_DOM_FAMIL + "," + UPDATED_DOM_FAMIL, "domFamil.in=" + UPDATED_DOM_FAMIL);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomFamilIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domFamil is not null
        defaultDDomakinRowFiltering("domFamil.specified=true", "domFamil.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomFamilContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domFamil contains
        defaultDDomakinRowFiltering("domFamil.contains=" + DEFAULT_DOM_FAMIL, "domFamil.contains=" + UPDATED_DOM_FAMIL);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomFamilNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domFamil does not contain
        defaultDDomakinRowFiltering("domFamil.doesNotContain=" + UPDATED_DOM_FAMIL, "domFamil.doesNotContain=" + DEFAULT_DOM_FAMIL);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomImeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domIme equals to
        defaultDDomakinRowFiltering("domIme.equals=" + DEFAULT_DOM_IME, "domIme.equals=" + UPDATED_DOM_IME);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomImeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domIme in
        defaultDDomakinRowFiltering("domIme.in=" + DEFAULT_DOM_IME + "," + UPDATED_DOM_IME, "domIme.in=" + UPDATED_DOM_IME);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomImeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domIme is not null
        defaultDDomakinRowFiltering("domIme.specified=true", "domIme.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomImeContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domIme contains
        defaultDDomakinRowFiltering("domIme.contains=" + DEFAULT_DOM_IME, "domIme.contains=" + UPDATED_DOM_IME);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomImeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domIme does not contain
        defaultDDomakinRowFiltering("domIme.doesNotContain=" + UPDATED_DOM_IME, "domIme.doesNotContain=" + DEFAULT_DOM_IME);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domDarj equals to
        defaultDDomakinRowFiltering("domDarj.equals=" + DEFAULT_DOM_DARJ, "domDarj.equals=" + UPDATED_DOM_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domDarj in
        defaultDDomakinRowFiltering("domDarj.in=" + DEFAULT_DOM_DARJ + "," + UPDATED_DOM_DARJ, "domDarj.in=" + UPDATED_DOM_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domDarj is not null
        defaultDDomakinRowFiltering("domDarj.specified=true", "domDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domDarj contains
        defaultDDomakinRowFiltering("domDarj.contains=" + DEFAULT_DOM_DARJ, "domDarj.contains=" + UPDATED_DOM_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domDarj does not contain
        defaultDDomakinRowFiltering("domDarj.doesNotContain=" + UPDATED_DOM_DARJ, "domDarj.doesNotContain=" + DEFAULT_DOM_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm equals to
        defaultDDomakinRowFiltering("domNm.equals=" + DEFAULT_DOM_NM, "domNm.equals=" + UPDATED_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm in
        defaultDDomakinRowFiltering("domNm.in=" + DEFAULT_DOM_NM + "," + UPDATED_DOM_NM, "domNm.in=" + UPDATED_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm is not null
        defaultDDomakinRowFiltering("domNm.specified=true", "domNm.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm is greater than or equal to
        defaultDDomakinRowFiltering("domNm.greaterThanOrEqual=" + DEFAULT_DOM_NM, "domNm.greaterThanOrEqual=" + UPDATED_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm is less than or equal to
        defaultDDomakinRowFiltering("domNm.lessThanOrEqual=" + DEFAULT_DOM_NM, "domNm.lessThanOrEqual=" + SMALLER_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm is less than
        defaultDDomakinRowFiltering("domNm.lessThan=" + UPDATED_DOM_NM, "domNm.lessThan=" + DEFAULT_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomNmIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domNm is greater than
        defaultDDomakinRowFiltering("domNm.greaterThan=" + SMALLER_DOM_NM, "domNm.greaterThan=" + DEFAULT_DOM_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomAdresIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domAdres equals to
        defaultDDomakinRowFiltering("domAdres.equals=" + DEFAULT_DOM_ADRES, "domAdres.equals=" + UPDATED_DOM_ADRES);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomAdresIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domAdres in
        defaultDDomakinRowFiltering("domAdres.in=" + DEFAULT_DOM_ADRES + "," + UPDATED_DOM_ADRES, "domAdres.in=" + UPDATED_DOM_ADRES);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomAdresIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domAdres is not null
        defaultDDomakinRowFiltering("domAdres.specified=true", "domAdres.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomAdresContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domAdres contains
        defaultDDomakinRowFiltering("domAdres.contains=" + DEFAULT_DOM_ADRES, "domAdres.contains=" + UPDATED_DOM_ADRES);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByDomAdresNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where domAdres does not contain
        defaultDDomakinRowFiltering("domAdres.doesNotContain=" + UPDATED_DOM_ADRES, "domAdres.doesNotContain=" + DEFAULT_DOM_ADRES);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedDarj equals to
        defaultDDomakinRowFiltering("vedDarj.equals=" + DEFAULT_VED_DARJ, "vedDarj.equals=" + UPDATED_VED_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedDarj in
        defaultDDomakinRowFiltering("vedDarj.in=" + DEFAULT_VED_DARJ + "," + UPDATED_VED_DARJ, "vedDarj.in=" + UPDATED_VED_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedDarj is not null
        defaultDDomakinRowFiltering("vedDarj.specified=true", "vedDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedDarj contains
        defaultDDomakinRowFiltering("vedDarj.contains=" + DEFAULT_VED_DARJ, "vedDarj.contains=" + UPDATED_VED_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedDarj does not contain
        defaultDDomakinRowFiltering("vedDarj.doesNotContain=" + UPDATED_VED_DARJ, "vedDarj.doesNotContain=" + DEFAULT_VED_DARJ);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedNmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedNm equals to
        defaultDDomakinRowFiltering("vedNm.equals=" + DEFAULT_VED_NM, "vedNm.equals=" + UPDATED_VED_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedNmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedNm in
        defaultDDomakinRowFiltering("vedNm.in=" + DEFAULT_VED_NM + "," + UPDATED_VED_NM, "vedNm.in=" + UPDATED_VED_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedNmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedNm is not null
        defaultDDomakinRowFiltering("vedNm.specified=true", "vedNm.specified=false");
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedNmContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedNm contains
        defaultDDomakinRowFiltering("vedNm.contains=" + DEFAULT_VED_NM, "vedNm.contains=" + UPDATED_VED_NM);
    }

    @Test
    @Transactional
    void getAllDDomakinRowsByVedNmNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        // Get all the dDomakinRowList where vedNm does not contain
        defaultDDomakinRowFiltering("vedNm.doesNotContain=" + UPDATED_VED_NM, "vedNm.doesNotContain=" + DEFAULT_VED_NM);
    }

    private void defaultDDomakinRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDDomakinRowShouldBeFound(shouldBeFound);
        defaultDDomakinRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDDomakinRowShouldBeFound(String filter) throws Exception {
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dDomakinRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].dmVid").value(hasItem(DEFAULT_DM_VID)))
            .andExpect(jsonPath("$.[*].nomPok").value(hasItem(DEFAULT_NOM_POK)))
            .andExpect(jsonPath("$.[*].domGraj").value(hasItem(DEFAULT_DOM_GRAJ)))
            .andExpect(jsonPath("$.[*].domFamil").value(hasItem(DEFAULT_DOM_FAMIL)))
            .andExpect(jsonPath("$.[*].domIme").value(hasItem(DEFAULT_DOM_IME)))
            .andExpect(jsonPath("$.[*].domDarj").value(hasItem(DEFAULT_DOM_DARJ)))
            .andExpect(jsonPath("$.[*].domNm").value(hasItem(DEFAULT_DOM_NM)))
            .andExpect(jsonPath("$.[*].domAdres").value(hasItem(DEFAULT_DOM_ADRES)))
            .andExpect(jsonPath("$.[*].vedDarj").value(hasItem(DEFAULT_VED_DARJ)))
            .andExpect(jsonPath("$.[*].vedNm").value(hasItem(DEFAULT_VED_NM)));

        // Check, that the count call also returns 1
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDDomakinRowShouldNotBeFound(String filter) throws Exception {
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDDomakinRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDDomakinRow() throws Exception {
        // Get the dDomakinRow
        restDDomakinRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDDomakinRow() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dDomakinRow
        DDomakinRow updatedDDomakinRow = dDomakinRowRepository.findById(dDomakinRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDDomakinRow are not directly saved in db
        em.detach(updatedDDomakinRow);
        updatedDDomakinRow
            .dmVid(UPDATED_DM_VID)
            .nomPok(UPDATED_NOM_POK)
            .domGraj(UPDATED_DOM_GRAJ)
            .domFamil(UPDATED_DOM_FAMIL)
            .domIme(UPDATED_DOM_IME)
            .domDarj(UPDATED_DOM_DARJ)
            .domNm(UPDATED_DOM_NM)
            .domAdres(UPDATED_DOM_ADRES)
            .vedDarj(UPDATED_VED_DARJ)
            .vedNm(UPDATED_VED_NM);
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(updatedDDomakinRow);

        restDDomakinRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dDomakinRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dDomakinRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDDomakinRowToMatchAllProperties(updatedDDomakinRow);
    }

    @Test
    @Transactional
    void putNonExistingDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dDomakinRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dDomakinRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dDomakinRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDDomakinRowWithPatch() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dDomakinRow using partial update
        DDomakinRow partialUpdatedDDomakinRow = new DDomakinRow();
        partialUpdatedDDomakinRow.setId(dDomakinRow.getId());

        partialUpdatedDDomakinRow.dmVid(UPDATED_DM_VID).domIme(UPDATED_DOM_IME).vedDarj(UPDATED_VED_DARJ).vedNm(UPDATED_VED_NM);

        restDDomakinRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDDomakinRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDDomakinRow))
            )
            .andExpect(status().isOk());

        // Validate the DDomakinRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDDomakinRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDDomakinRow, dDomakinRow),
            getPersistedDDomakinRow(dDomakinRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDDomakinRowWithPatch() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dDomakinRow using partial update
        DDomakinRow partialUpdatedDDomakinRow = new DDomakinRow();
        partialUpdatedDDomakinRow.setId(dDomakinRow.getId());

        partialUpdatedDDomakinRow
            .dmVid(UPDATED_DM_VID)
            .nomPok(UPDATED_NOM_POK)
            .domGraj(UPDATED_DOM_GRAJ)
            .domFamil(UPDATED_DOM_FAMIL)
            .domIme(UPDATED_DOM_IME)
            .domDarj(UPDATED_DOM_DARJ)
            .domNm(UPDATED_DOM_NM)
            .domAdres(UPDATED_DOM_ADRES)
            .vedDarj(UPDATED_VED_DARJ)
            .vedNm(UPDATED_VED_NM);

        restDDomakinRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDDomakinRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDDomakinRow))
            )
            .andExpect(status().isOk());

        // Validate the DDomakinRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDDomakinRowUpdatableFieldsEquals(partialUpdatedDDomakinRow, getPersistedDDomakinRow(partialUpdatedDDomakinRow));
    }

    @Test
    @Transactional
    void patchNonExistingDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dDomakinRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dDomakinRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dDomakinRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDDomakinRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dDomakinRow.setId(longCount.incrementAndGet());

        // Create the DDomakinRow
        DDomakinRowDTO dDomakinRowDTO = dDomakinRowMapper.toDto(dDomakinRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDDomakinRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dDomakinRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DDomakinRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDDomakinRow() throws Exception {
        // Initialize the database
        insertedDDomakinRow = dDomakinRowRepository.saveAndFlush(dDomakinRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dDomakinRow
        restDDomakinRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dDomakinRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dDomakinRowRepository.count();
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

    protected DDomakinRow getPersistedDDomakinRow(DDomakinRow dDomakinRow) {
        return dDomakinRowRepository.findById(dDomakinRow.getId()).orElseThrow();
    }

    protected void assertPersistedDDomakinRowToMatchAllProperties(DDomakinRow expectedDDomakinRow) {
        assertDDomakinRowAllPropertiesEquals(expectedDDomakinRow, getPersistedDDomakinRow(expectedDDomakinRow));
    }

    protected void assertPersistedDDomakinRowToMatchUpdatableProperties(DDomakinRow expectedDDomakinRow) {
        assertDDomakinRowAllUpdatablePropertiesEquals(expectedDDomakinRow, getPersistedDDomakinRow(expectedDDomakinRow));
    }
}
