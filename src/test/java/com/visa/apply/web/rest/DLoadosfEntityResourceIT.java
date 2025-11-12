package com.visa.apply.web.rest;

import static com.visa.apply.domain.DLoadosfEntityAsserts.*;
import static com.visa.apply.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.apply.IntegrationTest;
import com.visa.apply.domain.DBastaRow;
import com.visa.apply.domain.DDomakinRow;
import com.visa.apply.domain.DEurodaRow;
import com.visa.apply.domain.DFingersRow;
import com.visa.apply.domain.DImagesRow;
import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.domain.DLcuzRow;
import com.visa.apply.domain.DLoadosfEntity;
import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.domain.DVoitRow;
import com.visa.apply.domain.MolbaRow;
import com.visa.apply.repository.DLoadosfEntityRepository;
import com.visa.apply.service.dto.DLoadosfEntityDTO;
import com.visa.apply.service.mapper.DLoadosfEntityMapper;
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
 * Integration tests for the {@link DLoadosfEntityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DLoadosfEntityResourceIT {

    private static final Double DEFAULT_VERSION = 1D;
    private static final Double UPDATED_VERSION = 2D;
    private static final Double SMALLER_VERSION = 1D - 1D;

    private static final String ENTITY_API_URL = "/api/d-loadosf-entities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DLoadosfEntityRepository dLoadosfEntityRepository;

    @Autowired
    private DLoadosfEntityMapper dLoadosfEntityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDLoadosfEntityMockMvc;

    private DLoadosfEntity dLoadosfEntity;

    private DLoadosfEntity insertedDLoadosfEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLoadosfEntity createEntity() {
        return new DLoadosfEntity().version(DEFAULT_VERSION);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DLoadosfEntity createUpdatedEntity() {
        return new DLoadosfEntity().version(UPDATED_VERSION);
    }

    @BeforeEach
    void initTest() {
        dLoadosfEntity = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDLoadosfEntity != null) {
            dLoadosfEntityRepository.delete(insertedDLoadosfEntity);
            insertedDLoadosfEntity = null;
        }
    }

    @Test
    @Transactional
    void createDLoadosfEntity() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);
        var returnedDLoadosfEntityDTO = om.readValue(
            restDLoadosfEntityMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLoadosfEntityDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DLoadosfEntityDTO.class
        );

        // Validate the DLoadosfEntity in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDLoadosfEntity = dLoadosfEntityMapper.toEntity(returnedDLoadosfEntityDTO);
        assertDLoadosfEntityUpdatableFieldsEquals(returnedDLoadosfEntity, getPersistedDLoadosfEntity(returnedDLoadosfEntity));

        insertedDLoadosfEntity = returnedDLoadosfEntity;
    }

    @Test
    @Transactional
    void createDLoadosfEntityWithExistingId() throws Exception {
        // Create the DLoadosfEntity with an existing ID
        dLoadosfEntity.setId(1L);
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDLoadosfEntityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLoadosfEntityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntities() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLoadosfEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }

    @Test
    @Transactional
    void getDLoadosfEntity() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get the dLoadosfEntity
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL_ID, dLoadosfEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dLoadosfEntity.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }

    @Test
    @Transactional
    void getDLoadosfEntitiesByIdFiltering() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        Long id = dLoadosfEntity.getId();

        defaultDLoadosfEntityFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDLoadosfEntityFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDLoadosfEntityFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version equals to
        defaultDLoadosfEntityFiltering("version.equals=" + DEFAULT_VERSION, "version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version in
        defaultDLoadosfEntityFiltering("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION, "version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version is not null
        defaultDLoadosfEntityFiltering("version.specified=true", "version.specified=false");
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version is greater than or equal to
        defaultDLoadosfEntityFiltering("version.greaterThanOrEqual=" + DEFAULT_VERSION, "version.greaterThanOrEqual=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version is less than or equal to
        defaultDLoadosfEntityFiltering("version.lessThanOrEqual=" + DEFAULT_VERSION, "version.lessThanOrEqual=" + SMALLER_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version is less than
        defaultDLoadosfEntityFiltering("version.lessThan=" + UPDATED_VERSION, "version.lessThan=" + DEFAULT_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVersionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        // Get all the dLoadosfEntityList where version is greater than
        defaultDLoadosfEntityFiltering("version.greaterThan=" + SMALLER_VERSION, "version.greaterThan=" + DEFAULT_VERSION);
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByMsgheaderIsEqualToSomething() throws Exception {
        DMsgheaderRow msgheader;
        if (TestUtil.findAll(em, DMsgheaderRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            msgheader = DMsgheaderRowResourceIT.createEntity();
        } else {
            msgheader = TestUtil.findAll(em, DMsgheaderRow.class).get(0);
        }
        em.persist(msgheader);
        em.flush();
        dLoadosfEntity.setMsgheader(msgheader);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long msgheaderId = msgheader.getId();
        // Get all the dLoadosfEntityList where msgheader equals to msgheaderId
        defaultDLoadosfEntityShouldBeFound("msgheaderId.equals=" + msgheaderId);

        // Get all the dLoadosfEntityList where msgheader equals to (msgheaderId + 1)
        defaultDLoadosfEntityShouldNotBeFound("msgheaderId.equals=" + (msgheaderId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByLcuzIsEqualToSomething() throws Exception {
        DLcuzRow lcuz;
        if (TestUtil.findAll(em, DLcuzRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            lcuz = DLcuzRowResourceIT.createEntity();
        } else {
            lcuz = TestUtil.findAll(em, DLcuzRow.class).get(0);
        }
        em.persist(lcuz);
        em.flush();
        dLoadosfEntity.setLcuz(lcuz);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long lcuzId = lcuz.getId();
        // Get all the dLoadosfEntityList where lcuz equals to lcuzId
        defaultDLoadosfEntityShouldBeFound("lcuzId.equals=" + lcuzId);

        // Get all the dLoadosfEntityList where lcuz equals to (lcuzId + 1)
        defaultDLoadosfEntityShouldNotBeFound("lcuzId.equals=" + (lcuzId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByLcdopIsEqualToSomething() throws Exception {
        DLcdopRow lcdop;
        if (TestUtil.findAll(em, DLcdopRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            lcdop = DLcdopRowResourceIT.createEntity();
        } else {
            lcdop = TestUtil.findAll(em, DLcdopRow.class).get(0);
        }
        em.persist(lcdop);
        em.flush();
        dLoadosfEntity.setLcdop(lcdop);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long lcdopId = lcdop.getId();
        // Get all the dLoadosfEntityList where lcdop equals to lcdopId
        defaultDLoadosfEntityShouldBeFound("lcdopId.equals=" + lcdopId);

        // Get all the dLoadosfEntityList where lcdop equals to (lcdopId + 1)
        defaultDLoadosfEntityShouldNotBeFound("lcdopId.equals=" + (lcdopId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByBastaEntityIsEqualToSomething() throws Exception {
        DBastaRow bastaEntity;
        if (TestUtil.findAll(em, DBastaRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            bastaEntity = DBastaRowResourceIT.createEntity();
        } else {
            bastaEntity = TestUtil.findAll(em, DBastaRow.class).get(0);
        }
        em.persist(bastaEntity);
        em.flush();
        dLoadosfEntity.setBastaEntity(bastaEntity);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long bastaEntityId = bastaEntity.getId();
        // Get all the dLoadosfEntityList where bastaEntity equals to bastaEntityId
        defaultDLoadosfEntityShouldBeFound("bastaEntityId.equals=" + bastaEntityId);

        // Get all the dLoadosfEntityList where bastaEntity equals to (bastaEntityId + 1)
        defaultDLoadosfEntityShouldNotBeFound("bastaEntityId.equals=" + (bastaEntityId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByMaikaIsEqualToSomething() throws Exception {
        DMaikaRow maika;
        if (TestUtil.findAll(em, DMaikaRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            maika = DMaikaRowResourceIT.createEntity();
        } else {
            maika = TestUtil.findAll(em, DMaikaRow.class).get(0);
        }
        em.persist(maika);
        em.flush();
        dLoadosfEntity.setMaika(maika);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long maikaId = maika.getId();
        // Get all the dLoadosfEntityList where maika equals to maikaId
        defaultDLoadosfEntityShouldBeFound("maikaId.equals=" + maikaId);

        // Get all the dLoadosfEntityList where maika equals to (maikaId + 1)
        defaultDLoadosfEntityShouldNotBeFound("maikaId.equals=" + (maikaId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesBySaprugaIsEqualToSomething() throws Exception {
        DSaprugaRow sapruga;
        if (TestUtil.findAll(em, DSaprugaRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            sapruga = DSaprugaRowResourceIT.createEntity();
        } else {
            sapruga = TestUtil.findAll(em, DSaprugaRow.class).get(0);
        }
        em.persist(sapruga);
        em.flush();
        dLoadosfEntity.setSapruga(sapruga);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long saprugaId = sapruga.getId();
        // Get all the dLoadosfEntityList where sapruga equals to saprugaId
        defaultDLoadosfEntityShouldBeFound("saprugaId.equals=" + saprugaId);

        // Get all the dLoadosfEntityList where sapruga equals to (saprugaId + 1)
        defaultDLoadosfEntityShouldNotBeFound("saprugaId.equals=" + (saprugaId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByMolbaIsEqualToSomething() throws Exception {
        MolbaRow molba;
        if (TestUtil.findAll(em, MolbaRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            molba = MolbaRowResourceIT.createEntity();
        } else {
            molba = TestUtil.findAll(em, MolbaRow.class).get(0);
        }
        em.persist(molba);
        em.flush();
        dLoadosfEntity.setMolba(molba);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long molbaId = molba.getId();
        // Get all the dLoadosfEntityList where molba equals to molbaId
        defaultDLoadosfEntityShouldBeFound("molbaId.equals=" + molbaId);

        // Get all the dLoadosfEntityList where molba equals to (molbaId + 1)
        defaultDLoadosfEntityShouldNotBeFound("molbaId.equals=" + (molbaId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByDomakinIsEqualToSomething() throws Exception {
        DDomakinRow domakin;
        if (TestUtil.findAll(em, DDomakinRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            domakin = DDomakinRowResourceIT.createEntity();
        } else {
            domakin = TestUtil.findAll(em, DDomakinRow.class).get(0);
        }
        em.persist(domakin);
        em.flush();
        dLoadosfEntity.setDomakin(domakin);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long domakinId = domakin.getId();
        // Get all the dLoadosfEntityList where domakin equals to domakinId
        defaultDLoadosfEntityShouldBeFound("domakinId.equals=" + domakinId);

        // Get all the dLoadosfEntityList where domakin equals to (domakinId + 1)
        defaultDLoadosfEntityShouldNotBeFound("domakinId.equals=" + (domakinId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByEurodaIsEqualToSomething() throws Exception {
        DEurodaRow euroda;
        if (TestUtil.findAll(em, DEurodaRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            euroda = DEurodaRowResourceIT.createEntity();
        } else {
            euroda = TestUtil.findAll(em, DEurodaRow.class).get(0);
        }
        em.persist(euroda);
        em.flush();
        dLoadosfEntity.setEuroda(euroda);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long eurodaId = euroda.getId();
        // Get all the dLoadosfEntityList where euroda equals to eurodaId
        defaultDLoadosfEntityShouldBeFound("eurodaId.equals=" + eurodaId);

        // Get all the dLoadosfEntityList where euroda equals to (eurodaId + 1)
        defaultDLoadosfEntityShouldNotBeFound("eurodaId.equals=" + (eurodaId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByVoitIsEqualToSomething() throws Exception {
        DVoitRow voit;
        if (TestUtil.findAll(em, DVoitRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            voit = DVoitRowResourceIT.createEntity();
        } else {
            voit = TestUtil.findAll(em, DVoitRow.class).get(0);
        }
        em.persist(voit);
        em.flush();
        dLoadosfEntity.setVoit(voit);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long voitId = voit.getId();
        // Get all the dLoadosfEntityList where voit equals to voitId
        defaultDLoadosfEntityShouldBeFound("voitId.equals=" + voitId);

        // Get all the dLoadosfEntityList where voit equals to (voitId + 1)
        defaultDLoadosfEntityShouldNotBeFound("voitId.equals=" + (voitId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByImagesIsEqualToSomething() throws Exception {
        DImagesRow images;
        if (TestUtil.findAll(em, DImagesRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            images = DImagesRowResourceIT.createEntity();
        } else {
            images = TestUtil.findAll(em, DImagesRow.class).get(0);
        }
        em.persist(images);
        em.flush();
        dLoadosfEntity.setImages(images);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long imagesId = images.getId();
        // Get all the dLoadosfEntityList where images equals to imagesId
        defaultDLoadosfEntityShouldBeFound("imagesId.equals=" + imagesId);

        // Get all the dLoadosfEntityList where images equals to (imagesId + 1)
        defaultDLoadosfEntityShouldNotBeFound("imagesId.equals=" + (imagesId + 1));
    }

    @Test
    @Transactional
    void getAllDLoadosfEntitiesByFingersIsEqualToSomething() throws Exception {
        DFingersRow fingers;
        if (TestUtil.findAll(em, DFingersRow.class).isEmpty()) {
            dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
            fingers = DFingersRowResourceIT.createEntity();
        } else {
            fingers = TestUtil.findAll(em, DFingersRow.class).get(0);
        }
        em.persist(fingers);
        em.flush();
        dLoadosfEntity.setFingers(fingers);
        dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);
        Long fingersId = fingers.getId();
        // Get all the dLoadosfEntityList where fingers equals to fingersId
        defaultDLoadosfEntityShouldBeFound("fingersId.equals=" + fingersId);

        // Get all the dLoadosfEntityList where fingers equals to (fingersId + 1)
        defaultDLoadosfEntityShouldNotBeFound("fingersId.equals=" + (fingersId + 1));
    }

    private void defaultDLoadosfEntityFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDLoadosfEntityShouldBeFound(shouldBeFound);
        defaultDLoadosfEntityShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDLoadosfEntityShouldBeFound(String filter) throws Exception {
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dLoadosfEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));

        // Check, that the count call also returns 1
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDLoadosfEntityShouldNotBeFound(String filter) throws Exception {
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDLoadosfEntityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDLoadosfEntity() throws Exception {
        // Get the dLoadosfEntity
        restDLoadosfEntityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDLoadosfEntity() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLoadosfEntity
        DLoadosfEntity updatedDLoadosfEntity = dLoadosfEntityRepository.findById(dLoadosfEntity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDLoadosfEntity are not directly saved in db
        em.detach(updatedDLoadosfEntity);
        updatedDLoadosfEntity.version(UPDATED_VERSION);
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(updatedDLoadosfEntity);

        restDLoadosfEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLoadosfEntityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLoadosfEntityDTO))
            )
            .andExpect(status().isOk());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDLoadosfEntityToMatchAllProperties(updatedDLoadosfEntity);
    }

    @Test
    @Transactional
    void putNonExistingDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dLoadosfEntityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLoadosfEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dLoadosfEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dLoadosfEntityDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDLoadosfEntityWithPatch() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLoadosfEntity using partial update
        DLoadosfEntity partialUpdatedDLoadosfEntity = new DLoadosfEntity();
        partialUpdatedDLoadosfEntity.setId(dLoadosfEntity.getId());

        partialUpdatedDLoadosfEntity.version(UPDATED_VERSION);

        restDLoadosfEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLoadosfEntity.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLoadosfEntity))
            )
            .andExpect(status().isOk());

        // Validate the DLoadosfEntity in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLoadosfEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDLoadosfEntity, dLoadosfEntity),
            getPersistedDLoadosfEntity(dLoadosfEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateDLoadosfEntityWithPatch() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dLoadosfEntity using partial update
        DLoadosfEntity partialUpdatedDLoadosfEntity = new DLoadosfEntity();
        partialUpdatedDLoadosfEntity.setId(dLoadosfEntity.getId());

        partialUpdatedDLoadosfEntity.version(UPDATED_VERSION);

        restDLoadosfEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDLoadosfEntity.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDLoadosfEntity))
            )
            .andExpect(status().isOk());

        // Validate the DLoadosfEntity in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDLoadosfEntityUpdatableFieldsEquals(partialUpdatedDLoadosfEntity, getPersistedDLoadosfEntity(partialUpdatedDLoadosfEntity));
    }

    @Test
    @Transactional
    void patchNonExistingDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dLoadosfEntityDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLoadosfEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dLoadosfEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDLoadosfEntity() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dLoadosfEntity.setId(longCount.incrementAndGet());

        // Create the DLoadosfEntity
        DLoadosfEntityDTO dLoadosfEntityDTO = dLoadosfEntityMapper.toDto(dLoadosfEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDLoadosfEntityMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dLoadosfEntityDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DLoadosfEntity in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDLoadosfEntity() throws Exception {
        // Initialize the database
        insertedDLoadosfEntity = dLoadosfEntityRepository.saveAndFlush(dLoadosfEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dLoadosfEntity
        restDLoadosfEntityMockMvc
            .perform(delete(ENTITY_API_URL_ID, dLoadosfEntity.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dLoadosfEntityRepository.count();
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

    protected DLoadosfEntity getPersistedDLoadosfEntity(DLoadosfEntity dLoadosfEntity) {
        return dLoadosfEntityRepository.findById(dLoadosfEntity.getId()).orElseThrow();
    }

    protected void assertPersistedDLoadosfEntityToMatchAllProperties(DLoadosfEntity expectedDLoadosfEntity) {
        assertDLoadosfEntityAllPropertiesEquals(expectedDLoadosfEntity, getPersistedDLoadosfEntity(expectedDLoadosfEntity));
    }

    protected void assertPersistedDLoadosfEntityToMatchUpdatableProperties(DLoadosfEntity expectedDLoadosfEntity) {
        assertDLoadosfEntityAllUpdatablePropertiesEquals(expectedDLoadosfEntity, getPersistedDLoadosfEntity(expectedDLoadosfEntity));
    }
}
