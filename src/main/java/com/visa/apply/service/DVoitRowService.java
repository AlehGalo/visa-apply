package com.visa.apply.service;

import com.visa.apply.domain.DVoitRow;
import com.visa.apply.repository.DVoitRowRepository;
import com.visa.apply.service.dto.DVoitRowDTO;
import com.visa.apply.service.mapper.DVoitRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DVoitRow}.
 */
@Service
@Transactional
public class DVoitRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DVoitRowService.class);

    private final DVoitRowRepository dVoitRowRepository;

    private final DVoitRowMapper dVoitRowMapper;

    public DVoitRowService(DVoitRowRepository dVoitRowRepository, DVoitRowMapper dVoitRowMapper) {
        this.dVoitRowRepository = dVoitRowRepository;
        this.dVoitRowMapper = dVoitRowMapper;
    }

    /**
     * Save a dVoitRow.
     *
     * @param dVoitRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DVoitRowDTO save(DVoitRowDTO dVoitRowDTO) {
        LOG.debug("Request to save DVoitRow : {}", dVoitRowDTO);
        DVoitRow dVoitRow = dVoitRowMapper.toEntity(dVoitRowDTO);
        dVoitRow = dVoitRowRepository.save(dVoitRow);
        return dVoitRowMapper.toDto(dVoitRow);
    }

    /**
     * Update a dVoitRow.
     *
     * @param dVoitRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DVoitRowDTO update(DVoitRowDTO dVoitRowDTO) {
        LOG.debug("Request to update DVoitRow : {}", dVoitRowDTO);
        DVoitRow dVoitRow = dVoitRowMapper.toEntity(dVoitRowDTO);
        dVoitRow = dVoitRowRepository.save(dVoitRow);
        return dVoitRowMapper.toDto(dVoitRow);
    }

    /**
     * Partially update a dVoitRow.
     *
     * @param dVoitRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DVoitRowDTO> partialUpdate(DVoitRowDTO dVoitRowDTO) {
        LOG.debug("Request to partially update DVoitRow : {}", dVoitRowDTO);

        return dVoitRowRepository
            .findById(dVoitRowDTO.getId())
            .map(existingDVoitRow -> {
                dVoitRowMapper.partialUpdate(existingDVoitRow, dVoitRowDTO);

                return existingDVoitRow;
            })
            .map(dVoitRowRepository::save)
            .map(dVoitRowMapper::toDto);
    }

    /**
     * Get one dVoitRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DVoitRowDTO> findOne(Long id) {
        LOG.debug("Request to get DVoitRow : {}", id);
        return dVoitRowRepository.findById(id).map(dVoitRowMapper::toDto);
    }

    /**
     * Delete the dVoitRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DVoitRow : {}", id);
        dVoitRowRepository.deleteById(id);
    }
}
