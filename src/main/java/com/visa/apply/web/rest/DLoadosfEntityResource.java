package com.visa.apply.web.rest;

import com.visa.apply.repository.DLoadosfEntityRepository;
import com.visa.apply.service.DLoadosfEntityQueryService;
import com.visa.apply.service.DLoadosfEntityService;
import com.visa.apply.service.criteria.DLoadosfEntityCriteria;
import com.visa.apply.service.dto.DLoadosfEntityDTO;
import com.visa.apply.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.visa.apply.domain.DLoadosfEntity}.
 */
@RestController
@RequestMapping("/api/d-loadosf-entities")
public class DLoadosfEntityResource {

    private static final Logger LOG = LoggerFactory.getLogger(DLoadosfEntityResource.class);

    private static final String ENTITY_NAME = "dLoadosfEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DLoadosfEntityService dLoadosfEntityService;

    private final DLoadosfEntityRepository dLoadosfEntityRepository;

    private final DLoadosfEntityQueryService dLoadosfEntityQueryService;

    public DLoadosfEntityResource(
        DLoadosfEntityService dLoadosfEntityService,
        DLoadosfEntityRepository dLoadosfEntityRepository,
        DLoadosfEntityQueryService dLoadosfEntityQueryService
    ) {
        this.dLoadosfEntityService = dLoadosfEntityService;
        this.dLoadosfEntityRepository = dLoadosfEntityRepository;
        this.dLoadosfEntityQueryService = dLoadosfEntityQueryService;
    }

    /**
     * {@code POST  /d-loadosf-entities} : Create a new dLoadosfEntity.
     *
     * @param dLoadosfEntityDTO the dLoadosfEntityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dLoadosfEntityDTO, or with status {@code 400 (Bad Request)} if the dLoadosfEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DLoadosfEntityDTO> createDLoadosfEntity(@RequestBody DLoadosfEntityDTO dLoadosfEntityDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DLoadosfEntity : {}", dLoadosfEntityDTO);
        if (dLoadosfEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new dLoadosfEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dLoadosfEntityDTO = dLoadosfEntityService.save(dLoadosfEntityDTO);
        return ResponseEntity.created(new URI("/api/d-loadosf-entities/" + dLoadosfEntityDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dLoadosfEntityDTO.getId().toString()))
            .body(dLoadosfEntityDTO);
    }

    /**
     * {@code PUT  /d-loadosf-entities/:id} : Updates an existing dLoadosfEntity.
     *
     * @param id the id of the dLoadosfEntityDTO to save.
     * @param dLoadosfEntityDTO the dLoadosfEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLoadosfEntityDTO,
     * or with status {@code 400 (Bad Request)} if the dLoadosfEntityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dLoadosfEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DLoadosfEntityDTO> updateDLoadosfEntity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DLoadosfEntityDTO dLoadosfEntityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DLoadosfEntity : {}, {}", id, dLoadosfEntityDTO);
        if (dLoadosfEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLoadosfEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLoadosfEntityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dLoadosfEntityDTO = dLoadosfEntityService.update(dLoadosfEntityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLoadosfEntityDTO.getId().toString()))
            .body(dLoadosfEntityDTO);
    }

    /**
     * {@code PATCH  /d-loadosf-entities/:id} : Partial updates given fields of an existing dLoadosfEntity, field will ignore if it is null
     *
     * @param id the id of the dLoadosfEntityDTO to save.
     * @param dLoadosfEntityDTO the dLoadosfEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLoadosfEntityDTO,
     * or with status {@code 400 (Bad Request)} if the dLoadosfEntityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dLoadosfEntityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dLoadosfEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DLoadosfEntityDTO> partialUpdateDLoadosfEntity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DLoadosfEntityDTO dLoadosfEntityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DLoadosfEntity partially : {}, {}", id, dLoadosfEntityDTO);
        if (dLoadosfEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLoadosfEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLoadosfEntityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DLoadosfEntityDTO> result = dLoadosfEntityService.partialUpdate(dLoadosfEntityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLoadosfEntityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-loadosf-entities} : get all the dLoadosfEntities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dLoadosfEntities in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DLoadosfEntityDTO>> getAllDLoadosfEntities(
        DLoadosfEntityCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DLoadosfEntities by criteria: {}", criteria);

        Page<DLoadosfEntityDTO> page = dLoadosfEntityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-loadosf-entities/count} : count all the dLoadosfEntities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDLoadosfEntities(DLoadosfEntityCriteria criteria) {
        LOG.debug("REST request to count DLoadosfEntities by criteria: {}", criteria);
        return ResponseEntity.ok().body(dLoadosfEntityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-loadosf-entities/:id} : get the "id" dLoadosfEntity.
     *
     * @param id the id of the dLoadosfEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dLoadosfEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DLoadosfEntityDTO> getDLoadosfEntity(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DLoadosfEntity : {}", id);
        Optional<DLoadosfEntityDTO> dLoadosfEntityDTO = dLoadosfEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dLoadosfEntityDTO);
    }

    /**
     * {@code DELETE  /d-loadosf-entities/:id} : delete the "id" dLoadosfEntity.
     *
     * @param id the id of the dLoadosfEntityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDLoadosfEntity(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DLoadosfEntity : {}", id);
        dLoadosfEntityService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
