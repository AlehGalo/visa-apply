package com.visa.apply.service;

import com.visa.apply.domain.DBastaRow;
import com.visa.apply.repository.DBastaRowRepository;
import com.visa.apply.service.dto.DBastaRowDTO;
import com.visa.apply.service.mapper.DBastaRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DBastaRow}.
 */
@Service
@Transactional
public class DBastaRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DBastaRowService.class);

    private final DBastaRowRepository dBastaRowRepository;

    private final DBastaRowMapper dBastaRowMapper;

    public DBastaRowService(DBastaRowRepository dBastaRowRepository, DBastaRowMapper dBastaRowMapper) {
        this.dBastaRowRepository = dBastaRowRepository;
        this.dBastaRowMapper = dBastaRowMapper;
    }

    /**
     * Save a dBastaRow.
     *
     * @param dBastaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DBastaRowDTO save(DBastaRowDTO dBastaRowDTO) {
        LOG.debug("Request to save DBastaRow : {}", dBastaRowDTO);
        DBastaRow dBastaRow = dBastaRowMapper.toEntity(dBastaRowDTO);
        dBastaRow = dBastaRowRepository.save(dBastaRow);
        return dBastaRowMapper.toDto(dBastaRow);
    }

    /**
     * Update a dBastaRow.
     *
     * @param dBastaRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DBastaRowDTO update(DBastaRowDTO dBastaRowDTO) {
        LOG.debug("Request to update DBastaRow : {}", dBastaRowDTO);
        DBastaRow dBastaRow = dBastaRowMapper.toEntity(dBastaRowDTO);
        dBastaRow = dBastaRowRepository.save(dBastaRow);
        return dBastaRowMapper.toDto(dBastaRow);
    }

    /**
     * Partially update a dBastaRow.
     *
     * @param dBastaRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DBastaRowDTO> partialUpdate(DBastaRowDTO dBastaRowDTO) {
        LOG.debug("Request to partially update DBastaRow : {}", dBastaRowDTO);

        return dBastaRowRepository
            .findById(dBastaRowDTO.getId())
            .map(existingDBastaRow -> {
                dBastaRowMapper.partialUpdate(existingDBastaRow, dBastaRowDTO);

                return existingDBastaRow;
            })
            .map(dBastaRowRepository::save)
            .map(dBastaRowMapper::toDto);
    }

    /**
     * Get one dBastaRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DBastaRowDTO> findOne(Long id) {
        LOG.debug("Request to get DBastaRow : {}", id);
        return dBastaRowRepository.findById(id).map(dBastaRowMapper::toDto);
    }

    /**
     * Delete the dBastaRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DBastaRow : {}", id);
        dBastaRowRepository.deleteById(id);
    }
}
