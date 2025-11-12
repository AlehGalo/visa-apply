package com.visa.apply.web.rest;

import com.visa.apply.repository.DVoitRowRepository;
import com.visa.apply.service.DVoitRowQueryService;
import com.visa.apply.service.DVoitRowService;
import com.visa.apply.service.criteria.DVoitRowCriteria;
import com.visa.apply.service.dto.DVoitRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DVoitRow}.
 */
@RestController
@RequestMapping("/api/d-voit-rows")
public class DVoitRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DVoitRowResource.class);

    private static final String ENTITY_NAME = "dVoitRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DVoitRowService dVoitRowService;

    private final DVoitRowRepository dVoitRowRepository;

    private final DVoitRowQueryService dVoitRowQueryService;

    public DVoitRowResource(
        DVoitRowService dVoitRowService,
        DVoitRowRepository dVoitRowRepository,
        DVoitRowQueryService dVoitRowQueryService
    ) {
        this.dVoitRowService = dVoitRowService;
        this.dVoitRowRepository = dVoitRowRepository;
        this.dVoitRowQueryService = dVoitRowQueryService;
    }

    /**
     * {@code POST  /d-voit-rows} : Create a new dVoitRow.
     *
     * @param dVoitRowDTO the dVoitRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dVoitRowDTO, or with status {@code 400 (Bad Request)} if the dVoitRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DVoitRowDTO> createDVoitRow(@Valid @RequestBody DVoitRowDTO dVoitRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DVoitRow : {}", dVoitRowDTO);
        if (dVoitRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dVoitRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dVoitRowDTO = dVoitRowService.save(dVoitRowDTO);
        return ResponseEntity.created(new URI("/api/d-voit-rows/" + dVoitRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dVoitRowDTO.getId().toString()))
            .body(dVoitRowDTO);
    }

    /**
     * {@code PUT  /d-voit-rows/:id} : Updates an existing dVoitRow.
     *
     * @param id the id of the dVoitRowDTO to save.
     * @param dVoitRowDTO the dVoitRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dVoitRowDTO,
     * or with status {@code 400 (Bad Request)} if the dVoitRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dVoitRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DVoitRowDTO> updateDVoitRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DVoitRowDTO dVoitRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DVoitRow : {}, {}", id, dVoitRowDTO);
        if (dVoitRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dVoitRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dVoitRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dVoitRowDTO = dVoitRowService.update(dVoitRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dVoitRowDTO.getId().toString()))
            .body(dVoitRowDTO);
    }

    /**
     * {@code PATCH  /d-voit-rows/:id} : Partial updates given fields of an existing dVoitRow, field will ignore if it is null
     *
     * @param id the id of the dVoitRowDTO to save.
     * @param dVoitRowDTO the dVoitRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dVoitRowDTO,
     * or with status {@code 400 (Bad Request)} if the dVoitRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dVoitRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dVoitRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DVoitRowDTO> partialUpdateDVoitRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DVoitRowDTO dVoitRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DVoitRow partially : {}, {}", id, dVoitRowDTO);
        if (dVoitRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dVoitRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dVoitRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DVoitRowDTO> result = dVoitRowService.partialUpdate(dVoitRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dVoitRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-voit-rows} : get all the dVoitRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dVoitRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DVoitRowDTO>> getAllDVoitRows(
        DVoitRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DVoitRows by criteria: {}", criteria);

        Page<DVoitRowDTO> page = dVoitRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-voit-rows/count} : count all the dVoitRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDVoitRows(DVoitRowCriteria criteria) {
        LOG.debug("REST request to count DVoitRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dVoitRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-voit-rows/:id} : get the "id" dVoitRow.
     *
     * @param id the id of the dVoitRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dVoitRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DVoitRowDTO> getDVoitRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DVoitRow : {}", id);
        Optional<DVoitRowDTO> dVoitRowDTO = dVoitRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dVoitRowDTO);
    }

    /**
     * {@code DELETE  /d-voit-rows/:id} : delete the "id" dVoitRow.
     *
     * @param id the id of the dVoitRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDVoitRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DVoitRow : {}", id);
        dVoitRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
