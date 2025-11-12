package com.visa.apply.web.rest;

import static com.visa.apply.domain.DImagesRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DImagesRow;
import com.visa.apply.repository.DImagesRowRepository;
import com.visa.apply.service.dto.DImagesRowDTO;
import com.visa.apply.service.mapper.DImagesRowMapper;
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
 * Integration tests for the {@link DImagesRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DImagesRowResourceIT {

    private static final Integer DEFAULT_IM_DEVICETYPE = 1;
    private static final Integer UPDATED_IM_DEVICETYPE = 2;
    private static final Integer SMALLER_IM_DEVICETYPE = 1 - 1;

    private static final Integer DEFAULT_IM_WIDTH = 1;
    private static final Integer UPDATED_IM_WIDTH = 2;
    private static final Integer SMALLER_IM_WIDTH = 1 - 1;

    private static final Integer DEFAULT_IM_HEIGHT = 1;
    private static final Integer UPDATED_IM_HEIGHT = 2;
    private static final Integer SMALLER_IM_HEIGHT = 1 - 1;

    private static final Integer DEFAULT_IM_IMGLEN = 1;
    private static final Integer UPDATED_IM_IMGLEN = 2;
    private static final Integer SMALLER_IM_IMGLEN = 1 - 1;

    private static final String DEFAULT_IM_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IM_IMAGE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-images-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DImagesRowRepository dImagesRowRepository;

    @Autowired
    private DImagesRowMapper dImagesRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDImagesRowMockMvc;

    private DImagesRow dImagesRow;

    private DImagesRow insertedDImagesRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DImagesRow createEntity() {
        return new DImagesRow()
            .imDevicetype(DEFAULT_IM_DEVICETYPE)
            .imWidth(DEFAULT_IM_WIDTH)
            .imHeight(DEFAULT_IM_HEIGHT)
            .imImglen(DEFAULT_IM_IMGLEN)
            .imImage(DEFAULT_IM_IMAGE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DImagesRow createUpdatedEntity() {
        return new DImagesRow()
            .imDevicetype(UPDATED_IM_DEVICETYPE)
            .imWidth(UPDATED_IM_WIDTH)
            .imHeight(UPDATED_IM_HEIGHT)
            .imImglen(UPDATED_IM_IMGLEN)
            .imImage(UPDATED_IM_IMAGE);
    }

    @BeforeEach
    void initTest() {
        dImagesRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDImagesRow != null) {
            dImagesRowRepository.delete(insertedDImagesRow);
            insertedDImagesRow = null;
        }
    }

    @Test
    @Transactional
    void createDImagesRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);
        var returnedDImagesRowDTO = om.readValue(
            restDImagesRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dImagesRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DImagesRowDTO.class
        );

        // Validate the DImagesRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDImagesRow = dImagesRowMapper.toEntity(returnedDImagesRowDTO);
        assertDImagesRowUpdatableFieldsEquals(returnedDImagesRow, getPersistedDImagesRow(returnedDImagesRow));

        insertedDImagesRow = returnedDImagesRow;
    }

    @Test
    @Transactional
    void createDImagesRowWithExistingId() throws Exception {
        // Create the DImagesRow with an existing ID
        dImagesRow.setId(1L);
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDImagesRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dImagesRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkImImageIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dImagesRow.setImImage(null);

        // Create the DImagesRow, which fails.
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        restDImagesRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dImagesRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDImagesRows() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dImagesRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].imDevicetype").value(hasItem(DEFAULT_IM_DEVICETYPE)))
            .andExpect(jsonPath("$.[*].imWidth").value(hasItem(DEFAULT_IM_WIDTH)))
            .andExpect(jsonPath("$.[*].imHeight").value(hasItem(DEFAULT_IM_HEIGHT)))
            .andExpect(jsonPath("$.[*].imImglen").value(hasItem(DEFAULT_IM_IMGLEN)))
            .andExpect(jsonPath("$.[*].imImage").value(hasItem(DEFAULT_IM_IMAGE)));
    }

    @Test
    @Transactional
    void getDImagesRow() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get the dImagesRow
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dImagesRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dImagesRow.getId().intValue()))
            .andExpect(jsonPath("$.imDevicetype").value(DEFAULT_IM_DEVICETYPE))
            .andExpect(jsonPath("$.imWidth").value(DEFAULT_IM_WIDTH))
            .andExpect(jsonPath("$.imHeight").value(DEFAULT_IM_HEIGHT))
            .andExpect(jsonPath("$.imImglen").value(DEFAULT_IM_IMGLEN))
            .andExpect(jsonPath("$.imImage").value(DEFAULT_IM_IMAGE));
    }

    @Test
    @Transactional
    void getDImagesRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        Long id = dImagesRow.getId();

        defaultDImagesRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDImagesRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDImagesRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype equals to
        defaultDImagesRowFiltering("imDevicetype.equals=" + DEFAULT_IM_DEVICETYPE, "imDevicetype.equals=" + UPDATED_IM_DEVICETYPE);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype in
        defaultDImagesRowFiltering(
            "imDevicetype.in=" + DEFAULT_IM_DEVICETYPE + "," + UPDATED_IM_DEVICETYPE,
            "imDevicetype.in=" + UPDATED_IM_DEVICETYPE
        );
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype is not null
        defaultDImagesRowFiltering("imDevicetype.specified=true", "imDevicetype.specified=false");
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype is greater than or equal to
        defaultDImagesRowFiltering(
            "imDevicetype.greaterThanOrEqual=" + DEFAULT_IM_DEVICETYPE,
            "imDevicetype.greaterThanOrEqual=" + UPDATED_IM_DEVICETYPE
        );
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype is less than or equal to
        defaultDImagesRowFiltering(
            "imDevicetype.lessThanOrEqual=" + DEFAULT_IM_DEVICETYPE,
            "imDevicetype.lessThanOrEqual=" + SMALLER_IM_DEVICETYPE
        );
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype is less than
        defaultDImagesRowFiltering("imDevicetype.lessThan=" + UPDATED_IM_DEVICETYPE, "imDevicetype.lessThan=" + DEFAULT_IM_DEVICETYPE);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImDevicetypeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imDevicetype is greater than
        defaultDImagesRowFiltering(
            "imDevicetype.greaterThan=" + SMALLER_IM_DEVICETYPE,
            "imDevicetype.greaterThan=" + DEFAULT_IM_DEVICETYPE
        );
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth equals to
        defaultDImagesRowFiltering("imWidth.equals=" + DEFAULT_IM_WIDTH, "imWidth.equals=" + UPDATED_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth in
        defaultDImagesRowFiltering("imWidth.in=" + DEFAULT_IM_WIDTH + "," + UPDATED_IM_WIDTH, "imWidth.in=" + UPDATED_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth is not null
        defaultDImagesRowFiltering("imWidth.specified=true", "imWidth.specified=false");
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth is greater than or equal to
        defaultDImagesRowFiltering("imWidth.greaterThanOrEqual=" + DEFAULT_IM_WIDTH, "imWidth.greaterThanOrEqual=" + UPDATED_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth is less than or equal to
        defaultDImagesRowFiltering("imWidth.lessThanOrEqual=" + DEFAULT_IM_WIDTH, "imWidth.lessThanOrEqual=" + SMALLER_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth is less than
        defaultDImagesRowFiltering("imWidth.lessThan=" + UPDATED_IM_WIDTH, "imWidth.lessThan=" + DEFAULT_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImWidthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imWidth is greater than
        defaultDImagesRowFiltering("imWidth.greaterThan=" + SMALLER_IM_WIDTH, "imWidth.greaterThan=" + DEFAULT_IM_WIDTH);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight equals to
        defaultDImagesRowFiltering("imHeight.equals=" + DEFAULT_IM_HEIGHT, "imHeight.equals=" + UPDATED_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight in
        defaultDImagesRowFiltering("imHeight.in=" + DEFAULT_IM_HEIGHT + "," + UPDATED_IM_HEIGHT, "imHeight.in=" + UPDATED_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight is not null
        defaultDImagesRowFiltering("imHeight.specified=true", "imHeight.specified=false");
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight is greater than or equal to
        defaultDImagesRowFiltering("imHeight.greaterThanOrEqual=" + DEFAULT_IM_HEIGHT, "imHeight.greaterThanOrEqual=" + UPDATED_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight is less than or equal to
        defaultDImagesRowFiltering("imHeight.lessThanOrEqual=" + DEFAULT_IM_HEIGHT, "imHeight.lessThanOrEqual=" + SMALLER_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight is less than
        defaultDImagesRowFiltering("imHeight.lessThan=" + UPDATED_IM_HEIGHT, "imHeight.lessThan=" + DEFAULT_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImHeightIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imHeight is greater than
        defaultDImagesRowFiltering("imHeight.greaterThan=" + SMALLER_IM_HEIGHT, "imHeight.greaterThan=" + DEFAULT_IM_HEIGHT);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen equals to
        defaultDImagesRowFiltering("imImglen.equals=" + DEFAULT_IM_IMGLEN, "imImglen.equals=" + UPDATED_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen in
        defaultDImagesRowFiltering("imImglen.in=" + DEFAULT_IM_IMGLEN + "," + UPDATED_IM_IMGLEN, "imImglen.in=" + UPDATED_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen is not null
        defaultDImagesRowFiltering("imImglen.specified=true", "imImglen.specified=false");
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen is greater than or equal to
        defaultDImagesRowFiltering("imImglen.greaterThanOrEqual=" + DEFAULT_IM_IMGLEN, "imImglen.greaterThanOrEqual=" + UPDATED_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen is less than or equal to
        defaultDImagesRowFiltering("imImglen.lessThanOrEqual=" + DEFAULT_IM_IMGLEN, "imImglen.lessThanOrEqual=" + SMALLER_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen is less than
        defaultDImagesRowFiltering("imImglen.lessThan=" + UPDATED_IM_IMGLEN, "imImglen.lessThan=" + DEFAULT_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImglenIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImglen is greater than
        defaultDImagesRowFiltering("imImglen.greaterThan=" + SMALLER_IM_IMGLEN, "imImglen.greaterThan=" + DEFAULT_IM_IMGLEN);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImageIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImage equals to
        defaultDImagesRowFiltering("imImage.equals=" + DEFAULT_IM_IMAGE, "imImage.equals=" + UPDATED_IM_IMAGE);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImageIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImage in
        defaultDImagesRowFiltering("imImage.in=" + DEFAULT_IM_IMAGE + "," + UPDATED_IM_IMAGE, "imImage.in=" + UPDATED_IM_IMAGE);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImageIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImage is not null
        defaultDImagesRowFiltering("imImage.specified=true", "imImage.specified=false");
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImageContainsSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImage contains
        defaultDImagesRowFiltering("imImage.contains=" + DEFAULT_IM_IMAGE, "imImage.contains=" + UPDATED_IM_IMAGE);
    }

    @Test
    @Transactional
    void getAllDImagesRowsByImImageNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        // Get all the dImagesRowList where imImage does not contain
        defaultDImagesRowFiltering("imImage.doesNotContain=" + UPDATED_IM_IMAGE, "imImage.doesNotContain=" + DEFAULT_IM_IMAGE);
    }

    private void defaultDImagesRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDImagesRowShouldBeFound(shouldBeFound);
        defaultDImagesRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDImagesRowShouldBeFound(String filter) throws Exception {
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dImagesRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].imDevicetype").value(hasItem(DEFAULT_IM_DEVICETYPE)))
            .andExpect(jsonPath("$.[*].imWidth").value(hasItem(DEFAULT_IM_WIDTH)))
            .andExpect(jsonPath("$.[*].imHeight").value(hasItem(DEFAULT_IM_HEIGHT)))
            .andExpect(jsonPath("$.[*].imImglen").value(hasItem(DEFAULT_IM_IMGLEN)))
            .andExpect(jsonPath("$.[*].imImage").value(hasItem(DEFAULT_IM_IMAGE)));

        // Check, that the count call also returns 1
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDImagesRowShouldNotBeFound(String filter) throws Exception {
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDImagesRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDImagesRow() throws Exception {
        // Get the dImagesRow
        restDImagesRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDImagesRow() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dImagesRow
        DImagesRow updatedDImagesRow = dImagesRowRepository.findById(dImagesRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDImagesRow are not directly saved in db
        em.detach(updatedDImagesRow);
        updatedDImagesRow
            .imDevicetype(UPDATED_IM_DEVICETYPE)
            .imWidth(UPDATED_IM_WIDTH)
            .imHeight(UPDATED_IM_HEIGHT)
            .imImglen(UPDATED_IM_IMGLEN)
            .imImage(UPDATED_IM_IMAGE);
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(updatedDImagesRow);

        restDImagesRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dImagesRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dImagesRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDImagesRowToMatchAllProperties(updatedDImagesRow);
    }

    @Test
    @Transactional
    void putNonExistingDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dImagesRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dImagesRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dImagesRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dImagesRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDImagesRowWithPatch() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dImagesRow using partial update
        DImagesRow partialUpdatedDImagesRow = new DImagesRow();
        partialUpdatedDImagesRow.setId(dImagesRow.getId());

        partialUpdatedDImagesRow.imWidth(UPDATED_IM_WIDTH).imHeight(UPDATED_IM_HEIGHT);

        restDImagesRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDImagesRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDImagesRow))
            )
            .andExpect(status().isOk());

        // Validate the DImagesRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDImagesRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDImagesRow, dImagesRow),
            getPersistedDImagesRow(dImagesRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDImagesRowWithPatch() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dImagesRow using partial update
        DImagesRow partialUpdatedDImagesRow = new DImagesRow();
        partialUpdatedDImagesRow.setId(dImagesRow.getId());

        partialUpdatedDImagesRow
            .imDevicetype(UPDATED_IM_DEVICETYPE)
            .imWidth(UPDATED_IM_WIDTH)
            .imHeight(UPDATED_IM_HEIGHT)
            .imImglen(UPDATED_IM_IMGLEN)
            .imImage(UPDATED_IM_IMAGE);

        restDImagesRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDImagesRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDImagesRow))
            )
            .andExpect(status().isOk());

        // Validate the DImagesRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDImagesRowUpdatableFieldsEquals(partialUpdatedDImagesRow, getPersistedDImagesRow(partialUpdatedDImagesRow));
    }

    @Test
    @Transactional
    void patchNonExistingDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dImagesRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dImagesRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dImagesRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDImagesRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dImagesRow.setId(longCount.incrementAndGet());

        // Create the DImagesRow
        DImagesRowDTO dImagesRowDTO = dImagesRowMapper.toDto(dImagesRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDImagesRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dImagesRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DImagesRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDImagesRow() throws Exception {
        // Initialize the database
        insertedDImagesRow = dImagesRowRepository.saveAndFlush(dImagesRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dImagesRow
        restDImagesRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dImagesRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dImagesRowRepository.count();
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

    protected DImagesRow getPersistedDImagesRow(DImagesRow dImagesRow) {
        return dImagesRowRepository.findById(dImagesRow.getId()).orElseThrow();
    }

    protected void assertPersistedDImagesRowToMatchAllProperties(DImagesRow expectedDImagesRow) {
        assertDImagesRowAllPropertiesEquals(expectedDImagesRow, getPersistedDImagesRow(expectedDImagesRow));
    }

    protected void assertPersistedDImagesRowToMatchUpdatableProperties(DImagesRow expectedDImagesRow) {
        assertDImagesRowAllUpdatablePropertiesEquals(expectedDImagesRow, getPersistedDImagesRow(expectedDImagesRow));
    }
}
