package com.visa.apply.mapper;

import com.visa.apply.dto.LcuzDto;
import com.visa.apply.entity.Lcuz;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LcuzMapper {
    Lcuz toEntity(LcuzDto lcuzDto);

    LcuzDto toDto(Lcuz lcuz);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lcuz partialUpdate(LcuzDto lcuzDto, @MappingTarget Lcuz lcuz);
}