package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DSaprugaRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DSaprugaRowDTO.class);
        DSaprugaRowDTO dSaprugaRowDTO1 = new DSaprugaRowDTO();
        dSaprugaRowDTO1.setId(1L);
        DSaprugaRowDTO dSaprugaRowDTO2 = new DSaprugaRowDTO();
        assertThat(dSaprugaRowDTO1).isNotEqualTo(dSaprugaRowDTO2);
        dSaprugaRowDTO2.setId(dSaprugaRowDTO1.getId());
        assertThat(dSaprugaRowDTO1).isEqualTo(dSaprugaRowDTO2);
        dSaprugaRowDTO2.setId(2L);
        assertThat(dSaprugaRowDTO1).isNotEqualTo(dSaprugaRowDTO2);
        dSaprugaRowDTO1.setId(null);
        assertThat(dSaprugaRowDTO1).isNotEqualTo(dSaprugaRowDTO2);
    }
}
