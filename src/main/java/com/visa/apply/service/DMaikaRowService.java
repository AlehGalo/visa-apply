package com.visa.apply.service;

import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.repository.DMaikaRowRepository;
import com.visa.apply.service.dto.DMaikaRowDTO;
import com.visa.apply.service.mapper.DMaikaRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DMaikaRow}.
 */
@Service
@Transactional
public class DMaikaRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DMaikaRowService.class);

    private final DMaikaRowRepository dMaikaRowRepository;

    private final DMaikaRowMapper dMaikaRowMapper;

    public DMaikaRowService(DMaikaRowRepository dMaikaRowRepository, DMaikaRowMapper dMaikaRowMapper) {
        this.dMaikaRowRepository = dMaikaRowRepository;
        this.dMaikaRowMapper = dMaikaRowMapper;
    }

    /**
     * Save a dMaikaRow.
     *
     * @param dMaikaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DMaikaRowDTO save(DMaikaRowDTO dMaikaRowDTO) {
        LOG.debug("Request to save DMaikaRow : {}", dMaikaRowDTO);
        DMaikaRow dMaikaRow = dMaikaRowMapper.toEntity(dMaikaRowDTO);
        dMaikaRow = dMaikaRowRepository.save(dMaikaRow);
        return dMaikaRowMapper.toDto(dMaikaRow);
    }

    /**
     * Update a dMaikaRow.
     *
     * @param dMaikaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DMaikaRowDTO update(DMaikaRowDTO dMaikaRowDTO) {
        LOG.debug("Request to update DMaikaRow : {}", dMaikaRowDTO);
        DMaikaRow dMaikaRow = dMaikaRowMapper.toEntity(dMaikaRowDTO);
        dMaikaRow = dMaikaRowRepository.save(dMaikaRow);
        return dMaikaRowMapper.toDto(dMaikaRow);
    }

    /**
     * Partially update a dMaikaRow.
     *
     * @param dMaikaRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DMaikaRowDTO> partialUpdate(DMaikaRowDTO dMaikaRowDTO) {
        LOG.debug("Request to partially update DMaikaRow : {}", dMaikaRowDTO);

        return dMaikaRowRepository
            .findById(dMaikaRowDTO.getId())
            .map(existingDMaikaRow -> {
                dMaikaRowMapper.partialUpdate(existingDMaikaRow, dMaikaRowDTO);

                return existingDMaikaRow;
            })
            .map(dMaikaRowRepository::save)
            .map(dMaikaRowMapper::toDto);
    }

    /**
     * Get one dMaikaRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DMaikaRowDTO> findOne(Long id) {
        LOG.debug("Request to get DMaikaRow : {}", id);
        return dMaikaRowRepository.findById(id).map(dMaikaRowMapper::toDto);
    }

    /**
     * Delete the dMaikaRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DMaikaRow : {}", id);
        dMaikaRowRepository.deleteById(id);
    }
}
