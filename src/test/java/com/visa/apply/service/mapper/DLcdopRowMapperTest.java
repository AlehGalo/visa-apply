package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DLcdopRowAsserts.*;
import static com.visa.apply.domain.DLcdopRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DLcdopRowMapperTest {

    private DLcdopRowMapper dLcdopRowMapper;

    @BeforeEach
    void setUp() {
        dLcdopRowMapper = new DLcdopRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDLcdopRowSample1();
        var actual = dLcdopRowMapper.toEntity(dLcdopRowMapper.toDto(expected));
        assertDLcdopRowAllPropertiesEquals(expected, actual);
    }
}
