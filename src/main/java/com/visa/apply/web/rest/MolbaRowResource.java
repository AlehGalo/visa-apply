package com.visa.apply.web.rest;

import com.visa.apply.repository.MolbaRowRepository;
import com.visa.apply.service.MolbaRowQueryService;
import com.visa.apply.service.MolbaRowService;
import com.visa.apply.service.criteria.MolbaRowCriteria;
import com.visa.apply.service.dto.MolbaRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.MolbaRow}.
 */
@RestController
@RequestMapping("/api/molba-rows")
public class MolbaRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(MolbaRowResource.class);

    private static final String ENTITY_NAME = "molbaRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MolbaRowService molbaRowService;

    private final MolbaRowRepository molbaRowRepository;

    private final MolbaRowQueryService molbaRowQueryService;

    public MolbaRowResource(
        MolbaRowService molbaRowService,
        MolbaRowRepository molbaRowRepository,
        MolbaRowQueryService molbaRowQueryService
    ) {
        this.molbaRowService = molbaRowService;
        this.molbaRowRepository = molbaRowRepository;
        this.molbaRowQueryService = molbaRowQueryService;
    }

    /**
     * {@code POST  /molba-rows} : Create a new molbaRow.
     *
     * @param molbaRowDTO the molbaRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new molbaRowDTO, or with status {@code 400 (Bad Request)} if the molbaRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MolbaRowDTO> createMolbaRow(@Valid @RequestBody MolbaRowDTO molbaRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save MolbaRow : {}", molbaRowDTO);
        if (molbaRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new molbaRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        molbaRowDTO = molbaRowService.save(molbaRowDTO);
        return ResponseEntity.created(new URI("/api/molba-rows/" + molbaRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, molbaRowDTO.getId().toString()))
            .body(molbaRowDTO);
    }

    /**
     * {@code PUT  /molba-rows/:id} : Updates an existing molbaRow.
     *
     * @param id the id of the molbaRowDTO to save.
     * @param molbaRowDTO the molbaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated molbaRowDTO,
     * or with status {@code 400 (Bad Request)} if the molbaRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the molbaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MolbaRowDTO> updateMolbaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MolbaRowDTO molbaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update MolbaRow : {}, {}", id, molbaRowDTO);
        if (molbaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, molbaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!molbaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        molbaRowDTO = molbaRowService.update(molbaRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, molbaRowDTO.getId().toString()))
            .body(molbaRowDTO);
    }

    /**
     * {@code PATCH  /molba-rows/:id} : Partial updates given fields of an existing molbaRow, field will ignore if it is null
     *
     * @param id the id of the molbaRowDTO to save.
     * @param molbaRowDTO the molbaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated molbaRowDTO,
     * or with status {@code 400 (Bad Request)} if the molbaRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the molbaRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the molbaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MolbaRowDTO> partialUpdateMolbaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MolbaRowDTO molbaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MolbaRow partially : {}, {}", id, molbaRowDTO);
        if (molbaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, molbaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!molbaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MolbaRowDTO> result = molbaRowService.partialUpdate(molbaRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, molbaRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /molba-rows} : get all the molbaRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of molbaRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MolbaRowDTO>> getAllMolbaRows(
        MolbaRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get MolbaRows by criteria: {}", criteria);

        Page<MolbaRowDTO> page = molbaRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /molba-rows/count} : count all the molbaRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countMolbaRows(MolbaRowCriteria criteria) {
        LOG.debug("REST request to count MolbaRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(molbaRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /molba-rows/:id} : get the "id" molbaRow.
     *
     * @param id the id of the molbaRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the molbaRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MolbaRowDTO> getMolbaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MolbaRow : {}", id);
        Optional<MolbaRowDTO> molbaRowDTO = molbaRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(molbaRowDTO);
    }

    /**
     * {@code DELETE  /molba-rows/:id} : delete the "id" molbaRow.
     *
     * @param id the id of the molbaRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMolbaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MolbaRow : {}", id);
        molbaRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
