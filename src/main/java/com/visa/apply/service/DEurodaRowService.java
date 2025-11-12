package com.visa.apply.service;

import com.visa.apply.domain.DEurodaRow;
import com.visa.apply.repository.DEurodaRowRepository;
import com.visa.apply.service.dto.DEurodaRowDTO;
import com.visa.apply.service.mapper.DEurodaRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DEurodaRow}.
 */
@Service
@Transactional
public class DEurodaRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DEurodaRowService.class);

    private final DEurodaRowRepository dEurodaRowRepository;

    private final DEurodaRowMapper dEurodaRowMapper;

    public DEurodaRowService(DEurodaRowRepository dEurodaRowRepository, DEurodaRowMapper dEurodaRowMapper) {
        this.dEurodaRowRepository = dEurodaRowRepository;
        this.dEurodaRowMapper = dEurodaRowMapper;
    }

    /**
     * Save a dEurodaRow.
     *
     * @param dEurodaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DEurodaRowDTO save(DEurodaRowDTO dEurodaRowDTO) {
        LOG.debug("Request to save DEurodaRow : {}", dEurodaRowDTO);
        DEurodaRow dEurodaRow = dEurodaRowMapper.toEntity(dEurodaRowDTO);
        dEurodaRow = dEurodaRowRepository.save(dEurodaRow);
        return dEurodaRowMapper.toDto(dEurodaRow);
    }

    /**
     * Update a dEurodaRow.
     *
     * @param dEurodaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DEurodaRowDTO update(DEurodaRowDTO dEurodaRowDTO) {
        LOG.debug("Request to update DEurodaRow : {}", dEurodaRowDTO);
        DEurodaRow dEurodaRow = dEurodaRowMapper.toEntity(dEurodaRowDTO);
        dEurodaRow = dEurodaRowRepository.save(dEurodaRow);
        return dEurodaRowMapper.toDto(dEurodaRow);
    }

    /**
     * Partially update a dEurodaRow.
     *
     * @param dEurodaRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DEurodaRowDTO> partialUpdate(DEurodaRowDTO dEurodaRowDTO) {
        LOG.debug("Request to partially update DEurodaRow : {}", dEurodaRowDTO);

        return dEurodaRowRepository
            .findById(dEurodaRowDTO.getId())
            .map(existingDEurodaRow -> {
                dEurodaRowMapper.partialUpdate(existingDEurodaRow, dEurodaRowDTO);

                return existingDEurodaRow;
            })
            .map(dEurodaRowRepository::save)
            .map(dEurodaRowMapper::toDto);
    }

    /**
     * Get one dEurodaRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DEurodaRowDTO> findOne(Long id) {
        LOG.debug("Request to get DEurodaRow : {}", id);
        return dEurodaRowRepository.findById(id).map(dEurodaRowMapper::toDto);
    }

    /**
     * Delete the dEurodaRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DEurodaRow : {}", id);
        dEurodaRowRepository.deleteById(id);
    }
}
