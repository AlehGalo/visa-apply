package com.visa.apply.mapper;

import com.visa.apply.dto.LoadosfDto;
import com.visa.apply.entity.Loadosf;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoadosfMapper {
    Loadosf toEntity(LoadosfDto loadosfDto1);

    LoadosfDto toDto(Loadosf Loadosf);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Loadosf partialUpdate(LoadosfDto loadosfDto1, @MappingTarget Loadosf Loadosf);
}