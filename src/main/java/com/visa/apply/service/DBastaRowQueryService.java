package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DBastaRow;
import com.visa.apply.repository.DBastaRowRepository;
import com.visa.apply.service.criteria.DBastaRowCriteria;
import com.visa.apply.service.dto.DBastaRowDTO;
import com.visa.apply.service.mapper.DBastaRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DBastaRow} entities in the database.
 * The main input is a {@link DBastaRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DBastaRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DBastaRowQueryService extends QueryService<DBastaRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DBastaRowQueryService.class);

    private final DBastaRowRepository dBastaRowRepository;

    private final DBastaRowMapper dBastaRowMapper;

    public DBastaRowQueryService(DBastaRowRepository dBastaRowRepository, DBastaRowMapper dBastaRowMapper) {
        this.dBastaRowRepository = dBastaRowRepository;
        this.dBastaRowMapper = dBastaRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DBastaRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DBastaRowDTO> findByCriteria(DBastaRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DBastaRow> specification = createSpecification(criteria);
        return dBastaRowRepository.findAll(specification, page).map(dBastaRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DBastaRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DBastaRow> specification = createSpecification(criteria);
        return dBastaRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DBastaRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DBastaRow> createSpecification(DBastaRowCriteria criteria) {
        Specification<DBastaRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DBastaRow_.id),
                buildStringSpecification(criteria.getDcFamil(), DBastaRow_.dcFamil),
                buildStringSpecification(criteria.getDcImena(), DBastaRow_.dcImena),
                buildStringSpecification(criteria.getDcPol(), DBastaRow_.dcPol),
                buildStringSpecification(criteria.getDcGrad(), DBastaRow_.dcGrad),
                buildStringSpecification(criteria.getDcUlica(), DBastaRow_.dcUlica)
            );
        }
        return specification;
    }
}
