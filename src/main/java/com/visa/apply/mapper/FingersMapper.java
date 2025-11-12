package com.visa.apply.mapper;

import com.visa.apply.dto.FingersDto;
import com.visa.apply.entity.Fingers;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FingersMapper {
    Fingers toEntity(FingersDto fingersDto);

    FingersDto toDto(Fingers fingers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Fingers partialUpdate(FingersDto fingersDto, @MappingTarget Fingers fingers);
}