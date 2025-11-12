package com.visa.apply.service.mapper;

import com.visa.apply.domain.MolbaRow;
import com.visa.apply.service.dto.MolbaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MolbaRow} and its DTO {@link MolbaRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface MolbaRowMapper extends EntityMapper<MolbaRowDTO, MolbaRow> {}
