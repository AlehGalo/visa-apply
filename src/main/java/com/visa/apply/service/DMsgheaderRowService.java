package com.visa.apply.service;

import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.repository.DMsgheaderRowRepository;
import com.visa.apply.service.dto.DMsgheaderRowDTO;
import com.visa.apply.service.mapper.DMsgheaderRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DMsgheaderRow}.
 */
@Service
@Transactional
public class DMsgheaderRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DMsgheaderRowService.class);

    private final DMsgheaderRowRepository dMsgheaderRowRepository;

    private final DMsgheaderRowMapper dMsgheaderRowMapper;

    public DMsgheaderRowService(DMsgheaderRowRepository dMsgheaderRowRepository, DMsgheaderRowMapper dMsgheaderRowMapper) {
        this.dMsgheaderRowRepository = dMsgheaderRowRepository;
        this.dMsgheaderRowMapper = dMsgheaderRowMapper;
    }

    /**
     * Save a dMsgheaderRow.
     *
     * @param dMsgheaderRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DMsgheaderRowDTO save(DMsgheaderRowDTO dMsgheaderRowDTO) {
        LOG.debug("Request to save DMsgheaderRow : {}", dMsgheaderRowDTO);
        DMsgheaderRow dMsgheaderRow = dMsgheaderRowMapper.toEntity(dMsgheaderRowDTO);
        dMsgheaderRow = dMsgheaderRowRepository.save(dMsgheaderRow);
        return dMsgheaderRowMapper.toDto(dMsgheaderRow);
    }

    /**
     * Update a dMsgheaderRow.
     *
     * @param dMsgheaderRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DMsgheaderRowDTO update(DMsgheaderRowDTO dMsgheaderRowDTO) {
        LOG.debug("Request to update DMsgheaderRow : {}", dMsgheaderRowDTO);
        DMsgheaderRow dMsgheaderRow = dMsgheaderRowMapper.toEntity(dMsgheaderRowDTO);
        dMsgheaderRow = dMsgheaderRowRepository.save(dMsgheaderRow);
        return dMsgheaderRowMapper.toDto(dMsgheaderRow);
    }

    /**
     * Partially update a dMsgheaderRow.
     *
     * @param dMsgheaderRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DMsgheaderRowDTO> partialUpdate(DMsgheaderRowDTO dMsgheaderRowDTO) {
        LOG.debug("Request to partially update DMsgheaderRow : {}", dMsgheaderRowDTO);

        return dMsgheaderRowRepository
            .findById(dMsgheaderRowDTO.getId())
            .map(existingDMsgheaderRow -> {
                dMsgheaderRowMapper.partialUpdate(existingDMsgheaderRow, dMsgheaderRowDTO);

                return existingDMsgheaderRow;
            })
            .map(dMsgheaderRowRepository::save)
            .map(dMsgheaderRowMapper::toDto);
    }

    /**
     * Get one dMsgheaderRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DMsgheaderRowDTO> findOne(Long id) {
        LOG.debug("Request to get DMsgheaderRow : {}", id);
        return dMsgheaderRowRepository.findById(id).map(dMsgheaderRowMapper::toDto);
    }

    /**
     * Delete the dMsgheaderRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DMsgheaderRow : {}", id);
        dMsgheaderRowRepository.deleteById(id);
    }
}
