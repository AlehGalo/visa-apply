package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DFingersRow;
import com.visa.apply.repository.DFingersRowRepository;
import com.visa.apply.service.criteria.DFingersRowCriteria;
import com.visa.apply.service.dto.DFingersRowDTO;
import com.visa.apply.service.mapper.DFingersRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DFingersRow} entities in the database.
 * The main input is a {@link DFingersRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DFingersRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DFingersRowQueryService extends QueryService<DFingersRow> {

    private static final Logger LOG = LoggerFactory.getLogger(DFingersRowQueryService.class);

    private final DFingersRowRepository dFingersRowRepository;

    private final DFingersRowMapper dFingersRowMapper;

    public DFingersRowQueryService(DFingersRowRepository dFingersRowRepository, DFingersRowMapper dFingersRowMapper) {
        this.dFingersRowRepository = dFingersRowRepository;
        this.dFingersRowMapper = dFingersRowMapper;
    }

    /**
     * Return a {@link Page} of {@link DFingersRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DFingersRowDTO> findByCriteria(DFingersRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DFingersRow> specification = createSpecification(criteria);
        return dFingersRowRepository.findAll(specification, page).map(dFingersRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DFingersRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DFingersRow> specification = createSpecification(criteria);
        return dFingersRowRepository.count(specification);
    }

    /**
     * Function to convert {@link DFingersRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DFingersRow> createSpecification(DFingersRowCriteria criteria) {
        Specification<DFingersRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DFingersRow_.id),
                buildRangeSpecification(criteria.getFnDatreg(), DFingersRow_.fnDatreg),
                buildRangeSpecification(criteria.getFnDatvav(), DFingersRow_.fnDatvav),
                buildStringSpecification(criteria.getFnUsera(), DFingersRow_.fnUsera),
                buildRangeSpecification(criteria.getFnSid(), DFingersRow_.fnSid),
                buildRangeSpecification(criteria.getFnPnr(), DFingersRow_.fnPnr),
                buildStringSpecification(criteria.getFnVidmol(), DFingersRow_.fnVidmol),
                buildStringSpecification(criteria.getFnDrugi(), DFingersRow_.fnDrugi),
                buildRangeSpecification(criteria.getFnDeviceid(), DFingersRow_.fnDeviceid),
                buildRangeSpecification(criteria.getFnScanres(), DFingersRow_.fnScanres),
                buildRangeSpecification(criteria.getFnWidth(), DFingersRow_.fnWidth),
                buildRangeSpecification(criteria.getFnHeight(), DFingersRow_.fnHeight),
                buildRangeSpecification(criteria.getFnPixeldepth(), DFingersRow_.fnPixeldepth),
                buildRangeSpecification(criteria.getFnCompressalgo(), DFingersRow_.fnCompressalgo),
                buildStringSpecification(criteria.getFnFingerposition(), DFingersRow_.fnFingerposition),
                buildRangeSpecification(criteria.getFnImagequality(), DFingersRow_.fnImagequality),
                buildStringSpecification(criteria.getFnImage(), DFingersRow_.fnImage),
                buildRangeSpecification(criteria.getFnImglen(), DFingersRow_.fnImglen),
                buildStringSpecification(criteria.getFnNottakenreason(), DFingersRow_.fnNottakenreason)
            );
        }
        return specification;
    }
}
