package com.visa.apply.service.mapper;

import com.visa.apply.domain.DVoitRow;
import com.visa.apply.service.dto.DVoitRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DVoitRow} and its DTO {@link DVoitRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DVoitRowMapper extends EntityMapper<DVoitRowDTO, DVoitRow> {}
