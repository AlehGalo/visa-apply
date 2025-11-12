package com.visa.apply.web.rest;

import com.visa.apply.repository.DDomakinRowRepository;
import com.visa.apply.service.DDomakinRowQueryService;
import com.visa.apply.service.DDomakinRowService;
import com.visa.apply.service.criteria.DDomakinRowCriteria;
import com.visa.apply.service.dto.DDomakinRowDTO;
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
 * REST controller for managing {@link com.visa.apply.domain.DDomakinRow}.
 */
@RestController
@RequestMapping("/api/d-domakin-rows")
public class DDomakinRowResource {

    private static final Logger LOG = LoggerFactory.getLogger(DDomakinRowResource.class);

    private static final String ENTITY_NAME = "dDomakinRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DDomakinRowService dDomakinRowService;

    private final DDomakinRowRepository dDomakinRowRepository;

    private final DDomakinRowQueryService dDomakinRowQueryService;

    public DDomakinRowResource(
        DDomakinRowService dDomakinRowService,
        DDomakinRowRepository dDomakinRowRepository,
        DDomakinRowQueryService dDomakinRowQueryService
    ) {
        this.dDomakinRowService = dDomakinRowService;
        this.dDomakinRowRepository = dDomakinRowRepository;
        this.dDomakinRowQueryService = dDomakinRowQueryService;
    }

    /**
     * {@code POST  /d-domakin-rows} : Create a new dDomakinRow.
     *
     * @param dDomakinRowDTO the dDomakinRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dDomakinRowDTO, or with status {@code 400 (Bad Request)} if the dDomakinRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DDomakinRowDTO> createDDomakinRow(@Valid @RequestBody DDomakinRowDTO dDomakinRowDTO) throws URISyntaxException {
        LOG.debug("REST request to save DDomakinRow : {}", dDomakinRowDTO);
        if (dDomakinRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dDomakinRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dDomakinRowDTO = dDomakinRowService.save(dDomakinRowDTO);
        return ResponseEntity.created(new URI("/api/d-domakin-rows/" + dDomakinRowDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dDomakinRowDTO.getId().toString()))
            .body(dDomakinRowDTO);
    }

    /**
     * {@code PUT  /d-domakin-rows/:id} : Updates an existing dDomakinRow.
     *
     * @param id the id of the dDomakinRowDTO to save.
     * @param dDomakinRowDTO the dDomakinRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dDomakinRowDTO,
     * or with status {@code 400 (Bad Request)} if the dDomakinRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dDomakinRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DDomakinRowDTO> updateDDomakinRow(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DDomakinRowDTO dDomakinRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DDomakinRow : {}, {}", id, dDomakinRowDTO);
        if (dDomakinRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dDomakinRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dDomakinRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dDomakinRowDTO = dDomakinRowService.update(dDomakinRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dDomakinRowDTO.getId().toString()))
            .body(dDomakinRowDTO);
    }

    /**
     * {@code PATCH  /d-domakin-rows/:id} : Partial updates given fields of an existing dDomakinRow, field will ignore if it is null
     *
     * @param id the id of the dDomakinRowDTO to save.
     * @param dDomakinRowDTO the dDomakinRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dDomakinRowDTO,
     * or with status {@code 400 (Bad Request)} if the dDomakinRowDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dDomakinRowDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dDomakinRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DDomakinRowDTO> partialUpdateDDomakinRow(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DDomakinRowDTO dDomakinRowDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DDomakinRow partially : {}, {}", id, dDomakinRowDTO);
        if (dDomakinRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dDomakinRowDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dDomakinRowRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DDomakinRowDTO> result = dDomakinRowService.partialUpdate(dDomakinRowDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dDomakinRowDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /d-domakin-rows} : get all the dDomakinRows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dDomakinRows in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DDomakinRowDTO>> getAllDDomakinRows(
        DDomakinRowCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DDomakinRows by criteria: {}", criteria);

        Page<DDomakinRowDTO> page = dDomakinRowQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /d-domakin-rows/count} : count all the dDomakinRows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDDomakinRows(DDomakinRowCriteria criteria) {
        LOG.debug("REST request to count DDomakinRows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dDomakinRowQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /d-domakin-rows/:id} : get the "id" dDomakinRow.
     *
     * @param id the id of the dDomakinRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dDomakinRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DDomakinRowDTO> getDDomakinRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DDomakinRow : {}", id);
        Optional<DDomakinRowDTO> dDomakinRowDTO = dDomakinRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dDomakinRowDTO);
    }

    /**
     * {@code DELETE  /d-domakin-rows/:id} : delete the "id" dDomakinRow.
     *
     * @param id the id of the dDomakinRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDDomakinRow(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DDomakinRow : {}", id);
        dDomakinRowService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
