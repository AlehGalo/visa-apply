package com.visa.apply.web.rest;

import com.visa.apply.repository.DMaikaRowRepository;
import com.visa.apply.service.DMaikaRowQueryService;
import com.visa.apply.service.DMaikaRowService;
import com.visa.apply.service.criteria.DMaikaRowCriteria;
import com.visa.apply.service.dto.DMaikaRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DMaikaRow}.
 */
@RestController
@RequestMapping("/api/d-maika-rows")
public class DMaikaRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DMaikaRowResource.class);

    private static final String ENTITY_NAME = "dMaikaRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DMaikaRowService dMaikaRowService;

    private final DMaikaRowRepository dMaikaRowRepository;

    private final DMaikaRowQueryService dMaikaRowQueryService;

    public DMaikaRowResource(
        DMaikaRowService dMaikaRowService,
        DMaikaRowRepository dMaikaRowRepository,
        DMaikaRowQueryService dMaikaRowQueryService
    ) {
        this.dMaikaRowService = dMaikaRowService;
        this.dMaikaRowRepository = dMaikaRowRepository;
        this.dMaikaRowQueryService = dMaikaRowQueryService;
    }

    /**
     * {@code POST  /d-maika-rows} : Create a new dMaikaRow.
     *
     * @param dMaikaRowDTO the dMaikaRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dMaikaRowDTO, or with status {@code 400 (Bad Request)} if the dMaikaRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DMaikaRowDTO> createDMaikaRow(@Valid @RequestBody DMaikaRowDTO dMaikaRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DMaikaRow : {}", dMaikaRowDTO);
        if (dMaikaRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dMaikaRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dMaikaRowDTO = dMaikaRowService.save(dMaikaRowDTO);
        return ResponseEntity.created(new URI("/api/d-maika-rows/" + dMaikaRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dMaikaRowDTO.getId().toString()))
            .body(dMaikaRowDTO);
    }

    /**
     * {@code PUT  /d-maika-rows/:id} : Updates an existing dMaikaRow.
     *
     * @param id the id of the dMaikaRowDTO to save.
     * @param dMaikaRowDTO the dMaikaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dMaikaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dMaikaRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dMaikaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DMaikaRowDTO> updateDMaikaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DMaikaRowDTO dMaikaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DMaikaRow : {}, {}", id, dMaikaRowDTO);
        if (dMaikaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dMaikaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dMaikaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dMaikaRowDTO = dMaikaRowService.update(dMaikaRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dMaikaRowDTO.getId().toString()))
            .body(dMaikaRowDTO);
    }

    /**
     * {@code PATCH  /d-maika-rows/:id} : Partial updates given fields of an existing dMaikaRow, field will ignore if it is null
     *
     * @param id the id of the dMaikaRowDTO to save.
     * @param dMaikaRowDTO the dMaikaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dMaikaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dMaikaRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dMaikaRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dMaikaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DMaikaRowDTO> partialUpdateDMaikaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DMaikaRowDTO dMaikaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DMaikaRow partially : {}, {}", id, dMaikaRowDTO);
        if (dMaikaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dMaikaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dMaikaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DMaikaRowDTO> result = dMaikaRowService.partialUpdate(dMaikaRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dMaikaRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-maika-rows} : get all the dMaikaRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dMaikaRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DMaikaRowDTO>> getAllDMaikaRows(
        DMaikaRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DMaikaRows by criteria: {}", criteria);

        Page<DMaikaRowDTO> page = dMaikaRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-maika-rows/count} : count all the dMaikaRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDMaikaRows(DMaikaRowCriteria criteria) {
        LOG.debug("REST request to count DMaikaRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dMaikaRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-maika-rows/:id} : get the "id" dMaikaRow.
     *
     * @param id the id of the dMaikaRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dMaikaRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DMaikaRowDTO> getDMaikaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DMaikaRow : {}", id);
        Optional<DMaikaRowDTO> dMaikaRowDTO = dMaikaRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dMaikaRowDTO);
    }

    /**
     * {@code DELETE  /d-maika-rows/:id} : delete the "id" dMaikaRow.
     *
     * @param id the id of the dMaikaRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDMaikaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DMaikaRow : {}", id);
        dMaikaRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
