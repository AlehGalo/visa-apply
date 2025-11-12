package com.visa.apply.web.rest;

import com.visa.apply.repository.DBastaRowRepository;
import com.visa.apply.service.DBastaRowQueryService;
import com.visa.apply.service.DBastaRowService;
import com.visa.apply.service.criteria.DBastaRowCriteria;
import com.visa.apply.service.dto.DBastaRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DBastaRow}.
 */
@RestController
@RequestMapping("/api/d-basta-rows")
public class DBastaRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DBastaRowResource.class);

    private static final String ENTITY_NAME = "dBastaRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DBastaRowService dBastaRowService;

    private final DBastaRowRepository dBastaRowRepository;

    private final DBastaRowQueryService dBastaRowQueryService;

    public DBastaRowResource(
        DBastaRowService dBastaRowService,
        DBastaRowRepository dBastaRowRepository,
        DBastaRowQueryService dBastaRowQueryService
    ) {
        this.dBastaRowService = dBastaRowService;
        this.dBastaRowRepository = dBastaRowRepository;
        this.dBastaRowQueryService = dBastaRowQueryService;
    }

    /**
     * {@code POST  /d-basta-rows} : Create a new dBastaRow.
     *
     * @param dBastaRowDTO the dBastaRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dBastaRowDTO, or with status {@code 400 (Bad Request)} if the dBastaRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DBastaRowDTO> createDBastaRow(@Valid @RequestBody DBastaRowDTO dBastaRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DBastaRow : {}", dBastaRowDTO);
        if (dBastaRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dBastaRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dBastaRowDTO = dBastaRowService.save(dBastaRowDTO);
        return ResponseEntity.created(new URI("/api/d-basta-rows/" + dBastaRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dBastaRowDTO.getId().toString()))
            .body(dBastaRowDTO);
    }

    /**
     * {@code PUT  /d-basta-rows/:id} : Updates an existing dBastaRow.
     *
     * @param id the id of the dBastaRowDTO to save.
     * @param dBastaRowDTO the dBastaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dBastaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dBastaRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dBastaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DBastaRowDTO> updateDBastaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DBastaRowDTO dBastaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DBastaRow : {}, {}", id, dBastaRowDTO);
        if (dBastaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dBastaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dBastaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dBastaRowDTO = dBastaRowService.update(dBastaRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dBastaRowDTO.getId().toString()))
            .body(dBastaRowDTO);
    }

    /**
     * {@code PATCH  /d-basta-rows/:id} : Partial updates given fields of an existing dBastaRow, field will ignore if it is null
     *
     * @param id the id of the dBastaRowDTO to save.
     * @param dBastaRowDTO the dBastaRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dBastaRowDTO,
     * or with status {@code 400 (Bad Request)} if the dBastaRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dBastaRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dBastaRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DBastaRowDTO> partialUpdateDBastaRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DBastaRowDTO dBastaRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DBastaRow partially : {}, {}", id, dBastaRowDTO);
        if (dBastaRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dBastaRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dBastaRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DBastaRowDTO> result = dBastaRowService.partialUpdate(dBastaRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dBastaRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-basta-rows} : get all the dBastaRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dBastaRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DBastaRowDTO>> getAllDBastaRows(
        DBastaRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DBastaRows by criteria: {}", criteria);

        Page<DBastaRowDTO> page = dBastaRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-basta-rows/count} : count all the dBastaRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDBastaRows(DBastaRowCriteria criteria) {
        LOG.debug("REST request to count DBastaRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dBastaRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-basta-rows/:id} : get the "id" dBastaRow.
     *
     * @param id the id of the dBastaRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dBastaRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DBastaRowDTO> getDBastaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DBastaRow : {}", id);
        Optional<DBastaRowDTO> dBastaRowDTO = dBastaRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dBastaRowDTO);
    }

    /**
     * {@code DELETE  /d-basta-rows/:id} : delete the "id" dBastaRow.
     *
     * @param id the id of the dBastaRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDBastaRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DBastaRow : {}", id);
        dBastaRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
