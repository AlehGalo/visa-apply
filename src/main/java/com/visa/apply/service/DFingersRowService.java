package com.visa.apply.service;

import com.visa.apply.domain.DFingersRow;
import com.visa.apply.repository.DFingersRowRepository;
import com.visa.apply.service.dto.DFingersRowDTO;
import com.visa.apply.service.mapper.DFingersRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DFingersRow}.
 */
@Service
@Transactional
public class DFingersRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DFingersRowService.class);

    private final DFingersRowRepository dFingersRowRepository;

    private final DFingersRowMapper dFingersRowMapper;

    public DFingersRowService(DFingersRowRepository dFingersRowRepository, DFingersRowMapper dFingersRowMapper) {
        this.dFingersRowRepository = dFingersRowRepository;
        this.dFingersRowMapper = dFingersRowMapper;
    }

    /**
     * Save a dFingersRow.
     *
     * @param dFingersRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DFingersRowDTO save(DFingersRowDTO dFingersRowDTO) {
        LOG.debug("Request to save DFingersRow : {}", dFingersRowDTO);
        DFingersRow dFingersRow = dFingersRowMapper.toEntity(dFingersRowDTO);
        dFingersRow = dFingersRowRepository.save(dFingersRow);
        return dFingersRowMapper.toDto(dFingersRow);
    }

    /**
     * Update a dFingersRow.
     *
     * @param dFingersRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DFingersRowDTO update(DFingersRowDTO dFingersRowDTO) {
        LOG.debug("Request to update DFingersRow : {}", dFingersRowDTO);
        DFingersRow dFingersRow = dFingersRowMapper.toEntity(dFingersRowDTO);
        dFingersRow = dFingersRowRepository.save(dFingersRow);
        return dFingersRowMapper.toDto(dFingersRow);
    }

    /**
     * Partially update a dFingersRow.
     *
     * @param dFingersRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DFingersRowDTO> partialUpdate(DFingersRowDTO dFingersRowDTO) {
        LOG.debug("Request to partially update DFingersRow : {}", dFingersRowDTO);

        return dFingersRowRepository
            .findById(dFingersRowDTO.getId())
            .map(existingDFingersRow -> {
                dFingersRowMapper.partialUpdate(existingDFingersRow, dFingersRowDTO);

                return existingDFingersRow;
            })
            .map(dFingersRowRepository::save)
            .map(dFingersRowMapper::toDto);
    }

    /**
     * Get one dFingersRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DFingersRowDTO> findOne(Long id) {
        LOG.debug("Request to get DFingersRow : {}", id);
        return dFingersRowRepository.findById(id).map(dFingersRowMapper::toDto);
    }

    /**
     * Delete the dFingersRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DFingersRow : {}", id);
        dFingersRowRepository.deleteById(id);
    }
}
