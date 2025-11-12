package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DBastaRowAsserts.*;
import static com.visa.apply.domain.DBastaRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBastaRowMapperTest {

    private DBastaRowMapper dBastaRowMapper;

    @BeforeEach
    void setUp() {
        dBastaRowMapper = new DBastaRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDBastaRowSample1();
        var actual = dBastaRowMapper.toEntity(dBastaRowMapper.toDto(expected));
        assertDBastaRowAllPropertiesEquals(expected, actual);
    }
}
