package com.visa.apply.service;

import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.repository.DLcdopRowRepository;
import com.visa.apply.service.dto.DLcdopRowDTO;
import com.visa.apply.service.mapper.DLcdopRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DLcdopRow}.
 */
@Service
@Transactional
public class DLcdopRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DLcdopRowService.class);

    private final DLcdopRowRepository dLcdopRowRepository;

    private final DLcdopRowMapper dLcdopRowMapper;

    public DLcdopRowService(DLcdopRowRepository dLcdopRowRepository, DLcdopRowMapper dLcdopRowMapper) {
        this.dLcdopRowRepository = dLcdopRowRepository;
        this.dLcdopRowMapper = dLcdopRowMapper;
    }

    /**
     * Save a dLcdopRow.
     *
     * @param dLcdopRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DLcdopRowDTO save(DLcdopRowDTO dLcdopRowDTO) {
        LOG.debug("Request to save DLcdopRow : {}", dLcdopRowDTO);
        DLcdopRow dLcdopRow = dLcdopRowMapper.toEntity(dLcdopRowDTO);
        dLcdopRow = dLcdopRowRepository.save(dLcdopRow);
        return dLcdopRowMapper.toDto(dLcdopRow);
    }

    /**
     * Update a dLcdopRow.
     *
     * @param dLcdopRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DLcdopRowDTO update(DLcdopRowDTO dLcdopRowDTO) {
        LOG.debug("Request to update DLcdopRow : {}", dLcdopRowDTO);
        DLcdopRow dLcdopRow = dLcdopRowMapper.toEntity(dLcdopRowDTO);
        dLcdopRow = dLcdopRowRepository.save(dLcdopRow);
        return dLcdopRowMapper.toDto(dLcdopRow);
    }

    /**
     * Partially update a dLcdopRow.
     *
     * @param dLcdopRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DLcdopRowDTO> partialUpdate(DLcdopRowDTO dLcdopRowDTO) {
        LOG.debug("Request to partially update DLcdopRow : {}", dLcdopRowDTO);

        return dLcdopRowRepository
            .findById(dLcdopRowDTO.getId())
            .map(existingDLcdopRow -> {
                dLcdopRowMapper.partialUpdate(existingDLcdopRow, dLcdopRowDTO);

                return existingDLcdopRow;
            })
            .map(dLcdopRowRepository::save)
            .map(dLcdopRowMapper::toDto);
    }

    /**
     * Get one dLcdopRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DLcdopRowDTO> findOne(Long id) {
        LOG.debug("Request to get DLcdopRow : {}", id);
        return dLcdopRowRepository.findById(id).map(dLcdopRowMapper::toDto);
    }

    /**
     * Delete the dLcdopRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DLcdopRow : {}", id);
        dLcdopRowRepository.deleteById(id);
    }
}
