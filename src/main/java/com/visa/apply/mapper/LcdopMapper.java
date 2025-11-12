package com.visa.apply.mapper;

import com.visa.apply.dto.LcdopDto;
import com.visa.apply.entity.Lcdop;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LcdopMapper {
    Lcdop toEntity(LcdopDto lcdopDto);

    LcdopDto toDto(Lcdop lcdop);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lcdop partialUpdate(LcdopDto lcdopDto, @MappingTarget Lcdop lcdop);
}