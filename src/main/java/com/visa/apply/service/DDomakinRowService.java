package com.visa.apply.service;

import com.visa.apply.domain.DDomakinRow;
import com.visa.apply.repository.DDomakinRowRepository;
import com.visa.apply.service.dto.DDomakinRowDTO;
import com.visa.apply.service.mapper.DDomakinRowMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.visa.apply.domain.DDomakinRow}.
 */
@Service
@Transactional
public class DDomakinRowService {

    private static final Logger LOG = LoggerFactory.getLogger(DDomakinRowService.class);

    private final DDomakinRowRepository dDomakinRowRepository;

    private final DDomakinRowMapper dDomakinRowMapper;

    public DDomakinRowService(DDomakinRowRepository dDomakinRowRepository, DDomakinRowMapper dDomakinRowMapper) {
        this.dDomakinRowRepository = dDomakinRowRepository;
        this.dDomakinRowMapper = dDomakinRowMapper;
    }

    /**
     * Save a dDomakinRow.
     *
     * @param dDomakinRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DDomakinRowDTO save(DDomakinRowDTO dDomakinRowDTO) {
        LOG.debug("Request to save DDomakinRow : {}", dDomakinRowDTO);
        DDomakinRow dDomakinRow = dDomakinRowMapper.toEntity(dDomakinRowDTO);
        dDomakinRow = dDomakinRowRepository.save(dDomakinRow);
        return dDomakinRowMapper.toDto(dDomakinRow);
    }

    /**
     * Update a dDomakinRow.
     *
     * @param dDomakinRowDTO the entity to save.
     * @return the persisted entity.
     */
    public DDomakinRowDTO update(DDomakinRowDTO dDomakinRowDTO) {
        LOG.debug("Request to update DDomakinRow : {}", dDomakinRowDTO);
        DDomakinRow dDomakinRow = dDomakinRowMapper.toEntity(dDomakinRowDTO);
        dDomakinRow = dDomakinRowRepository.save(dDomakinRow);
        return dDomakinRowMapper.toDto(dDomakinRow);
    }

    /**
     * Partially update a dDomakinRow.
     *
     * @param dDomakinRowDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DDomakinRowDTO> partialUpdate(DDomakinRowDTO dDomakinRowDTO) {
        LOG.debug("Request to partially update DDomakinRow : {}", dDomakinRowDTO);

        return dDomakinRowRepository
            .findById(dDomakinRowDTO.getId())
            .map(existingDDomakinRow -> {
                dDomakinRowMapper.partialUpdate(existingDDomakinRow, dDomakinRowDTO);

                return existingDDomakinRow;
            })
            .map(dDomakinRowRepository::save)
            .map(dDomakinRowMapper::toDto);
    }

    /**
     * Get one dDomakinRow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DDomakinRowDTO> findOne(Long id) {
        LOG.debug("Request to get DDomakinRow : {}", id);
        return dDomakinRowRepository.findById(id).map(dDomakinRowMapper::toDto);
    }

    /**
     * Delete the dDomakinRow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DDomakinRow : {}", id);
        dDomakinRowRepository.deleteById(id);
    }
}
