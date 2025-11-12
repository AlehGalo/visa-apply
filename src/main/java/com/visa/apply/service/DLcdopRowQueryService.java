package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.repository.DLcdopRowRepository;
import com.visa.apply.service.criteria.DLcdopRowCriteria;
import com.visa.apply.service.dto.DLcdopRowDTO;
import com.visa.apply.service.mapper.DLcdopRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DLcdopRow} entities in the database.
 * The main input is a {@link DLcdopRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DLcdopRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DLcdopRowQueryService extends QueryService<DLcdopRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DLcdopRowQueryService.class);

    private final DLcdopRowRepository dLcdopRowRepository;

    private final DLcdopRowMapper dLcdopRowMapper;

    public DLcdopRowQueryService(DLcdopRowRepository dLcdopRowRepository, DLcdopRowMapper dLcdopRowMapper) {
        this.dLcdopRowRepository = dLcdopRowRepository;
        this.dLcdopRowMapper = dLcdopRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DLcdopRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DLcdopRowDTO> findByCriteria(DLcdopRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DLcdopRow> specification = createSpecification(criteria);
        return dLcdopRowRepository.findAll(specification, page).map(dLcdopRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DLcdopRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DLcdopRow> specification = createSpecification(criteria);
        return dLcdopRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DLcdopRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DLcdopRow> createSpecification(DLcdopRowCriteria criteria) {
        Specification<DLcdopRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DLcdopRow_.id),
                buildStringSpecification(criteria.getLdMrjdarj(), DLcdopRow_.ldMrjdarj),
                buildStringSpecification(criteria.getLdMrjnm(), DLcdopRow_.ldMrjnm),
                buildStringSpecification(criteria.getLdMrjgraj(), DLcdopRow_.ldMrjgraj),
                buildStringSpecification(criteria.getLdZenen(), DLcdopRow_.ldZenen),
                buildStringSpecification(criteria.getLdJitDarj(), DLcdopRow_.ldJitDarj),
                buildStringSpecification(criteria.getLdJitNm(), DLcdopRow_.ldJitNm),
                buildStringSpecification(criteria.getLdJitUl(), DLcdopRow_.ldJitUl),
                buildRangeSpecification(criteria.getLdTel(), DLcdopRow_.ldTel),
                buildStringSpecification(criteria.getLdRabota(), DLcdopRow_.ldRabota),
                buildStringSpecification(criteria.getLdProfkod(), DLcdopRow_.ldProfkod),
                buildStringSpecification(criteria.getLdProfesia(), DLcdopRow_.ldProfesia),
                buildStringSpecification(criteria.getLdSlDarj(), DLcdopRow_.ldSlDarj),
                buildStringSpecification(criteria.getLdSlNm(), DLcdopRow_.ldSlNm),
                buildStringSpecification(criteria.getLdSlUl(), DLcdopRow_.ldSlUl)
            );
        }
        return specification;
    }
}
