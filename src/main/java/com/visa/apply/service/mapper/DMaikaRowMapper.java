package com.visa.apply.service.mapper;

import com.visa.apply.domain.DMaikaRow;
import com.visa.apply.service.dto.DMaikaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DMaikaRow} and its DTO {@link DMaikaRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DMaikaRowMapper extends EntityMapper<DMaikaRowDTO, DMaikaRow> {}
