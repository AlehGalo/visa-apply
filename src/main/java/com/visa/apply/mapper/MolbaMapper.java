package com.visa.apply.mapper;

import com.visa.apply.dto.MolbaDto;
import com.visa.apply.entity.Molba;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MolbaMapper {
    Molba toEntity(MolbaDto molbaDto);

    MolbaDto toDto(Molba molba);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Molba partialUpdate(MolbaDto molbaDto, @MappingTarget Molba molba);
}