package com.visa.apply.mapper;

import com.visa.apply.dto.VoitDto;
import com.visa.apply.entity.Voit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoitMapper {
    Voit toEntity(VoitDto voitDto);

    VoitDto toDto(Voit voit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Voit partialUpdate(VoitDto voitDto, @MappingTarget Voit voit);
}