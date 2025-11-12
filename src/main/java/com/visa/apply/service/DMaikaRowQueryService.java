package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.repository.DMaikaRowRepository;
import com.visa.apply.service.criteria.DMaikaRowCriteria;
import com.visa.apply.service.dto.DMaikaRowDTO;
import com.visa.apply.service.mapper.DMaikaRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DMaikaRow} entities in the database.
 * The main input is a {@link DMaikaRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DMaikaRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DMaikaRowQueryService extends QueryService<DMaikaRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DMaikaRowQueryService.class);

    private final DMaikaRowRepository dMaikaRowRepository;

    private final DMaikaRowMapper dMaikaRowMapper;

    public DMaikaRowQueryService(DMaikaRowRepository dMaikaRowRepository, DMaikaRowMapper dMaikaRowMapper) {
        this.dMaikaRowRepository = dMaikaRowRepository;
        this.dMaikaRowMapper = dMaikaRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DMaikaRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DMaikaRowDTO> findByCriteria(DMaikaRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DMaikaRow> specification = createSpecification(criteria);
        return dMaikaRowRepository.findAll(specification, page).map(dMaikaRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DMaikaRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DMaikaRow> specification = createSpecification(criteria);
        return dMaikaRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DMaikaRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DMaikaRow> createSpecification(DMaikaRowCriteria criteria) {
        Specification<DMaikaRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DMaikaRow_.id),
                buildStringSpecification(criteria.getDcFamil(), DMaikaRow_.dcFamil),
                buildStringSpecification(criteria.getDcImena(), DMaikaRow_.dcImena),
                buildStringSpecification(criteria.getDcPol(), DMaikaRow_.dcPol),
                buildStringSpecification(criteria.getDcDarj(), DMaikaRow_.dcDarj),
                buildStringSpecification(criteria.getDcGrad(), DMaikaRow_.dcGrad),
                buildStringSpecification(criteria.getDcUlica(), DMaikaRow_.dcUlica)
            );
        }
        return specification;
    }
}
