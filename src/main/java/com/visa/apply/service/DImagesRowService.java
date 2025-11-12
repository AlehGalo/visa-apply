package com.visa.apply.service;

import com.visa.apply.domain.DImagesRow;
import com.visa.apply.repository.DImagesRowRepository;
import com.visa.apply.service.dto.DImagesRowDTO;
import com.visa.apply.service.mapper.DImagesRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DImagesRow}.
 */
@Service
@Transactional
public class DImagesRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DImagesRowService.class);

    private final DImagesRowRepository dImagesRowRepository;

    private final DImagesRowMapper dImagesRowMapper;

    public DImagesRowService(DImagesRowRepository dImagesRowRepository, DImagesRowMapper dImagesRowMapper) {
        this.dImagesRowRepository = dImagesRowRepository;
        this.dImagesRowMapper = dImagesRowMapper;
    }

    /**
     * Save a dImagesRow.
     *
     * @param dImagesRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DImagesRowDTO save(DImagesRowDTO dImagesRowDTO) {
        LOG.debug("Request to save DImagesRow : {}", dImagesRowDTO);
        DImagesRow dImagesRow = dImagesRowMapper.toEntity(dImagesRowDTO);
        dImagesRow = dImagesRowRepository.save(dImagesRow);
        return dImagesRowMapper.toDto(dImagesRow);
    }

    /**
     * Update a dImagesRow.
     *
     * @param dImagesRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DImagesRowDTO update(DImagesRowDTO dImagesRowDTO) {
        LOG.debug("Request to update DImagesRow : {}", dImagesRowDTO);
        DImagesRow dImagesRow = dImagesRowMapper.toEntity(dImagesRowDTO);
        dImagesRow = dImagesRowRepository.save(dImagesRow);
        return dImagesRowMapper.toDto(dImagesRow);
    }

    /**
     * Partially update a dImagesRow.
     *
     * @param dImagesRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DImagesRowDTO> partialUpdate(DImagesRowDTO dImagesRowDTO) {
        LOG.debug("Request to partially update DImagesRow : {}", dImagesRowDTO);

        return dImagesRowRepository
            .findById(dImagesRowDTO.getId())
            .map(existingDImagesRow -> {
                dImagesRowMapper.partialUpdate(existingDImagesRow, dImagesRowDTO);

                return existingDImagesRow;
            })
            .map(dImagesRowRepository::save)
            .map(dImagesRowMapper::toDto);
    }

    /**
     * Get one dImagesRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DImagesRowDTO> findOne(Long id) {
        LOG.debug("Request to get DImagesRow : {}", id);
        return dImagesRowRepository.findById(id).map(dImagesRowMapper::toDto);
    }

    /**
     * Delete the dImagesRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DImagesRow : {}", id);
        dImagesRowRepository.deleteById(id);
    }
}
