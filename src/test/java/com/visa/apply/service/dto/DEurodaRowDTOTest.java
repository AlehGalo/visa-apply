package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DEurodaRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DEurodaRowDTO.class);
        DEurodaRowDTO dEurodaRowDTO1 = new DEurodaRowDTO();
        dEurodaRowDTO1.setId(1L);
        DEurodaRowDTO dEurodaRowDTO2 = new DEurodaRowDTO();
        assertThat(dEurodaRowDTO1).isNotEqualTo(dEurodaRowDTO2);
        dEurodaRowDTO2.setId(dEurodaRowDTO1.getId());
        assertThat(dEurodaRowDTO1).isEqualTo(dEurodaRowDTO2);
        dEurodaRowDTO2.setId(2L);
        assertThat(dEurodaRowDTO1).isNotEqualTo(dEurodaRowDTO2);
        dEurodaRowDTO1.setId(null);
        assertThat(dEurodaRowDTO1).isNotEqualTo(dEurodaRowDTO2);
    }
}
