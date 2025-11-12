package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DLoadosfEntityAsserts.*;
import static com.visa.apply.domain.DLoadosfEntityTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DLoadosfEntityMapperTest {

    private DLoadosfEntityMapper dLoadosfEntityMapper;

    @BeforeEach
    void setUp() {
        dLoadosfEntityMapper = new DLoadosfEntityMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDLoadosfEntitySample1();
        var actual = dLoadosfEntityMapper.toEntity(dLoadosfEntityMapper.toDto(expected));
        assertDLoadosfEntityAllPropertiesEquals(expected, actual);
    }
}
