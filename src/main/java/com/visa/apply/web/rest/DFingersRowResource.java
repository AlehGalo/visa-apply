package com.visa.apply.web.rest;

import com.visa.apply.repository.DFingersRowRepository;
import com.visa.apply.service.DFingersRowQueryService;
import com.visa.apply.service.DFingersRowService;
import com.visa.apply.service.criteria.DFingersRowCriteria;
import com.visa.apply.service.dto.DFingersRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DFingersRow}.
 */
@RestController
@RequestMapping("/api/d-fingers-rows")
public class DFingersRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DFingersRowResource.class);

    private static final String ENTITY_NAME = "dFingersRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DFingersRowService dFingersRowService;

    private final DFingersRowRepository dFingersRowRepository;

    private final DFingersRowQueryService dFingersRowQueryService;

    public DFingersRowResource(
        DFingersRowService dFingersRowService,
        DFingersRowRepository dFingersRowRepository,
        DFingersRowQueryService dFingersRowQueryService
    ) {
        this.dFingersRowService = dFingersRowService;
        this.dFingersRowRepository = dFingersRowRepository;
        this.dFingersRowQueryService = dFingersRowQueryService;
    }

    /**
     * {@code POST  /d-fingers-rows} : Create a new dFingersRow.
     *
     * @param dFingersRowDTO the dFingersRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dFingersRowDTO, or with status {@code 400 (Bad Request)} if the dFingersRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DFingersRowDTO> createDFingersRow(@Valid @RequestBody DFingersRowDTO dFingersRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DFingersRow : {}", dFingersRowDTO);
        if (dFingersRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dFingersRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dFingersRowDTO = dFingersRowService.save(dFingersRowDTO);
        return ResponseEntity.created(new URI("/api/d-fingers-rows/" + dFingersRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dFingersRowDTO.getId().toString()))
            .body(dFingersRowDTO);
    }

    /**
     * {@code PUT  /d-fingers-rows/:id} : Updates an existing dFingersRow.
     *
     * @param id the id of the dFingersRowDTO to save.
     * @param dFingersRowDTO the dFingersRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dFingersRowDTO,
     * or with status {@code 400 (Bad Request)} if the dFingersRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dFingersRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DFingersRowDTO> updateDFingersRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DFingersRowDTO dFingersRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DFingersRow : {}, {}", id, dFingersRowDTO);
        if (dFingersRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dFingersRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dFingersRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dFingersRowDTO = dFingersRowService.update(dFingersRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dFingersRowDTO.getId().toString()))
            .body(dFingersRowDTO);
    }

    /**
     * {@code PATCH  /d-fingers-rows/:id} : Partial updates given fields of an existing dFingersRow, field will ignore if it is null
     *
     * @param id the id of the dFingersRowDTO to save.
     * @param dFingersRowDTO the dFingersRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dFingersRowDTO,
     * or with status {@code 400 (Bad Request)} if the dFingersRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dFingersRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dFingersRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DFingersRowDTO> partialUpdateDFingersRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DFingersRowDTO dFingersRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DFingersRow partially : {}, {}", id, dFingersRowDTO);
        if (dFingersRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dFingersRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dFingersRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DFingersRowDTO> result = dFingersRowService.partialUpdate(dFingersRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dFingersRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-fingers-rows} : get all the dFingersRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dFingersRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DFingersRowDTO>> getAllDFingersRows(
        DFingersRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DFingersRows by criteria: {}", criteria);

        Page<DFingersRowDTO> page = dFingersRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-fingers-rows/count} : count all the dFingersRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDFingersRows(DFingersRowCriteria criteria) {
        LOG.debug("REST request to count DFingersRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dFingersRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-fingers-rows/:id} : get the "id" dFingersRow.
     *
     * @param id the id of the dFingersRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dFingersRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DFingersRowDTO> getDFingersRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DFingersRow : {}", id);
        Optional<DFingersRowDTO> dFingersRowDTO = dFingersRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dFingersRowDTO);
    }

    /**
     * {@code DELETE  /d-fingers-rows/:id} : delete the "id" dFingersRow.
     *
     * @param id the id of the dFingersRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDFingersRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DFingersRow : {}", id);
        dFingersRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
