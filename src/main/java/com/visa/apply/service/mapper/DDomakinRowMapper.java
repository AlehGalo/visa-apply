package com.visa.apply.service.mapper;

import com.visa.apply.domain.DDomakinRow;
import com.visa.apply.service.dto.DDomakinRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DDomakinRow} and its DTO {@link DDomakinRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DDomakinRowMapper extends EntityMapper<DDomakinRowDTO, DDomakinRow> {}
