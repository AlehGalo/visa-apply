package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DVoitRowAsserts.*;
import static com.visa.apply.domain.DVoitRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DVoitRowMapperTest {

    private DVoitRowMapper dVoitRowMapper;

    @BeforeEach
    void setUp() {
        dVoitRowMapper = new DVoitRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDVoitRowSample1();
        var actual = dVoitRowMapper.toEntity(dVoitRowMapper.toDto(expected));
        assertDVoitRowAllPropertiesEquals(expected, actual);
    }
}
