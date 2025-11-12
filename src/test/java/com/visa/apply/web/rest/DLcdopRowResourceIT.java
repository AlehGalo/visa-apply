package com.visa.apply.web.rest;

import static com.visa.apply.domain.DLcdopRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.repository.DLcdopRowRepository;
import com.visa.apply.service.dto.DLcdopRowDTO;
import com.visa.apply.service.mapper.DLcdopRowMapper;
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
 * Integration tests for the {@link DLcdopRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DLcdopRowResourceIT {

    private static final String DEFAULT_LD_MRJDARJ = "AAAAAAAAAA";
    private static final String UPDATED_LD_MRJDARJ = "BBBBBBBBBB";

    private static final String DEFAULT_LD_MRJNM = "AAAAAAAAAA";
    private static final String UPDATED_LD_MRJNM = "BBBBBBBBBB";

    private static final String DEFAULT_LD_MRJGRAJ = "AAAAAAAAAA";
    private static final String UPDATED_LD_MRJGRAJ = "BBBBBBBBBB";

    private static final String DEFAULT_LD_ZENEN = "AAAAAAAAAA";
    private static final String UPDATED_LD_ZENEN = "BBBBBBBBBB";

    private static final String DEFAULT_LD_JIT_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_LD_JIT_DARJ = "BBBBBBBBBB";

    private static final String DEFAULT_LD_JIT_NM = "AAAAAAAAAA";
    private static final String UPDATED_LD_JIT_NM = "BBBBBBBBBB";

    private static final String DEFAULT_LD_JIT_UL = "AAAAAAAAAA";
    private static final String UPDATED_LD_JIT_UL = "BBBBBBBBBB";

    private static final Long DEFAULT_LD_TEL = 1L;
    private static final Long UPDATED_LD_TEL = 2L;
    private static final Long SMALLER_LD_TEL = 1L - 1L;

    private static final String DEFAULT_LD_RABOTA = "AAAAAAAAAA";
    private static final String UPDATED_LD_RABOTA = "BBBBBBBBBB";

    private static final String DEFAULT_LD_PROFKOD = "AAAAAAAAAA";
    private static final String UPDATED_LD_PROFKOD = "BBBBBBBBBB";

    private static final String DEFAULT_LD_PROFESIA = "AAAAAAAAAA";
    private static final String UPDATED_LD_PROFESIA = "BBBBBBBBBB";

    private static final String DEFAULT_LD_SL_DARJ = "AAAAAAAAAA";
    private static final String UPDATED_LD_SL_DARJ = "BBBBBBBBBB";

    private static final String DEFAULT_LD_SL_NM = "AAAAAAAAAA";
    private static final String UPDATED_LD_SL_NM = "BBBBBBBBBB";

    private static final String DEFAULT_LD_SL_UL = "AAAAAAAAAA";
    private static final String UPDATED_LD_SL_UL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-lcdop-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DLcdopRowRepository dLcdopRowRepository;

    @Autowired
    private DLcdopRowMapper dLcdopRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDLcdopRowMockMvc;

    private DLcdopRow dLcdopRow;

    private DLcdopRow insertedDLcdopRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLcdopRow createEntity() {
        return new DLcdopRow()
            .ldMrjdarj(DEFAULT_LD_MRJDARJ)
            .ldMrjnm(DEFAULT_LD_MRJNM)
            .ldMrjgraj(DEFAULT_LD_MRJGRAJ)
            .ldZenen(DEFAULT_LD_ZENEN)
            .ldJitDarj(DEFAULT_LD_JIT_DARJ)
            .ldJitNm(DEFAULT_LD_JIT_NM)
            .ldJitUl(DEFAULT_LD_JIT_UL)
            .ldTel(DEFAULT_LD_TEL)
            .ldRabota(DEFAULT_LD_RABOTA)
            .ldProfkod(DEFAULT_LD_PROFKOD)
            .ldProfesia(DEFAULT_LD_PROFESIA)
            .ldSlDarj(DEFAULT_LD_SL_DARJ)
            .ldSlNm(DEFAULT_LD_SL_NM)
            .ldSlUl(DEFAULT_LD_SL_UL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLcdopRow createUpdatedEntity() {
        return new DLcdopRow()
            .ldMrjdarj(UPDATED_LD_MRJDARJ)
            .ldMrjnm(UPDATED_LD_MRJNM)
            .ldMrjgraj(UPDATED_LD_MRJGRAJ)
            .ldZenen(UPDATED_LD_ZENEN)
            .ldJitDarj(UPDATED_LD_JIT_DARJ)
            .ldJitNm(UPDATED_LD_JIT_NM)
            .ldJitUl(UPDATED_LD_JIT_UL)
            .ldTel(UPDATED_LD_TEL)
            .ldRabota(UPDATED_LD_RABOTA)
            .ldProfkod(UPDATED_LD_PROFKOD)
            .ldProfesia(UPDATED_LD_PROFESIA)
            .ldSlDarj(UPDATED_LD_SL_DARJ)
            .ldSlNm(UPDATED_LD_SL_NM)
            .ldSlUl(UPDATED_LD_SL_UL);
    }

    @BeforeEach
    void initTest() {
        dLcdopRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDLcdopRow != null) {
            dLcdopRowRepository.delete(insertedDLcdopRow);
            insertedDLcdopRow = null;
        }
    }

    @Test
    @Transactional
    void createDLcdopRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);
        var returnedDLcdopRowDTO = om.readValue(
            restDLcdopRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DLcdopRowDTO.class
        );

        // Validate the DLcdopRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDLcdopRow = dLcdopRowMapper.toEntity(returnedDLcdopRowDTO);
        assertDLcdopRowUpdatableFieldsEquals(returnedDLcdopRow, getPersistedDLcdopRow(returnedDLcdopRow));

        insertedDLcdopRow = returnedDLcdopRow;
    }

    @Test
    @Transactional
    void createDLcdopRowWithExistingId() throws Exception {
        // Create the DLcdopRow with an existing ID
        dLcdopRow.setId(1L);
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLdMrjdarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdMrjdarj(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdMrjnmIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdMrjnm(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdMrjgrajIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdMrjgraj(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdZenenIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdZenen(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdJitDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdJitDarj(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdJitNmIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdJitNm(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdJitUlIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdJitUl(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdRabotaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdRabota(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdProfkodIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdProfkod(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdProfesiaIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdProfesia(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdSlDarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdSlDarj(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdSlNmIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdSlNm(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLdSlUlIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dLcdopRow.setLdSlUl(null);

        // Create the DLcdopRow, which fails.
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        restDLcdopRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDLcdopRows() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLcdopRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].ldMrjdarj").value(hasItem(DEFAULT_LD_MRJDARJ)))
            .andExpect(jsonPath("$.[*].ldMrjnm").value(hasItem(DEFAULT_LD_MRJNM)))
            .andExpect(jsonPath("$.[*].ldMrjgraj").value(hasItem(DEFAULT_LD_MRJGRAJ)))
            .andExpect(jsonPath("$.[*].ldZenen").value(hasItem(DEFAULT_LD_ZENEN)))
            .andExpect(jsonPath("$.[*].ldJitDarj").value(hasItem(DEFAULT_LD_JIT_DARJ)))
            .andExpect(jsonPath("$.[*].ldJitNm").value(hasItem(DEFAULT_LD_JIT_NM)))
            .andExpect(jsonPath("$.[*].ldJitUl").value(hasItem(DEFAULT_LD_JIT_UL)))
            .andExpect(jsonPath("$.[*].ldTel").value(hasItem(DEFAULT_LD_TEL.intValue())))
            .andExpect(jsonPath("$.[*].ldRabota").value(hasItem(DEFAULT_LD_RABOTA)))
            .andExpect(jsonPath("$.[*].ldProfkod").value(hasItem(DEFAULT_LD_PROFKOD)))
            .andExpect(jsonPath("$.[*].ldProfesia").value(hasItem(DEFAULT_LD_PROFESIA)))
            .andExpect(jsonPath("$.[*].ldSlDarj").value(hasItem(DEFAULT_LD_SL_DARJ)))
            .andExpect(jsonPath("$.[*].ldSlNm").value(hasItem(DEFAULT_LD_SL_NM)))
            .andExpect(jsonPath("$.[*].ldSlUl").value(hasItem(DEFAULT_LD_SL_UL)));
    }

    @Test
    @Transactional
    void getDLcdopRow() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get the dLcdopRow
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dLcdopRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dLcdopRow.getId().intValue()))
            .andExpect(jsonPath("$.ldMrjdarj").value(DEFAULT_LD_MRJDARJ))
            .andExpect(jsonPath("$.ldMrjnm").value(DEFAULT_LD_MRJNM))
            .andExpect(jsonPath("$.ldMrjgraj").value(DEFAULT_LD_MRJGRAJ))
            .andExpect(jsonPath("$.ldZenen").value(DEFAULT_LD_ZENEN))
            .andExpect(jsonPath("$.ldJitDarj").value(DEFAULT_LD_JIT_DARJ))
            .andExpect(jsonPath("$.ldJitNm").value(DEFAULT_LD_JIT_NM))
            .andExpect(jsonPath("$.ldJitUl").value(DEFAULT_LD_JIT_UL))
            .andExpect(jsonPath("$.ldTel").value(DEFAULT_LD_TEL.intValue()))
            .andExpect(jsonPath("$.ldRabota").value(DEFAULT_LD_RABOTA))
            .andExpect(jsonPath("$.ldProfkod").value(DEFAULT_LD_PROFKOD))
            .andExpect(jsonPath("$.ldProfesia").value(DEFAULT_LD_PROFESIA))
            .andExpect(jsonPath("$.ldSlDarj").value(DEFAULT_LD_SL_DARJ))
            .andExpect(jsonPath("$.ldSlNm").value(DEFAULT_LD_SL_NM))
            .andExpect(jsonPath("$.ldSlUl").value(DEFAULT_LD_SL_UL));
    }

    @Test
    @Transactional
    void getDLcdopRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        Long id = dLcdopRow.getId();

        defaultDLcdopRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDLcdopRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDLcdopRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjdarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjdarj equals to
        defaultDLcdopRowFiltering("ldMrjdarj.equals=" + DEFAULT_LD_MRJDARJ, "ldMrjdarj.equals=" + UPDATED_LD_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjdarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjdarj in
        defaultDLcdopRowFiltering("ldMrjdarj.in=" + DEFAULT_LD_MRJDARJ + "," + UPDATED_LD_MRJDARJ, "ldMrjdarj.in=" + UPDATED_LD_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjdarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjdarj is not null
        defaultDLcdopRowFiltering("ldMrjdarj.specified=true", "ldMrjdarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjdarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjdarj contains
        defaultDLcdopRowFiltering("ldMrjdarj.contains=" + DEFAULT_LD_MRJDARJ, "ldMrjdarj.contains=" + UPDATED_LD_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjdarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjdarj does not contain
        defaultDLcdopRowFiltering("ldMrjdarj.doesNotContain=" + UPDATED_LD_MRJDARJ, "ldMrjdarj.doesNotContain=" + DEFAULT_LD_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjnmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjnm equals to
        defaultDLcdopRowFiltering("ldMrjnm.equals=" + DEFAULT_LD_MRJNM, "ldMrjnm.equals=" + UPDATED_LD_MRJNM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjnmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjnm in
        defaultDLcdopRowFiltering("ldMrjnm.in=" + DEFAULT_LD_MRJNM + "," + UPDATED_LD_MRJNM, "ldMrjnm.in=" + UPDATED_LD_MRJNM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjnmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjnm is not null
        defaultDLcdopRowFiltering("ldMrjnm.specified=true", "ldMrjnm.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjnmContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjnm contains
        defaultDLcdopRowFiltering("ldMrjnm.contains=" + DEFAULT_LD_MRJNM, "ldMrjnm.contains=" + UPDATED_LD_MRJNM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjnmNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjnm does not contain
        defaultDLcdopRowFiltering("ldMrjnm.doesNotContain=" + UPDATED_LD_MRJNM, "ldMrjnm.doesNotContain=" + DEFAULT_LD_MRJNM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjgrajIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjgraj equals to
        defaultDLcdopRowFiltering("ldMrjgraj.equals=" + DEFAULT_LD_MRJGRAJ, "ldMrjgraj.equals=" + UPDATED_LD_MRJGRAJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjgrajIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjgraj in
        defaultDLcdopRowFiltering("ldMrjgraj.in=" + DEFAULT_LD_MRJGRAJ + "," + UPDATED_LD_MRJGRAJ, "ldMrjgraj.in=" + UPDATED_LD_MRJGRAJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjgrajIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjgraj is not null
        defaultDLcdopRowFiltering("ldMrjgraj.specified=true", "ldMrjgraj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjgrajContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjgraj contains
        defaultDLcdopRowFiltering("ldMrjgraj.contains=" + DEFAULT_LD_MRJGRAJ, "ldMrjgraj.contains=" + UPDATED_LD_MRJGRAJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdMrjgrajNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldMrjgraj does not contain
        defaultDLcdopRowFiltering("ldMrjgraj.doesNotContain=" + UPDATED_LD_MRJGRAJ, "ldMrjgraj.doesNotContain=" + DEFAULT_LD_MRJGRAJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdZenenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldZenen equals to
        defaultDLcdopRowFiltering("ldZenen.equals=" + DEFAULT_LD_ZENEN, "ldZenen.equals=" + UPDATED_LD_ZENEN);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdZenenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldZenen in
        defaultDLcdopRowFiltering("ldZenen.in=" + DEFAULT_LD_ZENEN + "," + UPDATED_LD_ZENEN, "ldZenen.in=" + UPDATED_LD_ZENEN);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdZenenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldZenen is not null
        defaultDLcdopRowFiltering("ldZenen.specified=true", "ldZenen.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdZenenContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldZenen contains
        defaultDLcdopRowFiltering("ldZenen.contains=" + DEFAULT_LD_ZENEN, "ldZenen.contains=" + UPDATED_LD_ZENEN);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdZenenNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldZenen does not contain
        defaultDLcdopRowFiltering("ldZenen.doesNotContain=" + UPDATED_LD_ZENEN, "ldZenen.doesNotContain=" + DEFAULT_LD_ZENEN);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitDarj equals to
        defaultDLcdopRowFiltering("ldJitDarj.equals=" + DEFAULT_LD_JIT_DARJ, "ldJitDarj.equals=" + UPDATED_LD_JIT_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitDarj in
        defaultDLcdopRowFiltering("ldJitDarj.in=" + DEFAULT_LD_JIT_DARJ + "," + UPDATED_LD_JIT_DARJ, "ldJitDarj.in=" + UPDATED_LD_JIT_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitDarj is not null
        defaultDLcdopRowFiltering("ldJitDarj.specified=true", "ldJitDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitDarj contains
        defaultDLcdopRowFiltering("ldJitDarj.contains=" + DEFAULT_LD_JIT_DARJ, "ldJitDarj.contains=" + UPDATED_LD_JIT_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitDarj does not contain
        defaultDLcdopRowFiltering("ldJitDarj.doesNotContain=" + UPDATED_LD_JIT_DARJ, "ldJitDarj.doesNotContain=" + DEFAULT_LD_JIT_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitNmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitNm equals to
        defaultDLcdopRowFiltering("ldJitNm.equals=" + DEFAULT_LD_JIT_NM, "ldJitNm.equals=" + UPDATED_LD_JIT_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitNmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitNm in
        defaultDLcdopRowFiltering("ldJitNm.in=" + DEFAULT_LD_JIT_NM + "," + UPDATED_LD_JIT_NM, "ldJitNm.in=" + UPDATED_LD_JIT_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitNmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitNm is not null
        defaultDLcdopRowFiltering("ldJitNm.specified=true", "ldJitNm.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitNmContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitNm contains
        defaultDLcdopRowFiltering("ldJitNm.contains=" + DEFAULT_LD_JIT_NM, "ldJitNm.contains=" + UPDATED_LD_JIT_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitNmNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitNm does not contain
        defaultDLcdopRowFiltering("ldJitNm.doesNotContain=" + UPDATED_LD_JIT_NM, "ldJitNm.doesNotContain=" + DEFAULT_LD_JIT_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitUlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitUl equals to
        defaultDLcdopRowFiltering("ldJitUl.equals=" + DEFAULT_LD_JIT_UL, "ldJitUl.equals=" + UPDATED_LD_JIT_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitUlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitUl in
        defaultDLcdopRowFiltering("ldJitUl.in=" + DEFAULT_LD_JIT_UL + "," + UPDATED_LD_JIT_UL, "ldJitUl.in=" + UPDATED_LD_JIT_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitUlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitUl is not null
        defaultDLcdopRowFiltering("ldJitUl.specified=true", "ldJitUl.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitUlContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitUl contains
        defaultDLcdopRowFiltering("ldJitUl.contains=" + DEFAULT_LD_JIT_UL, "ldJitUl.contains=" + UPDATED_LD_JIT_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdJitUlNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldJitUl does not contain
        defaultDLcdopRowFiltering("ldJitUl.doesNotContain=" + UPDATED_LD_JIT_UL, "ldJitUl.doesNotContain=" + DEFAULT_LD_JIT_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel equals to
        defaultDLcdopRowFiltering("ldTel.equals=" + DEFAULT_LD_TEL, "ldTel.equals=" + UPDATED_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel in
        defaultDLcdopRowFiltering("ldTel.in=" + DEFAULT_LD_TEL + "," + UPDATED_LD_TEL, "ldTel.in=" + UPDATED_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel is not null
        defaultDLcdopRowFiltering("ldTel.specified=true", "ldTel.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel is greater than or equal to
        defaultDLcdopRowFiltering("ldTel.greaterThanOrEqual=" + DEFAULT_LD_TEL, "ldTel.greaterThanOrEqual=" + UPDATED_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel is less than or equal to
        defaultDLcdopRowFiltering("ldTel.lessThanOrEqual=" + DEFAULT_LD_TEL, "ldTel.lessThanOrEqual=" + SMALLER_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel is less than
        defaultDLcdopRowFiltering("ldTel.lessThan=" + UPDATED_LD_TEL, "ldTel.lessThan=" + DEFAULT_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdTelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldTel is greater than
        defaultDLcdopRowFiltering("ldTel.greaterThan=" + SMALLER_LD_TEL, "ldTel.greaterThan=" + DEFAULT_LD_TEL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdRabotaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldRabota equals to
        defaultDLcdopRowFiltering("ldRabota.equals=" + DEFAULT_LD_RABOTA, "ldRabota.equals=" + UPDATED_LD_RABOTA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdRabotaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldRabota in
        defaultDLcdopRowFiltering("ldRabota.in=" + DEFAULT_LD_RABOTA + "," + UPDATED_LD_RABOTA, "ldRabota.in=" + UPDATED_LD_RABOTA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdRabotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldRabota is not null
        defaultDLcdopRowFiltering("ldRabota.specified=true", "ldRabota.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdRabotaContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldRabota contains
        defaultDLcdopRowFiltering("ldRabota.contains=" + DEFAULT_LD_RABOTA, "ldRabota.contains=" + UPDATED_LD_RABOTA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdRabotaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldRabota does not contain
        defaultDLcdopRowFiltering("ldRabota.doesNotContain=" + UPDATED_LD_RABOTA, "ldRabota.doesNotContain=" + DEFAULT_LD_RABOTA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfkodIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfkod equals to
        defaultDLcdopRowFiltering("ldProfkod.equals=" + DEFAULT_LD_PROFKOD, "ldProfkod.equals=" + UPDATED_LD_PROFKOD);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfkodIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfkod in
        defaultDLcdopRowFiltering("ldProfkod.in=" + DEFAULT_LD_PROFKOD + "," + UPDATED_LD_PROFKOD, "ldProfkod.in=" + UPDATED_LD_PROFKOD);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfkodIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfkod is not null
        defaultDLcdopRowFiltering("ldProfkod.specified=true", "ldProfkod.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfkodContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfkod contains
        defaultDLcdopRowFiltering("ldProfkod.contains=" + DEFAULT_LD_PROFKOD, "ldProfkod.contains=" + UPDATED_LD_PROFKOD);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfkodNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfkod does not contain
        defaultDLcdopRowFiltering("ldProfkod.doesNotContain=" + UPDATED_LD_PROFKOD, "ldProfkod.doesNotContain=" + DEFAULT_LD_PROFKOD);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfesiaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfesia equals to
        defaultDLcdopRowFiltering("ldProfesia.equals=" + DEFAULT_LD_PROFESIA, "ldProfesia.equals=" + UPDATED_LD_PROFESIA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfesiaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfesia in
        defaultDLcdopRowFiltering(
            "ldProfesia.in=" + DEFAULT_LD_PROFESIA + "," + UPDATED_LD_PROFESIA,
            "ldProfesia.in=" + UPDATED_LD_PROFESIA
        );
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfesiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfesia is not null
        defaultDLcdopRowFiltering("ldProfesia.specified=true", "ldProfesia.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfesiaContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfesia contains
        defaultDLcdopRowFiltering("ldProfesia.contains=" + DEFAULT_LD_PROFESIA, "ldProfesia.contains=" + UPDATED_LD_PROFESIA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdProfesiaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldProfesia does not contain
        defaultDLcdopRowFiltering("ldProfesia.doesNotContain=" + UPDATED_LD_PROFESIA, "ldProfesia.doesNotContain=" + DEFAULT_LD_PROFESIA);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlDarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlDarj equals to
        defaultDLcdopRowFiltering("ldSlDarj.equals=" + DEFAULT_LD_SL_DARJ, "ldSlDarj.equals=" + UPDATED_LD_SL_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlDarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlDarj in
        defaultDLcdopRowFiltering("ldSlDarj.in=" + DEFAULT_LD_SL_DARJ + "," + UPDATED_LD_SL_DARJ, "ldSlDarj.in=" + UPDATED_LD_SL_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlDarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlDarj is not null
        defaultDLcdopRowFiltering("ldSlDarj.specified=true", "ldSlDarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlDarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlDarj contains
        defaultDLcdopRowFiltering("ldSlDarj.contains=" + DEFAULT_LD_SL_DARJ, "ldSlDarj.contains=" + UPDATED_LD_SL_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlDarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlDarj does not contain
        defaultDLcdopRowFiltering("ldSlDarj.doesNotContain=" + UPDATED_LD_SL_DARJ, "ldSlDarj.doesNotContain=" + DEFAULT_LD_SL_DARJ);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlNmIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlNm equals to
        defaultDLcdopRowFiltering("ldSlNm.equals=" + DEFAULT_LD_SL_NM, "ldSlNm.equals=" + UPDATED_LD_SL_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlNmIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlNm in
        defaultDLcdopRowFiltering("ldSlNm.in=" + DEFAULT_LD_SL_NM + "," + UPDATED_LD_SL_NM, "ldSlNm.in=" + UPDATED_LD_SL_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlNmIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlNm is not null
        defaultDLcdopRowFiltering("ldSlNm.specified=true", "ldSlNm.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlNmContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlNm contains
        defaultDLcdopRowFiltering("ldSlNm.contains=" + DEFAULT_LD_SL_NM, "ldSlNm.contains=" + UPDATED_LD_SL_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlNmNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlNm does not contain
        defaultDLcdopRowFiltering("ldSlNm.doesNotContain=" + UPDATED_LD_SL_NM, "ldSlNm.doesNotContain=" + DEFAULT_LD_SL_NM);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlUlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlUl equals to
        defaultDLcdopRowFiltering("ldSlUl.equals=" + DEFAULT_LD_SL_UL, "ldSlUl.equals=" + UPDATED_LD_SL_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlUlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlUl in
        defaultDLcdopRowFiltering("ldSlUl.in=" + DEFAULT_LD_SL_UL + "," + UPDATED_LD_SL_UL, "ldSlUl.in=" + UPDATED_LD_SL_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlUlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlUl is not null
        defaultDLcdopRowFiltering("ldSlUl.specified=true", "ldSlUl.specified=false");
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlUlContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlUl contains
        defaultDLcdopRowFiltering("ldSlUl.contains=" + DEFAULT_LD_SL_UL, "ldSlUl.contains=" + UPDATED_LD_SL_UL);
    }

    @Test
    @Transactional
    void getAllDLcdopRowsByLdSlUlNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        // Get all the dLcdopRowList where ldSlUl does not contain
        defaultDLcdopRowFiltering("ldSlUl.doesNotContain=" + UPDATED_LD_SL_UL, "ldSlUl.doesNotContain=" + DEFAULT_LD_SL_UL);
    }

    private void defaultDLcdopRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDLcdopRowShouldBeFound(shouldBeFound);
        defaultDLcdopRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDLcdopRowShouldBeFound(String filter) throws Exception {
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLcdopRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].ldMrjdarj").value(hasItem(DEFAULT_LD_MRJDARJ)))
            .andExpect(jsonPath("$.[*].ldMrjnm").value(hasItem(DEFAULT_LD_MRJNM)))
            .andExpect(jsonPath("$.[*].ldMrjgraj").value(hasItem(DEFAULT_LD_MRJGRAJ)))
            .andExpect(jsonPath("$.[*].ldZenen").value(hasItem(DEFAULT_LD_ZENEN)))
            .andExpect(jsonPath("$.[*].ldJitDarj").value(hasItem(DEFAULT_LD_JIT_DARJ)))
            .andExpect(jsonPath("$.[*].ldJitNm").value(hasItem(DEFAULT_LD_JIT_NM)))
            .andExpect(jsonPath("$.[*].ldJitUl").value(hasItem(DEFAULT_LD_JIT_UL)))
            .andExpect(jsonPath("$.[*].ldTel").value(hasItem(DEFAULT_LD_TEL.intValue())))
            .andExpect(jsonPath("$.[*].ldRabota").value(hasItem(DEFAULT_LD_RABOTA)))
            .andExpect(jsonPath("$.[*].ldProfkod").value(hasItem(DEFAULT_LD_PROFKOD)))
            .andExpect(jsonPath("$.[*].ldProfesia").value(hasItem(DEFAULT_LD_PROFESIA)))
            .andExpect(jsonPath("$.[*].ldSlDarj").value(hasItem(DEFAULT_LD_SL_DARJ)))
            .andExpect(jsonPath("$.[*].ldSlNm").value(hasItem(DEFAULT_LD_SL_NM)))
            .andExpect(jsonPath("$.[*].ldSlUl").value(hasItem(DEFAULT_LD_SL_UL)));

        // Check, that the count call also returns 1
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDLcdopRowShouldNotBeFound(String filter) throws Exception {
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDLcdopRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDLcdopRow() throws Exception {
        // Get the dLcdopRow
        restDLcdopRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDLcdopRow() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcdopRow
        DLcdopRow updatedDLcdopRow = dLcdopRowRepository.findById(dLcdopRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDLcdopRow are not directly saved in db
        em.detach(updatedDLcdopRow);
        updatedDLcdopRow
            .ldMrjdarj(UPDATED_LD_MRJDARJ)
            .ldMrjnm(UPDATED_LD_MRJNM)
            .ldMrjgraj(UPDATED_LD_MRJGRAJ)
            .ldZenen(UPDATED_LD_ZENEN)
            .ldJitDarj(UPDATED_LD_JIT_DARJ)
            .ldJitNm(UPDATED_LD_JIT_NM)
            .ldJitUl(UPDATED_LD_JIT_UL)
            .ldTel(UPDATED_LD_TEL)
            .ldRabota(UPDATED_LD_RABOTA)
            .ldProfkod(UPDATED_LD_PROFKOD)
            .ldProfesia(UPDATED_LD_PROFESIA)
            .ldSlDarj(UPDATED_LD_SL_DARJ)
            .ldSlNm(UPDATED_LD_SL_NM)
            .ldSlUl(UPDATED_LD_SL_UL);
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(updatedDLcdopRow);

        restDLcdopRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLcdopRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcdopRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDLcdopRowToMatchAllProperties(updatedDLcdopRow);
    }

    @Test
    @Transactional
    void putNonExistingDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLcdopRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcdopRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLcdopRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDLcdopRowWithPatch() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcdopRow using partial update
        DLcdopRow partialUpdatedDLcdopRow = new DLcdopRow();
        partialUpdatedDLcdopRow.setId(dLcdopRow.getId());

        partialUpdatedDLcdopRow
            .ldMrjdarj(UPDATED_LD_MRJDARJ)
            .ldJitDarj(UPDATED_LD_JIT_DARJ)
            .ldJitUl(UPDATED_LD_JIT_UL)
            .ldTel(UPDATED_LD_TEL)
            .ldRabota(UPDATED_LD_RABOTA)
            .ldProfkod(UPDATED_LD_PROFKOD)
            .ldProfesia(UPDATED_LD_PROFESIA);

        restDLcdopRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLcdopRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLcdopRow))
            )
            .andExpect(status().isOk());

        // Validate the DLcdopRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLcdopRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDLcdopRow, dLcdopRow),
            getPersistedDLcdopRow(dLcdopRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDLcdopRowWithPatch() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLcdopRow using partial update
        DLcdopRow partialUpdatedDLcdopRow = new DLcdopRow();
        partialUpdatedDLcdopRow.setId(dLcdopRow.getId());

        partialUpdatedDLcdopRow
            .ldMrjdarj(UPDATED_LD_MRJDARJ)
            .ldMrjnm(UPDATED_LD_MRJNM)
            .ldMrjgraj(UPDATED_LD_MRJGRAJ)
            .ldZenen(UPDATED_LD_ZENEN)
            .ldJitDarj(UPDATED_LD_JIT_DARJ)
            .ldJitNm(UPDATED_LD_JIT_NM)
            .ldJitUl(UPDATED_LD_JIT_UL)
            .ldTel(UPDATED_LD_TEL)
            .ldRabota(UPDATED_LD_RABOTA)
            .ldProfkod(UPDATED_LD_PROFKOD)
            .ldProfesia(UPDATED_LD_PROFESIA)
            .ldSlDarj(UPDATED_LD_SL_DARJ)
            .ldSlNm(UPDATED_LD_SL_NM)
            .ldSlUl(UPDATED_LD_SL_UL);

        restDLcdopRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLcdopRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLcdopRow))
            )
            .andExpect(status().isOk());

        // Validate the DLcdopRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLcdopRowUpdatableFieldsEquals(partialUpdatedDLcdopRow, getPersistedDLcdopRow(partialUpdatedDLcdopRow));
    }

    @Test
    @Transactional
    void patchNonExistingDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dLcdopRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLcdopRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLcdopRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDLcdopRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLcdopRow.setId(longCount.incrementAndGet());

        // Create the DLcdopRow
        DLcdopRowDTO dLcdopRowDTO = dLcdopRowMapper.toDto(dLcdopRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLcdopRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dLcdopRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLcdopRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDLcdopRow() throws Exception {
        // Initialize the database
        insertedDLcdopRow = dLcdopRowRepository.saveAndFlush(dLcdopRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dLcdopRow
        restDLcdopRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dLcdopRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dLcdopRowRepository.count();
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

    protected DLcdopRow getPersistedDLcdopRow(DLcdopRow dLcdopRow) {
        return dLcdopRowRepository.findById(dLcdopRow.getId()).orElseThrow();
    }

    protected void assertPersistedDLcdopRowToMatchAllProperties(DLcdopRow expectedDLcdopRow) {
        assertDLcdopRowAllPropertiesEquals(expectedDLcdopRow, getPersistedDLcdopRow(expectedDLcdopRow));
    }

    protected void assertPersistedDLcdopRowToMatchUpdatableProperties(DLcdopRow expectedDLcdopRow) {
        assertDLcdopRowAllUpdatablePropertiesEquals(expectedDLcdopRow, getPersistedDLcdopRow(expectedDLcdopRow));
    }
}
