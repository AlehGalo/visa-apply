package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DMaikaRowAsserts.*;
import static com.visa.apply.domain.DMaikaRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DMaikaRowMapperTest {

    private DMaikaRowMapper dMaikaRowMapper;

    @BeforeEach
    void setUp() {
        dMaikaRowMapper = new DMaikaRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDMaikaRowSample1();
        var actual = dMaikaRowMapper.toEntity(dMaikaRowMapper.toDto(expected));
        assertDMaikaRowAllPropertiesEquals(expected, actual);
    }
}
