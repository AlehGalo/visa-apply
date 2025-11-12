package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.repository.DSaprugaRowRepository;
import com.visa.apply.service.criteria.DSaprugaRowCriteria;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import com.visa.apply.service.mapper.DSaprugaRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DSaprugaRow} entities in the database.
 * The main input is a {@link DSaprugaRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DSaprugaRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DSaprugaRowQueryService extends QueryService<DSaprugaRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DSaprugaRowQueryService.class);

    private final DSaprugaRowRepository dSaprugaRowRepository;

    private final DSaprugaRowMapper dSaprugaRowMapper;

    public DSaprugaRowQueryService(DSaprugaRowRepository dSaprugaRowRepository, DSaprugaRowMapper dSaprugaRowMapper) {
        this.dSaprugaRowRepository = dSaprugaRowRepository;
        this.dSaprugaRowMapper = dSaprugaRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DSaprugaRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DSaprugaRowDTO> findByCriteria(DSaprugaRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DSaprugaRow> specification = createSpecification(criteria);
        return dSaprugaRowRepository.findAll(specification, page).map(dSaprugaRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DSaprugaRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DSaprugaRow> specification = createSpecification(criteria);
        return dSaprugaRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DSaprugaRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DSaprugaRow> createSpecification(DSaprugaRowCriteria criteria) {
        Specification<DSaprugaRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DSaprugaRow_.id),
                buildStringSpecification(criteria.getSpMrjdarj(), DSaprugaRow_.spMrjdarj)
            );
        }
        return specification;
    }
}
