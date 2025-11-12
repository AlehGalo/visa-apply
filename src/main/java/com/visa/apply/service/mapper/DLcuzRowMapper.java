package com.visa.apply.service.mapper;

import com.visa.apply.domain.DLcuzRow;
import com.visa.apply.service.dto.DLcuzRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DLcuzRow} and its DTO {@link DLcuzRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DLcuzRowMapper extends EntityMapper<DLcuzRowDTO, DLcuzRow> {}
