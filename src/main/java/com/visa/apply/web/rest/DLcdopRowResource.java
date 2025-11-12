package com.visa.apply.web.rest;

import com.visa.apply.repository.DLcdopRowRepository;
import com.visa.apply.service.DLcdopRowQueryService;
import com.visa.apply.service.DLcdopRowService;
import com.visa.apply.service.criteria.DLcdopRowCriteria;
import com.visa.apply.service.dto.DLcdopRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DLcdopRow}.
 */
@RestController
@RequestMapping("/api/d-lcdop-rows")
public class DLcdopRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DLcdopRowResource.class);

    private static final String ENTITY_NAME = "dLcdopRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DLcdopRowService dLcdopRowService;

    private final DLcdopRowRepository dLcdopRowRepository;

    private final DLcdopRowQueryService dLcdopRowQueryService;

    public DLcdopRowResource(
        DLcdopRowService dLcdopRowService,
        DLcdopRowRepository dLcdopRowRepository,
        DLcdopRowQueryService dLcdopRowQueryService
    ) {
        this.dLcdopRowService = dLcdopRowService;
        this.dLcdopRowRepository = dLcdopRowRepository;
        this.dLcdopRowQueryService = dLcdopRowQueryService;
    }

    /**
     * {@code POST  /d-lcdop-rows} : Create a new dLcdopRow.
     *
     * @param dLcdopRowDTO the dLcdopRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dLcdopRowDTO, or with status {@code 400 (Bad Request)} if the dLcdopRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DLcdopRowDTO> createDLcdopRow(@Valid @RequestBody DLcdopRowDTO dLcdopRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DLcdopRow : {}", dLcdopRowDTO);
        if (dLcdopRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dLcdopRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dLcdopRowDTO = dLcdopRowService.save(dLcdopRowDTO);
        return ResponseEntity.created(new URI("/api/d-lcdop-rows/" + dLcdopRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dLcdopRowDTO.getId().toString()))
            .body(dLcdopRowDTO);
    }

    /**
     * {@code PUT  /d-lcdop-rows/:id} : Updates an existing dLcdopRow.
     *
     * @param id the id of the dLcdopRowDTO to save.
     * @param dLcdopRowDTO the dLcdopRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLcdopRowDTO,
     * or with status {@code 400 (Bad Request)} if the dLcdopRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dLcdopRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DLcdopRowDTO> updateDLcdopRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DLcdopRowDTO dLcdopRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DLcdopRow : {}, {}", id, dLcdopRowDTO);
        if (dLcdopRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLcdopRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLcdopRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dLcdopRowDTO = dLcdopRowService.update(dLcdopRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLcdopRowDTO.getId().toString()))
            .body(dLcdopRowDTO);
    }

    /**
     * {@code PATCH  /d-lcdop-rows/:id} : Partial updates given fields of an existing dLcdopRow, field will ignore if it is null
     *
     * @param id the id of the dLcdopRowDTO to save.
     * @param dLcdopRowDTO the dLcdopRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dLcdopRowDTO,
     * or with status {@code 400 (Bad Request)} if the dLcdopRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dLcdopRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dLcdopRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DLcdopRowDTO> partialUpdateDLcdopRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DLcdopRowDTO dLcdopRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DLcdopRow partially : {}, {}", id, dLcdopRowDTO);
        if (dLcdopRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dLcdopRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dLcdopRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DLcdopRowDTO> result = dLcdopRowService.partialUpdate(dLcdopRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dLcdopRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-lcdop-rows} : get all the dLcdopRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dLcdopRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DLcdopRowDTO>> getAllDLcdopRows(
        DLcdopRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DLcdopRows by criteria: {}", criteria);

        Page<DLcdopRowDTO> page = dLcdopRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-lcdop-rows/count} : count all the dLcdopRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDLcdopRows(DLcdopRowCriteria criteria) {
        LOG.debug("REST request to count DLcdopRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dLcdopRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-lcdop-rows/:id} : get the "id" dLcdopRow.
     *
     * @param id the id of the dLcdopRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dLcdopRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DLcdopRowDTO> getDLcdopRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DLcdopRow : {}", id);
        Optional<DLcdopRowDTO> dLcdopRowDTO = dLcdopRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dLcdopRowDTO);
    }

    /**
     * {@code DELETE  /d-lcdop-rows/:id} : delete the "id" dLcdopRow.
     *
     * @param id the id of the dLcdopRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDLcdopRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DLcdopRow : {}", id);
        dLcdopRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
