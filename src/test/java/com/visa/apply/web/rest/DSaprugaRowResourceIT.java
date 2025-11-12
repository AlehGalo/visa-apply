package com.visa.apply.web.rest;

import static com.visa.apply.domain.DSaprugaRowAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.repository.DSaprugaRowRepository;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import com.visa.apply.service.mapper.DSaprugaRowMapper;
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
 * Integration tests for the {@link DSaprugaRowResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DSaprugaRowResourceIT {

    private static final String DEFAULT_SP_MRJDARJ = "AAAAAAAAAA";
    private static final String UPDATED_SP_MRJDARJ = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-sapruga-rows";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DSaprugaRowRepository dSaprugaRowRepository;

    @Autowired
    private DSaprugaRowMapper dSaprugaRowMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDSaprugaRowMockMvc;

    private DSaprugaRow dSaprugaRow;

    private DSaprugaRow insertedDSaprugaRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DSaprugaRow createEntity() {
        return new DSaprugaRow().spMrjdarj(DEFAULT_SP_MRJDARJ);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DSaprugaRow createUpdatedEntity() {
        return new DSaprugaRow().spMrjdarj(UPDATED_SP_MRJDARJ);
    }

    @BeforeEach
    void initTest() {
        dSaprugaRow = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDSaprugaRow != null) {
            dSaprugaRowRepository.delete(insertedDSaprugaRow);
            insertedDSaprugaRow = null;
        }
    }

    @Test
    @Transactional
    void createDSaprugaRow() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);
        var returnedDSaprugaRowDTO = om.readValue(
            restDSaprugaRowMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dSaprugaRowDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DSaprugaRowDTO.class
        );

        // Validate the DSaprugaRow in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDSaprugaRow = dSaprugaRowMapper.toEntity(returnedDSaprugaRowDTO);
        assertDSaprugaRowUpdatableFieldsEquals(returnedDSaprugaRow, getPersistedDSaprugaRow(returnedDSaprugaRow));

        insertedDSaprugaRow = returnedDSaprugaRow;
    }

    @Test
    @Transactional
    void createDSaprugaRowWithExistingId() throws Exception {
        // Create the DSaprugaRow with an existing ID
        dSaprugaRow.setId(1L);
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDSaprugaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dSaprugaRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSpMrjdarjIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        dSaprugaRow.setSpMrjdarj(null);

        // Create the DSaprugaRow, which fails.
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        restDSaprugaRowMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dSaprugaRowDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDSaprugaRows() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dSaprugaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].spMrjdarj").value(hasItem(DEFAULT_SP_MRJDARJ)));
    }

    @Test
    @Transactional
    void getDSaprugaRow() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get the dSaprugaRow
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL_ID, dSaprugaRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dSaprugaRow.getId().intValue()))
            .andExpect(jsonPath("$.spMrjdarj").value(DEFAULT_SP_MRJDARJ));
    }

    @Test
    @Transactional
    void getDSaprugaRowsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        Long id = dSaprugaRow.getId();

        defaultDSaprugaRowFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDSaprugaRowFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDSaprugaRowFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDSaprugaRowsBySpMrjdarjIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList where spMrjdarj equals to
        defaultDSaprugaRowFiltering("spMrjdarj.equals=" + DEFAULT_SP_MRJDARJ, "spMrjdarj.equals=" + UPDATED_SP_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDSaprugaRowsBySpMrjdarjIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList where spMrjdarj in
        defaultDSaprugaRowFiltering("spMrjdarj.in=" + DEFAULT_SP_MRJDARJ + "," + UPDATED_SP_MRJDARJ, "spMrjdarj.in=" + UPDATED_SP_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDSaprugaRowsBySpMrjdarjIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList where spMrjdarj is not null
        defaultDSaprugaRowFiltering("spMrjdarj.specified=true", "spMrjdarj.specified=false");
    }

    @Test
    @Transactional
    void getAllDSaprugaRowsBySpMrjdarjContainsSomething() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList where spMrjdarj contains
        defaultDSaprugaRowFiltering("spMrjdarj.contains=" + DEFAULT_SP_MRJDARJ, "spMrjdarj.contains=" + UPDATED_SP_MRJDARJ);
    }

    @Test
    @Transactional
    void getAllDSaprugaRowsBySpMrjdarjNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        // Get all the dSaprugaRowList where spMrjdarj does not contain
        defaultDSaprugaRowFiltering("spMrjdarj.doesNotContain=" + UPDATED_SP_MRJDARJ, "spMrjdarj.doesNotContain=" + DEFAULT_SP_MRJDARJ);
    }

    private void defaultDSaprugaRowFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDSaprugaRowShouldBeFound(shouldBeFound);
        defaultDSaprugaRowShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDSaprugaRowShouldBeFound(String filter) throws Exception {
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dSaprugaRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].spMrjdarj").value(hasItem(DEFAULT_SP_MRJDARJ)));

        // Check, that the count call also returns 1
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDSaprugaRowShouldNotBeFound(String filter) throws Exception {
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDSaprugaRowMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDSaprugaRow() throws Exception {
        // Get the dSaprugaRow
        restDSaprugaRowMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDSaprugaRow() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dSaprugaRow
        DSaprugaRow updatedDSaprugaRow = dSaprugaRowRepository.findById(dSaprugaRow.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDSaprugaRow are not directly saved in db
        em.detach(updatedDSaprugaRow);
        updatedDSaprugaRow.spMrjdarj(UPDATED_SP_MRJDARJ);
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(updatedDSaprugaRow);

        restDSaprugaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dSaprugaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dSaprugaRowDTO))
            )
            .andExpect(status().isOk());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDSaprugaRowToMatchAllProperties(updatedDSaprugaRow);
    }

    @Test
    @Transactional
    void putNonExistingDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dSaprugaRowDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dSaprugaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dSaprugaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dSaprugaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDSaprugaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dSaprugaRow using partial update
        DSaprugaRow partialUpdatedDSaprugaRow = new DSaprugaRow();
        partialUpdatedDSaprugaRow.setId(dSaprugaRow.getId());

        restDSaprugaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDSaprugaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDSaprugaRow))
            )
            .andExpect(status().isOk());

        // Validate the DSaprugaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDSaprugaRowUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDSaprugaRow, dSaprugaRow),
            getPersistedDSaprugaRow(dSaprugaRow)
        );
    }

    @Test
    @Transactional
    void fullUpdateDSaprugaRowWithPatch() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dSaprugaRow using partial update
        DSaprugaRow partialUpdatedDSaprugaRow = new DSaprugaRow();
        partialUpdatedDSaprugaRow.setId(dSaprugaRow.getId());

        partialUpdatedDSaprugaRow.spMrjdarj(UPDATED_SP_MRJDARJ);

        restDSaprugaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDSaprugaRow.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDSaprugaRow))
            )
            .andExpect(status().isOk());

        // Validate the DSaprugaRow in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDSaprugaRowUpdatableFieldsEquals(partialUpdatedDSaprugaRow, getPersistedDSaprugaRow(partialUpdatedDSaprugaRow));
    }

    @Test
    @Transactional
    void patchNonExistingDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dSaprugaRowDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dSaprugaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dSaprugaRowDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDSaprugaRow() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dSaprugaRow.setId(longCount.incrementAndGet());

        // Create the DSaprugaRow
        DSaprugaRowDTO dSaprugaRowDTO = dSaprugaRowMapper.toDto(dSaprugaRow);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDSaprugaRowMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dSaprugaRowDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DSaprugaRow in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDSaprugaRow() throws Exception {
        // Initialize the database
        insertedDSaprugaRow = dSaprugaRowRepository.saveAndFlush(dSaprugaRow);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dSaprugaRow
        restDSaprugaRowMockMvc
            .perform(delete(ENTITY_API_URL_ID, dSaprugaRow.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dSaprugaRowRepository.count();
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

    protected DSaprugaRow getPersistedDSaprugaRow(DSaprugaRow dSaprugaRow) {
        return dSaprugaRowRepository.findById(dSaprugaRow.getId()).orElseThrow();
    }

    protected void assertPersistedDSaprugaRowToMatchAllProperties(DSaprugaRow expectedDSaprugaRow) {
        assertDSaprugaRowAllPropertiesEquals(expectedDSaprugaRow, getPersistedDSaprugaRow(expectedDSaprugaRow));
    }

    protected void assertPersistedDSaprugaRowToMatchUpdatableProperties(DSaprugaRow expectedDSaprugaRow) {
        assertDSaprugaRowAllUpdatablePropertiesEquals(expectedDSaprugaRow, getPersistedDSaprugaRow(expectedDSaprugaRow));
    }
}
