package com.visa.apply.mapper;

import com.visa.apply.dto.MaikaDto;
import com.visa.apply.entity.Maika;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaikaMapper {
    Maika toEntity(MaikaDto maikaDto);

    MaikaDto toDto(Maika maika);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Maika partialUpdate(MaikaDto maikaDto, @MappingTarget Maika maika);
}