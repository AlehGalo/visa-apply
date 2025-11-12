package com.visa.apply.service;

import com.visa.apply.domain.MolbaRow;
import com.visa.apply.repository.MolbaRowRepository;
import com.visa.apply.service.dto.MolbaRowDTO;
import com.visa.apply.service.mapper.MolbaRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.MolbaRow}.
 */
@Service
@Transactional
public class MolbaRowService {

    private static final Logger LOG = LoggerFactory.getLogger(MolbaRowService.class);

    private final MolbaRowRepository molbaRowRepository;

    private final MolbaRowMapper molbaRowMapper;

    public MolbaRowService(MolbaRowRepository molbaRowRepository, MolbaRowMapper molbaRowMapper) {
        this.molbaRowRepository = molbaRowRepository;
        this.molbaRowMapper = molbaRowMapper;
    }

    /**
     * Save a molbaRow.
     *
     * @param molbaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public MolbaRowDTO save(MolbaRowDTO molbaRowDTO) {
        LOG.debug("Request to save MolbaRow : {}", molbaRowDTO);
        MolbaRow molbaRow = molbaRowMapper.toEntity(molbaRowDTO);
        molbaRow = molbaRowRepository.save(molbaRow);
        return molbaRowMapper.toDto(molbaRow);
    }

    /**
     * Update a molbaRow.
     *
     * @param molbaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public MolbaRowDTO update(MolbaRowDTO molbaRowDTO) {
        LOG.debug("Request to update MolbaRow : {}", molbaRowDTO);
        MolbaRow molbaRow = molbaRowMapper.toEntity(molbaRowDTO);
        molbaRow = molbaRowRepository.save(molbaRow);
        return molbaRowMapper.toDto(molbaRow);
    }

    /**
     * Partially update a molbaRow.
     *
     * @param molbaRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MolbaRowDTO> partialUpdate(MolbaRowDTO molbaRowDTO) {
        LOG.debug("Request to partially update MolbaRow : {}", molbaRowDTO);

        return molbaRowRepository
            .findById(molbaRowDTO.getId())
            .map(existingMolbaRow -> {
                molbaRowMapper.partialUpdate(existingMolbaRow, molbaRowDTO);

                return existingMolbaRow;
            })
            .map(molbaRowRepository::save)
            .map(molbaRowMapper::toDto);
    }

    /**
     * Get one molbaRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MolbaRowDTO> findOne(Long id) {
        LOG.debug("Request to get MolbaRow : {}", id);
        return molbaRowRepository.findById(id).map(molbaRowMapper::toDto);
    }

    /**
     * Delete the molbaRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete MolbaRow : {}", id);
        molbaRowRepository.deleteById(id);
    }
}
