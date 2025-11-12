package com.visa.apply.mapper;

import com.visa.apply.dto.MsgheaderDto;
import com.visa.apply.entity.Msgheader;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MsgheaderMapper {
    Msgheader toEntity(MsgheaderDto msgheaderDto);

    MsgheaderDto toDto(Msgheader msgheader);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Msgheader partialUpdate(MsgheaderDto msgheaderDto, @MappingTarget Msgheader msgheader);
}