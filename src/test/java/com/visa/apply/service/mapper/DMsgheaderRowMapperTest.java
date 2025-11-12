package com.visa.apply.service.mapper;

import static com.visa.apply.domain.DMsgheaderRowAsserts.*;
import static com.visa.apply.domain.DMsgheaderRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DMsgheaderRowMapperTest {

    private DMsgheaderRowMapper dMsgheaderRowMapper;

    @BeforeEach
    void setUp() {
        dMsgheaderRowMapper = new DMsgheaderRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDMsgheaderRowSample1();
        var actual = dMsgheaderRowMapper.toEntity(dMsgheaderRowMapper.toDto(expected));
        assertDMsgheaderRowAllPropertiesEquals(expected, actual);
    }
}
