package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DImagesRowAsserts.*;
import static com.visa.apply.domain.DImagesRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DImagesRowMapperTest {

    private DImagesRowMapper dImagesRowMapper;

    @BeforeEach
    void setUp() {
        dImagesRowMapper = new DImagesRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDImagesRowSample1();
        var actual = dImagesRowMapper.toEntity(dImagesRowMapper.toDto(expected));
        assertDImagesRowAllPropertiesEquals(expected, actual);
    }
}
