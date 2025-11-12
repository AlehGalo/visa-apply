package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DImagesRow;
import com.visa.apply.repository.DImagesRowRepository;
import com.visa.apply.service.criteria.DImagesRowCriteria;
import com.visa.apply.service.dto.DImagesRowDTO;
import com.visa.apply.service.mapper.DImagesRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DImagesRow} entities in the database.
 * The main input is a {@link DImagesRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DImagesRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DImagesRowQueryService extends QueryService<DImagesRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DImagesRowQueryService.class);

    private final DImagesRowRepository dImagesRowRepository;

    private final DImagesRowMapper dImagesRowMapper;

    public DImagesRowQueryService(DImagesRowRepository dImagesRowRepository, DImagesRowMapper dImagesRowMapper) {
        this.dImagesRowRepository = dImagesRowRepository;
        this.dImagesRowMapper = dImagesRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DImagesRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DImagesRowDTO> findByCriteria(DImagesRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DImagesRow> specification = createSpecification(criteria);
        return dImagesRowRepository.findAll(specification, page).map(dImagesRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DImagesRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DImagesRow> specification = createSpecification(criteria);
        return dImagesRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DImagesRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DImagesRow> createSpecification(DImagesRowCriteria criteria) {
        Specification<DImagesRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DImagesRow_.id),
                buildRangeSpecification(criteria.getImDevicetype(), DImagesRow_.imDevicetype),
                buildRangeSpecification(criteria.getImWidth(), DImagesRow_.imWidth),
                buildRangeSpecification(criteria.getImHeight(), DImagesRow_.imHeight),
                buildRangeSpecification(criteria.getImImglen(), DImagesRow_.imImglen),
                buildStringSpecification(criteria.getImImage(), DImagesRow_.imImage)
            );
        }
        return specification;
    }
}
