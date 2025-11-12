package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DFingersRowAsserts.*;
import static com.visa.apply.domain.DFingersRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DFingersRowMapperTest {

    private DFingersRowMapper dFingersRowMapper;

    @BeforeEach
    void setUp() {
        dFingersRowMapper = new DFingersRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDFingersRowSample1();
        var actual = dFingersRowMapper.toEntity(dFingersRowMapper.toDto(expected));
        assertDFingersRowAllPropertiesEquals(expected, actual);
    }
}
