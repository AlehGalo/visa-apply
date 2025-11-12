package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLcuzRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLcuzRowDTO.class);
        DLcuzRowDTO dLcuzRowDTO1 = new DLcuzRowDTO();
        dLcuzRowDTO1.setId(1L);
        DLcuzRowDTO dLcuzRowDTO2 = new DLcuzRowDTO();
        assertThat(dLcuzRowDTO1).isNotEqualTo(dLcuzRowDTO2);
        dLcuzRowDTO2.setId(dLcuzRowDTO1.getId());
        assertThat(dLcuzRowDTO1).isEqualTo(dLcuzRowDTO2);
        dLcuzRowDTO2.setId(2L);
        assertThat(dLcuzRowDTO1).isNotEqualTo(dLcuzRowDTO2);
        dLcuzRowDTO1.setId(null);
        assertThat(dLcuzRowDTO1).isNotEqualTo(dLcuzRowDTO2);
    }
}
