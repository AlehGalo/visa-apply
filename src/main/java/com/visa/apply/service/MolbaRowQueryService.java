package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.MolbaRow;
import com.visa.apply.repository.MolbaRowRepository;
import com.visa.apply.service.criteria.MolbaRowCriteria;
import com.visa.apply.service.dto.MolbaRowDTO;
import com.visa.apply.service.mapper.MolbaRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link MolbaRow} entities in the database.
 * The main input is a {@link MolbaRowCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link MolbaRowDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MolbaRowQueryService extends QueryService<MolbaRow> {

    private static final Logger LOG = LoggerFactory.getLogger(MolbaRowQueryService.class);

    private final MolbaRowRepository molbaRowRepository;

    private final MolbaRowMapper molbaRowMapper;

    public MolbaRowQueryService(MolbaRowRepository molbaRowRepository, MolbaRowMapper molbaRowMapper) {
        this.molbaRowRepository = molbaRowRepository;
        this.molbaRowMapper = molbaRowMapper;
    }

    /**
     * Return a {@link Page} of {@link MolbaRowDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MolbaRowDTO> findByCriteria(MolbaRowCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MolbaRow> specification = createSpecification(criteria);
        return molbaRowRepository.findAll(specification, page).map(molbaRowMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MolbaRowCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<MolbaRow> specification = createSpecification(criteria);
        return molbaRowRepository.count(specification);
    }

    /**
     * Function to convert {@link MolbaRowCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MolbaRow> createSpecification(MolbaRowCriteria criteria) {
        Specification<MolbaRow> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), MolbaRow_.id),
                buildRangeSpecification(criteria.getDatVli(), MolbaRow_.datVli),
                buildRangeSpecification(criteria.getDatIzl(), MolbaRow_.datIzl),
                buildStringSpecification(criteria.getVidvis(), MolbaRow_.vidvis),
                buildRangeSpecification(criteria.getBrvl(), MolbaRow_.brvl),
                buildStringSpecification(criteria.getVidus(), MolbaRow_.vidus),
                buildStringSpecification(criteria.getValvis(), MolbaRow_.valvis),
                buildRangeSpecification(criteria.getBrdni(), MolbaRow_.brdni),
                buildRangeSpecification(criteria.getCel(), MolbaRow_.cel),
                buildRangeSpecification(criteria.getMolDatVav(), MolbaRow_.molDatVav),
                buildStringSpecification(criteria.getGratis(), MolbaRow_.gratis),
                buildStringSpecification(criteria.getImavisa(), MolbaRow_.imavisa),
                buildRangeSpecification(criteria.getCenamol(), MolbaRow_.cenamol),
                buildStringSpecification(criteria.getCenacurr(), MolbaRow_.cenacurr),
                buildStringSpecification(criteria.getMaindest(), MolbaRow_.maindest),
                buildStringSpecification(criteria.getMaindestnm(), MolbaRow_.maindestnm),
                buildStringSpecification(criteria.getGkppDarj(), MolbaRow_.gkppDarj),
                buildStringSpecification(criteria.getGkppText(), MolbaRow_.gkppText),
                buildStringSpecification(criteria.getTextIni(), MolbaRow_.textIni)
            );
        }
        return specification;
    }
}
