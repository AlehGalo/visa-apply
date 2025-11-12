package com.visa.apply.service.mapper;

import com.visa.apply.domain.DImagesRow;
import com.visa.apply.service.dto.DImagesRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DImagesRow} and its DTO {@link DImagesRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DImagesRowMapper extends EntityMapper<DImagesRowDTO, DImagesRow> {}
