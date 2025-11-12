package com.visa.apply.service;

import com.visa.apply.domain.DLoadosfEntity;
import com.visa.apply.repository.DLoadosfEntityRepository;
import com.visa.apply.service.dto.DLoadosfEntityDTO;
import com.visa.apply.service.mapper.DLoadosfEntityMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DLoadosfEntity}.
 */
@Service
@Transactional
public class DLoadosfEntityService {

    private static final Logger LOG = LoggerFactory.getLogger(DLoadosfEntityService.class);

    private final DLoadosfEntityRepository dLoadosfEntityRepository;

    private final DLoadosfEntityMapper dLoadosfEntityMapper;

    public DLoadosfEntityService(DLoadosfEntityRepository dLoadosfEntityRepository, DLoadosfEntityMapper dLoadosfEntityMapper) {
        this.dLoadosfEntityRepository = dLoadosfEntityRepository;
        this.dLoadosfEntityMapper = dLoadosfEntityMapper;
    }

    /**
     * Save a dLoadosfEntity.
     *
     * @param dLoadosfEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public DLoadosfEntityDTO save(DLoadosfEntityDTO dLoadosfEntityDTO) {
        LOG.debug("Request to save DLoadosfEntity : {}", dLoadosfEntityDTO);
        DLoadosfEntity dLoadosfEntity = dLoadosfEntityMapper.toEntity(dLoadosfEntityDTO);
        dLoadosfEntity = dLoadosfEntityRepository.save(dLoadosfEntity);
        return dLoadosfEntityMapper.toDto(dLoadosfEntity);
    }

    /**
     * Update a dLoadosfEntity.
     *
     * @param dLoadosfEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public DLoadosfEntityDTO update(DLoadosfEntityDTO dLoadosfEntityDTO) {
        LOG.debug("Request to update DLoadosfEntity : {}", dLoadosfEntityDTO);
        DLoadosfEntity dLoadosfEntity = dLoadosfEntityMapper.toEntity(dLoadosfEntityDTO);
        dLoadosfEntity = dLoadosfEntityRepository.save(dLoadosfEntity);
        return dLoadosfEntityMapper.toDto(dLoadosfEntity);
    }

    /**
     * Partially update a dLoadosfEntity.
     *
     * @param dLoadosfEntityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DLoadosfEntityDTO> partialUpdate(DLoadosfEntityDTO dLoadosfEntityDTO) {
        LOG.debug("Request to partially update DLoadosfEntity : {}", dLoadosfEntityDTO);

        return dLoadosfEntityRepository
            .findById(dLoadosfEntityDTO.getId())
            .map(existingDLoadosfEntity -> {
                dLoadosfEntityMapper.partialUpdate(existingDLoadosfEntity, dLoadosfEntityDTO);

                return existingDLoadosfEntity;
            })
            .map(dLoadosfEntityRepository::save)
            .map(dLoadosfEntityMapper::toDto);
    }

    /**
     * Get one dLoadosfEntity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DLoadosfEntityDTO> findOne(Long id) {
        LOG.debug("Request to get DLoadosfEntity : {}", id);
        return dLoadosfEntityRepository.findById(id).map(dLoadosfEntityMapper::toDto);
    }

    /**
     * Delete the dLoadosfEntity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DLoadosfEntity : {}", id);
        dLoadosfEntityRepository.deleteById(id);
    }
}
