package com.visa.apply.service.mapper;

import com.visa.apply.domain.DEurodaRow;
import com.visa.apply.service.dto.DEurodaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DEurodaRow} and its DTO {@link DEurodaRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DEurodaRowMapper extends EntityMapper<DEurodaRowDTO, DEurodaRow> {}
