package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DSaprugaRowAsserts.*;
import static com.visa.apply.domain.DSaprugaRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DSaprugaRowMapperTest {

    private DSaprugaRowMapper dSaprugaRowMapper;

    @BeforeEach
    void setUp() {
        dSaprugaRowMapper = new DSaprugaRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDSaprugaRowSample1();
        var actual = dSaprugaRowMapper.toEntity(dSaprugaRowMapper.toDto(expected));
        assertDSaprugaRowAllPropertiesEquals(expected, actual);
    }
}
