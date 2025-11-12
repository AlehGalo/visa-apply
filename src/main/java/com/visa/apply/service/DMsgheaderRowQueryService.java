package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.repository.DMsgheaderRowRepository;
import com.visa.apply.service.criteria.DMsgheaderRowCriteria;
import com.visa.apply.service.dto.DMsgheaderRowDTO;
import com.visa.apply.service.mapper.DMsgheaderRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DMsgheaderRow} entities in the database.
 * The main input is a {@link DMsgheaderRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DMsgheaderRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DMsgheaderRowQueryService extends QueryService<DMsgheaderRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DMsgheaderRowQueryService.class);

    private final DMsgheaderRowRepository dMsgheaderRowRepository;

    private final DMsgheaderRowMapper dMsgheaderRowMapper;

    public DMsgheaderRowQueryService(DMsgheaderRowRepository dMsgheaderRowRepository, DMsgheaderRowMapper dMsgheaderRowMapper) {
        this.dMsgheaderRowRepository = dMsgheaderRowRepository;
        this.dMsgheaderRowMapper = dMsgheaderRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DMsgheaderRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DMsgheaderRowDTO> findByCriteria(DMsgheaderRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DMsgheaderRow> specification = createSpecification(criteria);
        return dMsgheaderRowRepository.findAll(specification, page).map(dMsgheaderRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DMsgheaderRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DMsgheaderRow> specification = createSpecification(criteria);
        return dMsgheaderRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DMsgheaderRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DMsgheaderRow> createSpecification(DMsgheaderRowCriteria criteria) {
        Specification<DMsgheaderRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DMsgheaderRow_.id),
                buildStringSpecification(criteria.getMhKscreated(), DMsgheaderRow_.mhKscreated),
                buildRangeSpecification(criteria.getMhRegnom(), DMsgheaderRow_.mhRegnom),
                buildStringSpecification(criteria.getMhVfsrefno(), DMsgheaderRow_.mhVfsrefno),
                buildStringSpecification(criteria.getMhUsera(), DMsgheaderRow_.mhUsera),
                buildStringSpecification(criteria.getMhDatvav(), DMsgheaderRow_.mhDatvav)
            );
        }
        return specification;
    }
}
