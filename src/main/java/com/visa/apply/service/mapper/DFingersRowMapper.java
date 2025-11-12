package com.visa.apply.service.mapper;

import com.visa.apply.domain.DFingersRow;
import com.visa.apply.service.dto.DFingersRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DFingersRow} and its DTO {@link DFingersRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DFingersRowMapper extends EntityMapper<DFingersRowDTO, DFingersRow> {}
