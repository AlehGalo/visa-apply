package com.visa.apply.web.rest;

import static com.visa.apply.domain.DMsgheaderRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.repository.DMsgheaderRowRepository;
import com.visa.apply.service.dto.DMsgheaderRowDTO;
import com.visa.apply.service.mapper.DMsgheaderRowMapper;
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
 * Integration tests for the {@link DMsgheaderRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DMsgheaderRowResourceIT {

    private static final String DEFAULT_MH_KSCREATED = "AAAAAAAAAA";
    private static final String UPDATED_MH_KSCREATED = "BBBBBBBBBB";

    private static final Integer DEFAULT_MH_REGNOM = 1;
    private static final Integer UPDATED_MH_REGNOM = 2;
    private static final Integer SMALLER_MH_REGNOM = 1 - 1;

    private static final String DEFAULT_MH_VFSREFNO = "AAAAAAAAAA";
    private static final String UPDATED_MH_VFSREFNO = "BBBBBBBBBB";

    private static final String DEFAULT_MH_USERA = "AAAAAAAAAA";
    private static final String UPDATED_MH_USERA = "BBBBBBBBBB";

    private static final String DEFAULT_MH_DATVAV = "AAAAAAAAAA";
    private static final String UPDATED_MH_DATVAV = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-msgheader-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DMsgheaderRowRepository dMsgheaderRowRepository;

    @Autowired
    private DMsgheaderRowMapper dMsgheaderRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDMsgheaderRowMockMvc;

    private DMsgheaderRow dMsgheaderRow;

    private DMsgheaderRow insertedDMsgheaderRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DMsgheaderRow createEntity() {
        return new DMsgheaderRow()
            .mhKscreated(DEFAULT_MH_KSCREATED)
            .mhRegnom(DEFAULT_MH_REGNOM)
            .mhVfsrefno(DEFAULT_MH_VFSREFNO)
            .mhUsera(DEFAULT_MH_USERA)
            .mhDatvav(DEFAULT_MH_DATVAV);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DMsgheaderRow createUpdatedEntity() {
        return new DMsgheaderRow()
            .mhKscreated(UPDATED_MH_KSCREATED)
            .mhRegnom(UPDATED_MH_REGNOM)
            .mhVfsrefno(UPDATED_MH_VFSREFNO)
            .mhUsera(UPDATED_MH_USERA)
            .mhDatvav(UPDATED_MH_DATVAV);
    }

    @BeforeEach
    void initTest() {
        dMsgheaderRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDMsgheaderRow != null) {
            dMsgheaderRowRepository.delete(insertedDMsgheaderRow);
            insertedDMsgheaderRow = null;
        }
    }

    @Test
    @Transactional
    void createDMsgheaderRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);
        var returnedDMsgheaderRowDTO = om.readValue(
            restDMsgheaderRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DMsgheaderRowDTO.class
        );

        // Validate the DMsgheaderRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDMsgheaderRow = dMsgheaderRowMapper.toEntity(returnedDMsgheaderRowDTO);
        assertDMsgheaderRowUpdatableFieldsEquals(returnedDMsgheaderRow, getPersistedDMsgheaderRow(returnedDMsgheaderRow));

        insertedDMsgheaderRow = returnedDMsgheaderRow;
    }

    @Test
    @Transactional
    void createDMsgheaderRowWithExistingId() throws Exception {
        // Create the DMsgheaderRow with an existing ID
        dMsgheaderRow.setId(1L);
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDMsgheaderRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMhKscreatedIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMsgheaderRow.setMhKscreated(null);

        // Create the DMsgheaderRow, which fails.
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        restDMsgheaderRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMhVfsrefnoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMsgheaderRow.setMhVfsrefno(null);

        // Create the DMsgheaderRow, which fails.
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        restDMsgheaderRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMhUseraIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMsgheaderRow.setMhUsera(null);

        // Create the DMsgheaderRow, which fails.
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        restDMsgheaderRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMhDatvavIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dMsgheaderRow.setMhDatvav(null);

        // Create the DMsgheaderRow, which fails.
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        restDMsgheaderRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRows() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dMsgheaderRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].mhKscreated").value(hasItem(DEFAULT_MH_KSCREATED)))
            .andExpect(jsonPath("$.[*].mhRegnom").value(hasItem(DEFAULT_MH_REGNOM)))
            .andExpect(jsonPath("$.[*].mhVfsrefno").value(hasItem(DEFAULT_MH_VFSREFNO)))
            .andExpect(jsonPath("$.[*].mhUsera").value(hasItem(DEFAULT_MH_USERA)))
            .andExpect(jsonPath("$.[*].mhDatvav").value(hasItem(DEFAULT_MH_DATVAV)));
    }

    @Test
    @Transactional
    void getDMsgheaderRow() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get the dMsgheaderRow
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dMsgheaderRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dMsgheaderRow.getId().intValue()))
            .andExpect(jsonPath("$.mhKscreated").value(DEFAULT_MH_KSCREATED))
            .andExpect(jsonPath("$.mhRegnom").value(DEFAULT_MH_REGNOM))
            .andExpect(jsonPath("$.mhVfsrefno").value(DEFAULT_MH_VFSREFNO))
            .andExpect(jsonPath("$.mhUsera").value(DEFAULT_MH_USERA))
            .andExpect(jsonPath("$.mhDatvav").value(DEFAULT_MH_DATVAV));
    }

    @Test
    @Transactional
    void getDMsgheaderRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        Long id = dMsgheaderRow.getId();

        defaultDMsgheaderRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDMsgheaderRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDMsgheaderRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhKscreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhKscreated equals to
        defaultDMsgheaderRowFiltering("mhKscreated.equals=" + DEFAULT_MH_KSCREATED, "mhKscreated.equals=" + UPDATED_MH_KSCREATED);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhKscreatedIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhKscreated in
        defaultDMsgheaderRowFiltering(
            "mhKscreated.in=" + DEFAULT_MH_KSCREATED + "," + UPDATED_MH_KSCREATED,
            "mhKscreated.in=" + UPDATED_MH_KSCREATED
        );
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhKscreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhKscreated is not null
        defaultDMsgheaderRowFiltering("mhKscreated.specified=true", "mhKscreated.specified=false");
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhKscreatedContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhKscreated contains
        defaultDMsgheaderRowFiltering("mhKscreated.contains=" + DEFAULT_MH_KSCREATED, "mhKscreated.contains=" + UPDATED_MH_KSCREATED);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhKscreatedNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhKscreated does not contain
        defaultDMsgheaderRowFiltering(
            "mhKscreated.doesNotContain=" + UPDATED_MH_KSCREATED,
            "mhKscreated.doesNotContain=" + DEFAULT_MH_KSCREATED
        );
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom equals to
        defaultDMsgheaderRowFiltering("mhRegnom.equals=" + DEFAULT_MH_REGNOM, "mhRegnom.equals=" + UPDATED_MH_REGNOM);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom in
        defaultDMsgheaderRowFiltering("mhRegnom.in=" + DEFAULT_MH_REGNOM + "," + UPDATED_MH_REGNOM, "mhRegnom.in=" + UPDATED_MH_REGNOM);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom is not null
        defaultDMsgheaderRowFiltering("mhRegnom.specified=true", "mhRegnom.specified=false");
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom is greater than or equal to
        defaultDMsgheaderRowFiltering(
            "mhRegnom.greaterThanOrEqual=" + DEFAULT_MH_REGNOM,
            "mhRegnom.greaterThanOrEqual=" + UPDATED_MH_REGNOM
        );
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom is less than or equal to
        defaultDMsgheaderRowFiltering("mhRegnom.lessThanOrEqual=" + DEFAULT_MH_REGNOM, "mhRegnom.lessThanOrEqual=" + SMALLER_MH_REGNOM);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom is less than
        defaultDMsgheaderRowFiltering("mhRegnom.lessThan=" + UPDATED_MH_REGNOM, "mhRegnom.lessThan=" + DEFAULT_MH_REGNOM);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhRegnomIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhRegnom is greater than
        defaultDMsgheaderRowFiltering("mhRegnom.greaterThan=" + SMALLER_MH_REGNOM, "mhRegnom.greaterThan=" + DEFAULT_MH_REGNOM);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhVfsrefnoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhVfsrefno equals to
        defaultDMsgheaderRowFiltering("mhVfsrefno.equals=" + DEFAULT_MH_VFSREFNO, "mhVfsrefno.equals=" + UPDATED_MH_VFSREFNO);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhVfsrefnoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhVfsrefno in
        defaultDMsgheaderRowFiltering(
            "mhVfsrefno.in=" + DEFAULT_MH_VFSREFNO + "," + UPDATED_MH_VFSREFNO,
            "mhVfsrefno.in=" + UPDATED_MH_VFSREFNO
        );
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhVfsrefnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhVfsrefno is not null
        defaultDMsgheaderRowFiltering("mhVfsrefno.specified=true", "mhVfsrefno.specified=false");
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhVfsrefnoContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhVfsrefno contains
        defaultDMsgheaderRowFiltering("mhVfsrefno.contains=" + DEFAULT_MH_VFSREFNO, "mhVfsrefno.contains=" + UPDATED_MH_VFSREFNO);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhVfsrefnoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhVfsrefno does not contain
        defaultDMsgheaderRowFiltering(
            "mhVfsrefno.doesNotContain=" + UPDATED_MH_VFSREFNO,
            "mhVfsrefno.doesNotContain=" + DEFAULT_MH_VFSREFNO
        );
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhUseraIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhUsera equals to
        defaultDMsgheaderRowFiltering("mhUsera.equals=" + DEFAULT_MH_USERA, "mhUsera.equals=" + UPDATED_MH_USERA);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhUseraIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhUsera in
        defaultDMsgheaderRowFiltering("mhUsera.in=" + DEFAULT_MH_USERA + "," + UPDATED_MH_USERA, "mhUsera.in=" + UPDATED_MH_USERA);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhUseraIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhUsera is not null
        defaultDMsgheaderRowFiltering("mhUsera.specified=true", "mhUsera.specified=false");
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhUseraContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhUsera contains
        defaultDMsgheaderRowFiltering("mhUsera.contains=" + DEFAULT_MH_USERA, "mhUsera.contains=" + UPDATED_MH_USERA);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhUseraNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhUsera does not contain
        defaultDMsgheaderRowFiltering("mhUsera.doesNotContain=" + UPDATED_MH_USERA, "mhUsera.doesNotContain=" + DEFAULT_MH_USERA);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhDatvavIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhDatvav equals to
        defaultDMsgheaderRowFiltering("mhDatvav.equals=" + DEFAULT_MH_DATVAV, "mhDatvav.equals=" + UPDATED_MH_DATVAV);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhDatvavIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhDatvav in
        defaultDMsgheaderRowFiltering("mhDatvav.in=" + DEFAULT_MH_DATVAV + "," + UPDATED_MH_DATVAV, "mhDatvav.in=" + UPDATED_MH_DATVAV);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhDatvavIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhDatvav is not null
        defaultDMsgheaderRowFiltering("mhDatvav.specified=true", "mhDatvav.specified=false");
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhDatvavContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhDatvav contains
        defaultDMsgheaderRowFiltering("mhDatvav.contains=" + DEFAULT_MH_DATVAV, "mhDatvav.contains=" + UPDATED_MH_DATVAV);
    }

    @Test
    @Transactional
    void getAllDMsgheaderRowsByMhDatvavNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        // Get all the dMsgheaderRowList where mhDatvav does not contain
        defaultDMsgheaderRowFiltering("mhDatvav.doesNotContain=" + UPDATED_MH_DATVAV, "mhDatvav.doesNotContain=" + DEFAULT_MH_DATVAV);
    }

    private void defaultDMsgheaderRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDMsgheaderRowShouldBeFound(shouldBeFound);
        defaultDMsgheaderRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDMsgheaderRowShouldBeFound(String filter) throws Exception {
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dMsgheaderRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].mhKscreated").value(hasItem(DEFAULT_MH_KSCREATED)))
            .andExpect(jsonPath("$.[*].mhRegnom").value(hasItem(DEFAULT_MH_REGNOM)))
            .andExpect(jsonPath("$.[*].mhVfsrefno").value(hasItem(DEFAULT_MH_VFSREFNO)))
            .andExpect(jsonPath("$.[*].mhUsera").value(hasItem(DEFAULT_MH_USERA)))
            .andExpect(jsonPath("$.[*].mhDatvav").value(hasItem(DEFAULT_MH_DATVAV)));

        // Check, that the count call also returns 1
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDMsgheaderRowShouldNotBeFound(String filter) throws Exception {
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDMsgheaderRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDMsgheaderRow() throws Exception {
        // Get the dMsgheaderRow
        restDMsgheaderRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDMsgheaderRow() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMsgheaderRow
        DMsgheaderRow updatedDMsgheaderRow = dMsgheaderRowRepository.findById(dMsgheaderRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDMsgheaderRow are not directly saved in db
        em.detach(updatedDMsgheaderRow);
        updatedDMsgheaderRow
            .mhKscreated(UPDATED_MH_KSCREATED)
            .mhRegnom(UPDATED_MH_REGNOM)
            .mhVfsrefno(UPDATED_MH_VFSREFNO)
            .mhUsera(UPDATED_MH_USERA)
            .mhDatvav(UPDATED_MH_DATVAV);
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(updatedDMsgheaderRow);

        restDMsgheaderRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dMsgheaderRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMsgheaderRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDMsgheaderRowToMatchAllProperties(updatedDMsgheaderRow);
    }

    @Test
    @Transactional
    void putNonExistingDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dMsgheaderRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMsgheaderRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dMsgheaderRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDMsgheaderRowWithPatch() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMsgheaderRow using partial update
        DMsgheaderRow partialUpdatedDMsgheaderRow = new DMsgheaderRow();
        partialUpdatedDMsgheaderRow.setId(dMsgheaderRow.getId());

        partialUpdatedDMsgheaderRow.mhKscreated(UPDATED_MH_KSCREATED).mhRegnom(UPDATED_MH_REGNOM).mhVfsrefno(UPDATED_MH_VFSREFNO);

        restDMsgheaderRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDMsgheaderRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDMsgheaderRow))
            )
            .andExpect(status().isOk());

        // Validate the DMsgheaderRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDMsgheaderRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDMsgheaderRow, dMsgheaderRow),
            getPersistedDMsgheaderRow(dMsgheaderRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDMsgheaderRowWithPatch() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dMsgheaderRow using partial update
        DMsgheaderRow partialUpdatedDMsgheaderRow = new DMsgheaderRow();
        partialUpdatedDMsgheaderRow.setId(dMsgheaderRow.getId());

        partialUpdatedDMsgheaderRow
            .mhKscreated(UPDATED_MH_KSCREATED)
            .mhRegnom(UPDATED_MH_REGNOM)
            .mhVfsrefno(UPDATED_MH_VFSREFNO)
            .mhUsera(UPDATED_MH_USERA)
            .mhDatvav(UPDATED_MH_DATVAV);

        restDMsgheaderRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDMsgheaderRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDMsgheaderRow))
            )
            .andExpect(status().isOk());

        // Validate the DMsgheaderRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDMsgheaderRowUpdatableFieldsEquals(partialUpdatedDMsgheaderRow, getPersistedDMsgheaderRow(partialUpdatedDMsgheaderRow));
    }

    @Test
    @Transactional
    void patchNonExistingDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dMsgheaderRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dMsgheaderRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dMsgheaderRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDMsgheaderRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dMsgheaderRow.setId(longCount.incrementAndGet());

        // Create the DMsgheaderRow
        DMsgheaderRowDTO dMsgheaderRowDTO = dMsgheaderRowMapper.toDto(dMsgheaderRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDMsgheaderRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dMsgheaderRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DMsgheaderRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDMsgheaderRow() throws Exception {
        // Initialize the database
        insertedDMsgheaderRow = dMsgheaderRowRepository.saveAndFlush(dMsgheaderRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dMsgheaderRow
        restDMsgheaderRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dMsgheaderRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dMsgheaderRowRepository.count();
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

    protected DMsgheaderRow getPersistedDMsgheaderRow(DMsgheaderRow dMsgheaderRow) {
        return dMsgheaderRowRepository.findById(dMsgheaderRow.getId()).orElseThrow();
    }

    protected void assertPersistedDMsgheaderRowToMatchAllProperties(DMsgheaderRow expectedDMsgheaderRow) {
        assertDMsgheaderRowAllPropertiesEquals(expectedDMsgheaderRow, getPersistedDMsgheaderRow(expectedDMsgheaderRow));
    }

    protected void assertPersistedDMsgheaderRowToMatchUpdatableProperties(DMsgheaderRow expectedDMsgheaderRow) {
        assertDMsgheaderRowAllUpdatablePropertiesEquals(expectedDMsgheaderRow, getPersistedDMsgheaderRow(expectedDMsgheaderRow));
    }
}
