package com.visa.apply.service;

import com.visa.apply.domain.DLcuzRow;
import com.visa.apply.repository.DLcuzRowRepository;
import com.visa.apply.service.dto.DLcuzRowDTO;
import com.visa.apply.service.mapper.DLcuzRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DLcuzRow}.
 */
@Service
@Transactional
public class DLcuzRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DLcuzRowService.class);

    private final DLcuzRowRepository dLcuzRowRepository;

    private final DLcuzRowMapper dLcuzRowMapper;

    public DLcuzRowService(DLcuzRowRepository dLcuzRowRepository, DLcuzRowMapper dLcuzRowMapper) {
        this.dLcuzRowRepository = dLcuzRowRepository;
        this.dLcuzRowMapper = dLcuzRowMapper;
    }

    /**
     * Save a dLcuzRow.
     *
     * @param dLcuzRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DLcuzRowDTO save(DLcuzRowDTO dLcuzRowDTO) {
        LOG.debug("Request to save DLcuzRow : {}", dLcuzRowDTO);
        DLcuzRow dLcuzRow = dLcuzRowMapper.toEntity(dLcuzRowDTO);
        dLcuzRow = dLcuzRowRepository.save(dLcuzRow);
        return dLcuzRowMapper.toDto(dLcuzRow);
    }

    /**
     * Update a dLcuzRow.
     *
     * @param dLcuzRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DLcuzRowDTO update(DLcuzRowDTO dLcuzRowDTO) {
        LOG.debug("Request to update DLcuzRow : {}", dLcuzRowDTO);
        DLcuzRow dLcuzRow = dLcuzRowMapper.toEntity(dLcuzRowDTO);
        dLcuzRow = dLcuzRowRepository.save(dLcuzRow);
        return dLcuzRowMapper.toDto(dLcuzRow);
    }

    /**
     * Partially update a dLcuzRow.
     *
     * @param dLcuzRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DLcuzRowDTO> partialUpdate(DLcuzRowDTO dLcuzRowDTO) {
        LOG.debug("Request to partially update DLcuzRow : {}", dLcuzRowDTO);

        return dLcuzRowRepository
            .findById(dLcuzRowDTO.getId())
            .map(existingDLcuzRow -> {
                dLcuzRowMapper.partialUpdate(existingDLcuzRow, dLcuzRowDTO);

                return existingDLcuzRow;
            })
            .map(dLcuzRowRepository::save)
            .map(dLcuzRowMapper::toDto);
    }

    /**
     * Get one dLcuzRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DLcuzRowDTO> findOne(Long id) {
        LOG.debug("Request to get DLcuzRow : {}", id);
        return dLcuzRowRepository.findById(id).map(dLcuzRowMapper::toDto);
    }

    /**
     * Delete the dLcuzRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DLcuzRow : {}", id);
        dLcuzRowRepository.deleteById(id);
    }
}
