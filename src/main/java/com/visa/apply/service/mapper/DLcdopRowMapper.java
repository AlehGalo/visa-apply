package com.visa.apply.service.mapper;

import com.visa.apply.domain.DLcdopRow;
import com.visa.apply.service.dto.DLcdopRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DLcdopRow} and its DTO {@link DLcdopRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DLcdopRowMapper extends EntityMapper<DLcdopRowDTO, DLcdopRow> {}
