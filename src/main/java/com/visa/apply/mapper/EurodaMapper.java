package com.visa.apply.mapper;

import com.visa.apply.dto.EurodaDto;
import com.visa.apply.entity.Euroda;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EurodaMapper {
    Euroda toEntity(EurodaDto eurodaDto);

    EurodaDto toDto(Euroda euroda);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Euroda partialUpdate(EurodaDto eurodaDto, @MappingTarget Euroda euroda);
}