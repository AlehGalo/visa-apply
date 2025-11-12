package com.visa.apply.service;

import com.visa.apply.domain.*; // for static metamodels
import com.visa.apply.domain.DLoadosfEntity;
import com.visa.apply.repository.DLoadosfEntityRepository;
import com.visa.apply.service.criteria.DLoadosfEntityCriteria;
import com.visa.apply.service.dto.DLoadosfEntityDTO;
import com.visa.apply.service.mapper.DLoadosfEntityMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DLoadosfEntity} entities in the database.
 * The main input is a {@link DLoadosfEntityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DLoadosfEntityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DLoadosfEntityQueryService extends QueryService<DLoadosfEntity> {

    private static final Logger LOG = LoggerFactory.getLogger(DLoadosfEntityQueryService.class);

    private final DLoadosfEntityRepository dLoadosfEntityRepository;

    private final DLoadosfEntityMapper dLoadosfEntityMapper;

    public DLoadosfEntityQueryService(DLoadosfEntityRepository dLoadosfEntityRepository, DLoadosfEntityMapper dLoadosfEntityMapper) {
        this.dLoadosfEntityRepository = dLoadosfEntityRepository;
        this.dLoadosfEntityMapper = dLoadosfEntityMapper;
    }

    /**
     * Return a {@link Page} of {@link DLoadosfEntityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DLoadosfEntityDTO> findByCriteria(DLoadosfEntityCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DLoadosfEntity> specification = createSpecification(criteria);
        return dLoadosfEntityRepository.findAll(specification, page).map(dLoadosfEntityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DLoadosfEntityCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DLoadosfEntity> specification = createSpecification(criteria);
        return dLoadosfEntityRepository.count(specification);
    }

    /**
     * Function to convert {@link DLoadosfEntityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DLoadosfEntity> createSpecification(DLoadosfEntityCriteria criteria) {
        Specification<DLoadosfEntity> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), DLoadosfEntity_.id),
                buildRangeSpecification(criteria.getVersion(), DLoadosfEntity_.version),
                buildSpecification(criteria.getMsgheaderId(), root ->
                    root.join(DLoadosfEntity_.msgheader, JoinType.LEFT).get(DMsgheaderRow_.id)
                ),
                buildSpecification(criteria.getLcuzId(), root -> root.join(DLoadosfEntity_.lcuz, JoinType.LEFT).get(DLcuzRow_.id)),
                buildSpecification(criteria.getLcdopId(), root -> root.join(DLoadosfEntity_.lcdop, JoinType.LEFT).get(DLcdopRow_.id)),
                buildSpecification(criteria.getBastaEntityId(), root ->
                    root.join(DLoadosfEntity_.bastaEntity, JoinType.LEFT).get(DBastaRow_.id)
                ),
                buildSpecification(criteria.getMaikaId(), root -> root.join(DLoadosfEntity_.maika, JoinType.LEFT).get(DMaikaRow_.id)),
                buildSpecification(criteria.getSaprugaId(), root -> root.join(DLoadosfEntity_.sapruga, JoinType.LEFT).get(DSaprugaRow_.id)),
                buildSpecification(criteria.getMolbaId(), root -> root.join(DLoadosfEntity_.molba, JoinType.LEFT).get(MolbaRow_.id)),
                buildSpecification(criteria.getDomakinId(), root -> root.join(DLoadosfEntity_.domakin, JoinType.LEFT).get(DDomakinRow_.id)),
                buildSpecification(criteria.getEurodaId(), root -> root.join(DLoadosfEntity_.euroda, JoinType.LEFT).get(DEurodaRow_.id)),
                buildSpecification(criteria.getVoitId(), root -> root.join(DLoadosfEntity_.voit, JoinType.LEFT).get(DVoitRow_.id)),
                buildSpecification(criteria.getImagesId(), root -> root.join(DLoadosfEntity_.images, JoinType.LEFT).get(DImagesRow_.id)),
                buildSpecification(criteria.getFingersId(), root -> root.join(DLoadosfEntity_.fingers, JoinType.LEFT).get(DFingersRow_.id))
            );
        }
        return specification;
    }
}
