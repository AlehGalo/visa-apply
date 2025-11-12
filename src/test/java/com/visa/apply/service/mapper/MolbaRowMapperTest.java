package com.visa.apply.service.mapper;

import static com.visa.apply.domain.MolbaRowAsserts.*;
import static com.visa.apply.domain.MolbaRowTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MolbaRowMapperTest {

    private MolbaRowMapper molbaRowMapper;

    @BeforeEach
    void setUp() {
        molbaRowMapper = new MolbaRowMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getMolbaRowSample1();
        var actual = molbaRowMapper.toEntity(molbaRowMapper.toDto(expected));
        assertMolbaRowAllPropertiesEquals(expected, actual);
    }
}
