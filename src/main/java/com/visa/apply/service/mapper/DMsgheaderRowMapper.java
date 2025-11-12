package com.visa.apply.service.mapper;

import com.visa.apply.domain.DMsgheaderRow;
import com.visa.apply.service.dto.DMsgheaderRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DMsgheaderRow} and its DTO {@link DMsgheaderRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DMsgheaderRowMapper extends EntityMapper<DMsgheaderRowDTO, DMsgheaderRow> {}
