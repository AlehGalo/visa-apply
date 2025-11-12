package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DEurodaRowAsserts.*;
import static com.visa.apply.domain.DEurodaRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DEurodaRowMapperTest {

    private DEurodaRowMapper dEurodaRowMapper;

    @BeforeEach
    void setUp() {
        dEurodaRowMapper = new DEurodaRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDEurodaRowSample1();
        var actual = dEurodaRowMapper.toEntity(dEurodaRowMapper.toDto(expected));
        assertDEurodaRowAllPropertiesEquals(expected, actual);
    }
}
