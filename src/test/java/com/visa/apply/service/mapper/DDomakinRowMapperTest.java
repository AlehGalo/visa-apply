package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DDomakinRowAsserts.*;
import static com.visa.apply.domain.DDomakinRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DDomakinRowMapperTest {

    private DDomakinRowMapper dDomakinRowMapper;

    @BeforeEach
    void setUp() {
        dDomakinRowMapper = new DDomakinRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDDomakinRowSample1();
        var actual = dDomakinRowMapper.toEntity(dDomakinRowMapper.toDto(expected));
        assertDDomakinRowAllPropertiesEquals(expected, actual);
    }
}
