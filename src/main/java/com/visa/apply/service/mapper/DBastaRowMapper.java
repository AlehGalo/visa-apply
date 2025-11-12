package com.visa.apply.service.mapper;

import com.visa.apply.domain.DBastaRow;
import com.visa.apply.service.dto.DBastaRowDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DBastaRow} and its DTO {@link DBastaRowDTO}.
 */
@Mapper(componentModel = "spring")
public interface DBastaRowMapper extends EntityMapper<DBastaRowDTO, DBastaRow> {}
