package com.visa.apply.mapper;

import com.visa.apply.dto.BastaEntityDto;
import com.visa.apply.entity.Basta;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BastaEntityMapper {
    Basta toEntity(BastaEntityDto bastaEntityDto);

    BastaEntityDto toDto(Basta basta);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Basta partialUpdate(BastaEntityDto bastaEntityDto, @MappingTarget Basta basta);
}