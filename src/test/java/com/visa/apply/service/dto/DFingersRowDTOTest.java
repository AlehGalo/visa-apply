package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DFingersRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFingersRowDTO.class);
        DFingersRowDTO dFingersRowDTO1 = new DFingersRowDTO();
        dFingersRowDTO1.setId(1L);
        DFingersRowDTO dFingersRowDTO2 = new DFingersRowDTO();
        assertThat(dFingersRowDTO1).isNotEqualTo(dFingersRowDTO2);
        dFingersRowDTO2.setId(dFingersRowDTO1.getId());
        assertThat(dFingersRowDTO1).isEqualTo(dFingersRowDTO2);
        dFingersRowDTO2.setId(2L);
        assertThat(dFingersRowDTO1).isNotEqualTo(dFingersRowDTO2);
        dFingersRowDTO1.setId(null);
        assertThat(dFingersRowDTO1).isNotEqualTo(dFingersRowDTO2);
    }
}
