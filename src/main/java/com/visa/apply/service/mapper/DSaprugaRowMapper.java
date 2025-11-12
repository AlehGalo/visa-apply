package com.visa.apply.service.mapper;

import com.visa.apply.domain.DSaprugaRow;
import com.visa.apply.service.dto.DSaprugaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DSaprugaRow} and its DTO {@link DSaprugaRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DSaprugaRowMapper extends EntityMapper<DSaprugaRowDTO, DSaprugaRow> {}
