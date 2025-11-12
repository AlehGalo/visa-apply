package com.visa.apply.web.rest;

import com.visa.apply.repository.DSaprugaRowRepository;
import com.visa.apply.service.DSaprugaRowQueryService;
import com.visa.apply.service.DSaprugaRowService;
import com.visa.apply.service.criteria.DSaprugaRowCriteria;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import com.visa.apply.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.visa.apply.domain.DSaprugaRow}.
 */
@RestController
@RequestMapping("/api/d-sapruga-rows")
public class DSaprugaRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DSaprugaRowResource.class);

    private static final String ENTITY_NAME = "dSaprugaRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DSaprugaRowService dSaprugaRowService;

    private final DSaprugaRowRepository dSaprugaRowRepository;

    private final DSaprugaRowQueryService dSaprugaRowQueryService;

    public DSaprugaRowResource(
        DSaprugaRowService dSaprugaRowService,
        DSaprugaRowRepository dSaprugaRowRepository,
        DSaprugaRowQueryService dSaprugaRowQueryService
    ) {
        this.dSaprugaRowService = dSaprugaRowService;
        this.dSaprugaRowRepository = dSaprugaRowRepository;
        this.dSaprugaRowQueryService = dSaprugaRowQueryService;
    }

    /**
     * {@code POST  /d-sapruga-rows} : Create a new dSaprugaRow.
     *
     * @param dSaprugaRowDTO the dSaprugaRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dSaprugaRowDTO, or with status {@code 400 (Bad Request)} if the dSaprugaRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DSaprugaRowDTO> createDSaprugaRow(@Valid @RequestBody DSaprugaRowDTO dSaprugaRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DSaprugaRow : {}", dSaprugaRowDTO);
        if (dSaprugaRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dSaprugaRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dSaprugaRowDTO = dSaprugaRowService.save(dSaprugaRowDTO);
        return ResponseEntity.created(new URI("/api/d-sapruga-rows/" + dSaprugaRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dSaprugaRowDTO.getId().toString()))
            .body(dSaprugaRowDTO);
    }

    /**
     * {@code PUT  /d-sapruga-rows/:id} : Updates an existing dSaprugaRow.
     *
     * @param id the id of the dSaprugaRowDTO to save.
     * @param dSaprugaRowDTO the dSaprugaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dSaprugaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dSaprugaRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dSaprugaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DSaprugaRowDTO> updateDSaprugaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DSaprugaRowDTO dSaprugaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DSaprugaRow : {}, {}", id, dSaprugaRowDTO);
        if (dSaprugaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dSaprugaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dSaprugaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dSaprugaRowDTO = dSaprugaRowService.update(dSaprugaRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dSaprugaRowDTO.getId().toString()))
            .body(dSaprugaRowDTO);
    }

    /**
     * {@code PATCH  /d-sapruga-rows/:id} : Partial updates given fields of an existing dSaprugaRow, field will ignore if it is null
     *
     * @param id the id of the dSaprugaRowDTO to save.
     * @param dSaprugaRowDTO the dSaprugaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dSaprugaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dSaprugaRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dSaprugaRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dSaprugaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DSaprugaRowDTO> partialUpdateDSaprugaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DSaprugaRowDTO dSaprugaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DSaprugaRow partially : {}, {}", id, dSaprugaRowDTO);
        if (dSaprugaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dSaprugaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dSaprugaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DSaprugaRowDTO> result = dSaprugaRowService.partialUpdate(dSaprugaRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dSaprugaRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-sapruga-rows} : get all the dSaprugaRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dSaprugaRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DSaprugaRowDTO>> getAllDSaprugaRows(
        DSaprugaRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DSaprugaRows by criteria: {}", criteria);

        Page<DSaprugaRowDTO> page = dSaprugaRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-sapruga-rows/count} : count all the dSaprugaRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDSaprugaRows(DSaprugaRowCriteria criteria) {
        LOG.debug("REST request to count DSaprugaRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dSaprugaRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-sapruga-rows/:id} : get the "id" dSaprugaRow.
     *
     * @param id the id of the dSaprugaRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dSaprugaRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DSaprugaRowDTO> getDSaprugaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DSaprugaRow : {}", id);
        Optional<DSaprugaRowDTO> dSaprugaRowDTO = dSaprugaRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dSaprugaRowDTO);
    }

    /**
     * {@code DELETE  /d-sapruga-rows/:id} : delete the "id" dSaprugaRow.
     *
     * @param id the id of the dSaprugaRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDSaprugaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DSaprugaRow : {}", id);
        dSaprugaRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
