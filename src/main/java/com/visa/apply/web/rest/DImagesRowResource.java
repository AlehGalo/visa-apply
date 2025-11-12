package com.visa.apply.web.rest;

import com.visa.apply.repository.DImagesRowRepository;
import com.visa.apply.service.DImagesRowQueryService;
import com.visa.apply.service.DImagesRowService;
import com.visa.apply.service.criteria.DImagesRowCriteria;
import com.visa.apply.service.dto.DImagesRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DImagesRow}.
 */
@RestController
@RequestMapping("/api/d-images-rows")
public class DImagesRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DImagesRowResource.class);

    private static final String ENTITY_NAME = "dImagesRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DImagesRowService dImagesRowService;

    private final DImagesRowRepository dImagesRowRepository;

    private final DImagesRowQueryService dImagesRowQueryService;

    public DImagesRowResource(
        DImagesRowService dImagesRowService,
        DImagesRowRepository dImagesRowRepository,
        DImagesRowQueryService dImagesRowQueryService
    ) {
        this.dImagesRowService = dImagesRowService;
        this.dImagesRowRepository = dImagesRowRepository;
        this.dImagesRowQueryService = dImagesRowQueryService;
    }

    /**
     * {@code POST  /d-images-rows} : Create a new dImagesRow.
     *
     * @param dImagesRowDTO the dImagesRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dImagesRowDTO, or with status {@code 400 (Bad Request)} if the dImagesRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DImagesRowDTO> createDImagesRow(@Valid @RequestBody DImagesRowDTO dImagesRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DImagesRow : {}", dImagesRowDTO);
        if (dImagesRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dImagesRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dImagesRowDTO = dImagesRowService.save(dImagesRowDTO);
        return ResponseEntity.created(new URI("/api/d-images-rows/" + dImagesRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dImagesRowDTO.getId().toString()))
            .body(dImagesRowDTO);
    }

    /**
     * {@code PUT  /d-images-rows/:id} : Updates an existing dImagesRow.
     *
     * @param id the id of the dImagesRowDTO to save.
     * @param dImagesRowDTO the dImagesRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dImagesRowDTO,
     * or with status {@code 400 (Bad Request)} if the dImagesRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dImagesRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DImagesRowDTO> updateDImagesRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DImagesRowDTO dImagesRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DImagesRow : {}, {}", id, dImagesRowDTO);
        if (dImagesRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dImagesRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dImagesRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dImagesRowDTO = dImagesRowService.update(dImagesRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dImagesRowDTO.getId().toString()))
            .body(dImagesRowDTO);
    }

    /**
     * {@code PATCH  /d-images-rows/:id} : Partial updates given fields of an existing dImagesRow, field will ignore if it is null
     *
     * @param id the id of the dImagesRowDTO to save.
     * @param dImagesRowDTO the dImagesRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dImagesRowDTO,
     * or with status {@code 400 (Bad Request)} if the dImagesRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dImagesRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dImagesRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DImagesRowDTO> partialUpdateDImagesRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DImagesRowDTO dImagesRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DImagesRow partially : {}, {}", id, dImagesRowDTO);
        if (dImagesRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dImagesRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dImagesRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DImagesRowDTO> result = dImagesRowService.partialUpdate(dImagesRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dImagesRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-images-rows} : get all the dImagesRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dImagesRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DImagesRowDTO>> getAllDImagesRows(
        DImagesRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DImagesRows by criteria: {}", criteria);

        Page<DImagesRowDTO> page = dImagesRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-images-rows/count} : count all the dImagesRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDImagesRows(DImagesRowCriteria criteria) {
        LOG.debug("REST request to count DImagesRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dImagesRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-images-rows/:id} : get the "id" dImagesRow.
     *
     * @param id the id of the dImagesRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dImagesRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DImagesRowDTO> getDImagesRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DImagesRow : {}", id);
        Optional<DImagesRowDTO> dImagesRowDTO = dImagesRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dImagesRowDTO);
    }

    /**
     * {@code DELETE  /d-images-rows/:id} : delete the "id" dImagesRow.
     *
     * @param id the id of the dImagesRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDImagesRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DImagesRow : {}", id);
        dImagesRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
