package com.visa.apply.mapper;

import com.visa.apply.dto.DomakinDto;
import com.visa.apply.entity.Domakin;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DomakinMapper {
    Domakin toEntity(DomakinDto domakinDto);

    DomakinDto toDto(Domakin domakin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Domakin partialUpdate(DomakinDto domakinDto, @MappingTarget Domakin domakin);
}