package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DLcuzRowAsserts.*;
import static com.visa.apply.domain.DLcuzRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DLcuzRowMapperTest {

    private DLcuzRowMapper dLcuzRowMapper;

    @BeforeEach
    void setUp() {
        dLcuzRowMapper = new DLcuzRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDLcuzRowSample1();
        var actual = dLcuzRowMapper.toEntity(dLcuzRowMapper.toDto(expected));
        assertDLcuzRowAllPropertiesEquals(expected, actual);
    }
}
