package com.visa.apply.service;

import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.repository.DSaprugaRowRepository;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import com.visa.apply.service.mapper.DSaprugaRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DSaprugaRow}.
 */
@Service
@Transactional
public class DSaprugaRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DSaprugaRowService.class);

    private final DSaprugaRowRepository dSaprugaRowRepository;

    private final DSaprugaRowMapper dSaprugaRowMapper;

    public DSaprugaRowService(DSaprugaRowRepository dSaprugaRowRepository, DSaprugaRowMapper dSaprugaRowMapper) {
        this.dSaprugaRowRepository = dSaprugaRowRepository;
        this.dSaprugaRowMapper = dSaprugaRowMapper;
    }

    /**
     * Save a dSaprugaRow.
     *
     * @param dSaprugaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DSaprugaRowDTO save(DSaprugaRowDTO dSaprugaRowDTO) {
        LOG.debug("Request to save DSaprugaRow : {}", dSaprugaRowDTO);
        DSaprugaRow dSaprugaRow = dSaprugaRowMapper.toEntity(dSaprugaRowDTO);
        dSaprugaRow = dSaprugaRowRepository.save(dSaprugaRow);
        return dSaprugaRowMapper.toDto(dSaprugaRow);
    }

    /**
     * Update a dSaprugaRow.
     *
     * @param dSaprugaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DSaprugaRowDTO update(DSaprugaRowDTO dSaprugaRowDTO) {
        LOG.debug("Request to update DSaprugaRow : {}", dSaprugaRowDTO);
        DSaprugaRow dSaprugaRow = dSaprugaRowMapper.toEntity(dSaprugaRowDTO);
        dSaprugaRow = dSaprugaRowRepository.save(dSaprugaRow);
        return dSaprugaRowMapper.toDto(dSaprugaRow);
    }

    /**
     * Partially update a dSaprugaRow.
     *
     * @param dSaprugaRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DSaprugaRowDTO> partialUpdate(DSaprugaRowDTO dSaprugaRowDTO) {
        LOG.debug("Request to partially update DSaprugaRow : {}", dSaprugaRowDTO);

        return dSaprugaRowRepository
            .findById(dSaprugaRowDTO.getId())
            .map(existingDSaprugaRow -> {
                dSaprugaRowMapper.partialUpdate(existingDSaprugaRow, dSaprugaRowDTO);

                return existingDSaprugaRow;
            })
            .map(dSaprugaRowRepository::save)
            .map(dSaprugaRowMapper::toDto);
    }

    /**
     * Get one dSaprugaRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DSaprugaRowDTO> findOne(Long id) {
        LOG.debug("Request to get DSaprugaRow : {}", id);
        return dSaprugaRowRepository.findById(id).map(dSaprugaRowMapper::toDto);
    }

    /**
     * Delete the dSaprugaRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DSaprugaRow : {}", id);
        dSaprugaRowRepository.deleteById(id);
    }
}
