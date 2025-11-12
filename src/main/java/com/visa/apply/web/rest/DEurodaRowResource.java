package com.visa.apply.web.rest;

import com.visa.apply.repository.DEurodaRowRepository;
import com.visa.apply.service.DEurodaRowQueryService;
import com.visa.apply.service.DEurodaRowService;
import com.visa.apply.service.criteria.DEurodaRowCriteria;
import com.visa.apply.service.dto.DEurodaRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DEurodaRow}.
 */
@RestController
@RequestMapping("/api/d-euroda-rows")
public class DEurodaRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DEurodaRowResource.class);

    private static final String ENTITY_NAME = "dEurodaRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DEurodaRowService dEurodaRowService;

    private final DEurodaRowRepository dEurodaRowRepository;

    private final DEurodaRowQueryService dEurodaRowQueryService;

    public DEurodaRowResource(
        DEurodaRowService dEurodaRowService,
        DEurodaRowRepository dEurodaRowRepository,
        DEurodaRowQueryService dEurodaRowQueryService
    ) {
        this.dEurodaRowService = dEurodaRowService;
        this.dEurodaRowRepository = dEurodaRowRepository;
        this.dEurodaRowQueryService = dEurodaRowQueryService;
    }

    /**
     * {@code POST  /d-euroda-rows} : Create a new dEurodaRow.
     *
     * @param dEurodaRowDTO the dEurodaRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dEurodaRowDTO, or with status {@code 400 (Bad Request)} if the dEurodaRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DEurodaRowDTO> createDEurodaRow(@Valid @RequestBody DEurodaRowDTO dEurodaRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DEurodaRow : {}", dEurodaRowDTO);
        if (dEurodaRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dEurodaRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dEurodaRowDTO = dEurodaRowService.save(dEurodaRowDTO);
        return ResponseEntity.created(new URI("/api/d-euroda-rows/" + dEurodaRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dEurodaRowDTO.getId().toString()))
            .body(dEurodaRowDTO);
    }

    /**
     * {@code PUT  /d-euroda-rows/:id} : Updates an existing dEurodaRow.
     *
     * @param id the id of the dEurodaRowDTO to save.
     * @param dEurodaRowDTO the dEurodaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dEurodaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dEurodaRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dEurodaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DEurodaRowDTO> updateDEurodaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DEurodaRowDTO dEurodaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DEurodaRow : {}, {}", id, dEurodaRowDTO);
        if (dEurodaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dEurodaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dEurodaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dEurodaRowDTO = dEurodaRowService.update(dEurodaRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dEurodaRowDTO.getId().toString()))
            .body(dEurodaRowDTO);
    }

    /**
     * {@code PATCH  /d-euroda-rows/:id} : Partial updates given fields of an existing dEurodaRow, field will ignore if it is null
     *
     * @param id the id of the dEurodaRowDTO to save.
     * @param dEurodaRowDTO the dEurodaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dEurodaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dEurodaRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dEurodaRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dEurodaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DEurodaRowDTO> partialUpdateDEurodaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DEurodaRowDTO dEurodaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DEurodaRow partially : {}, {}", id, dEurodaRowDTO);
        if (dEurodaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dEurodaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dEurodaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DEurodaRowDTO> result = dEurodaRowService.partialUpdate(dEurodaRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dEurodaRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-euroda-rows} : get all the dEurodaRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dEurodaRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DEurodaRowDTO>> getAllDEurodaRows(
        DEurodaRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DEurodaRows by criteria: {}", criteria);

        Page<DEurodaRowDTO> page = dEurodaRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-euroda-rows/count} : count all the dEurodaRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDEurodaRows(DEurodaRowCriteria criteria) {
        LOG.debug("REST request to count DEurodaRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dEurodaRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-euroda-rows/:id} : get the "id" dEurodaRow.
     *
     * @param id the id of the dEurodaRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dEurodaRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DEurodaRowDTO> getDEurodaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DEurodaRow : {}", id);
        Optional<DEurodaRowDTO> dEurodaRowDTO = dEurodaRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dEurodaRowDTO);
    }

    /**
     * {@code DELETE  /d-euroda-rows/:id} : delete the "id" dEurodaRow.
     *
     * @param id the id of the dEurodaRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDEurodaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DEurodaRow : {}", id);
        dEurodaRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
