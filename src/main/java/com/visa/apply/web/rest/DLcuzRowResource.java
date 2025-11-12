package com.visa.apply.web.rest;

import com.visa.apply.repository.DLcuzRowRepository;
import com.visa.apply.service.DLcuzRowQueryService;
import com.visa.apply.service.DLcuzRowService;
import com.visa.apply.service.criteria.DLcuzRowCriteria;
import com.visa.apply.service.dto.DLcuzRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DLcuzRow}.
 */
@RestController
@RequestMapping("/api/d-lcuz-rows")
public class DLcuzRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DLcuzRowResource.class);

    private static final String ENTITY_NAME = "dLcuzRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DLcuzRowService dLcuzRowService;

    private final DLcuzRowRepository dLcuzRowRepository;

    private final DLcuzRowQueryService dLcuzRowQueryService;

    public DLcuzRowResource(
        DLcuzRowService dLcuzRowService,
        DLcuzRowRepository dLcuzRowRepository,
        DLcuzRowQueryService dLcuzRowQueryService
    ) {
        this.dLcuzRowService = dLcuzRowService;
        this.dLcuzRowRepository = dLcuzRowRepository;
        this.dLcuzRowQueryService = dLcuzRowQueryService;
    }

    /**
     * {@code POST  /d-lcuz-rows} : Create a new dLcuzRow.
     *
     * @param dLcuzRowDTO the dLcuzRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dLcuzRowDTO, or with status {@code 400 (Bad Request)} if the dLcuzRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DLcuzRowDTO> createDLcuzRow(@Valid @RequestBody DLcuzRowDTO dLcuzRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DLcuzRow : {}", dLcuzRowDTO);
        if (dLcuzRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dLcuzRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dLcuzRowDTO = dLcuzRowService.save(dLcuzRowDTO);
        return ResponseEntity.created(new URI("/api/d-lcuz-rows/" + dLcuzRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dLcuzRowDTO.getId().toString()))
            .body(dLcuzRowDTO);
    }

    /**
     * {@code PUT  /d-lcuz-rows/:id} : Updates an existing dLcuzRow.
     *
     * @param id the id of the dLcuzRowDTO to save.
     * @param dLcuzRowDTO the dLcuzRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLcuzRowDTO,
     * or with status {@code 400 (Bad Request)} if the dLcuzRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dLcuzRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DLcuzRowDTO> updateDLcuzRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DLcuzRowDTO dLcuzRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DLcuzRow : {}, {}", id, dLcuzRowDTO);
        if (dLcuzRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLcuzRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLcuzRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dLcuzRowDTO = dLcuzRowService.update(dLcuzRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLcuzRowDTO.getId().toString()))
            .body(dLcuzRowDTO);
    }

    /**
     * {@code PATCH  /d-lcuz-rows/:id} : Partial updates given fields of an existing dLcuzRow, field will ignore if it is null
     *
     * @param id the id of the dLcuzRowDTO to save.
     * @param dLcuzRowDTO the dLcuzRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLcuzRowDTO,
     * or with status {@code 400 (Bad Request)} if the dLcuzRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dLcuzRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dLcuzRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DLcuzRowDTO> partialUpdateDLcuzRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DLcuzRowDTO dLcuzRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DLcuzRow partially : {}, {}", id, dLcuzRowDTO);
        if (dLcuzRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLcuzRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLcuzRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DLcuzRowDTO> result = dLcuzRowService.partialUpdate(dLcuzRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLcuzRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-lcuz-rows} : get all the dLcuzRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dLcuzRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DLcuzRowDTO>> getAllDLcuzRows(
        DLcuzRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DLcuzRows by criteria: {}", criteria);

        Page<DLcuzRowDTO> page = dLcuzRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-lcuz-rows/count} : count all the dLcuzRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDLcuzRows(DLcuzRowCriteria criteria) {
        LOG.debug("REST request to count DLcuzRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dLcuzRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-lcuz-rows/:id} : get the "id" dLcuzRow.
     *
     * @param id the id of the dLcuzRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dLcuzRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DLcuzRowDTO> getDLcuzRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DLcuzRow : {}", id);
        Optional<DLcuzRowDTO> dLcuzRowDTO = dLcuzRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dLcuzRowDTO);
    }

    /**
     * {@code DELETE  /d-lcuz-rows/:id} : delete the "id" dLcuzRow.
     *
     * @param id the id of the dLcuzRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDLcuzRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DLcuzRow : {}", id);
        dLcuzRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
