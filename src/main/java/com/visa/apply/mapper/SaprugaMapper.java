package com.visa.apply.mapper;

import com.visa.apply.dto.SaprugaDto;
import com.visa.apply.entity.Sapruga;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaprugaMapper {
    Sapruga toEntity(SaprugaDto saprugaDto);

    SaprugaDto toDto(Sapruga sapruga);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sapruga partialUpdate(SaprugaDto saprugaDto, @MappingTarget Sapruga sapruga);
}