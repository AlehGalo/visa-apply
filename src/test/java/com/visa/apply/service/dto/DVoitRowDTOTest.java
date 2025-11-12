package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DVoitRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DVoitRowDTO.class);
        DVoitRowDTO dVoitRowDTO1 = new DVoitRowDTO();
        dVoitRowDTO1.setId(1L);
        DVoitRowDTO dVoitRowDTO2 = new DVoitRowDTO();
        assertThat(dVoitRowDTO1).isNotEqualTo(dVoitRowDTO2);
        dVoitRowDTO2.setId(dVoitRowDTO1.getId());
        assertThat(dVoitRowDTO1).isEqualTo(dVoitRowDTO2);
        dVoitRowDTO2.setId(2L);
        assertThat(dVoitRowDTO1).isNotEqualTo(dVoitRowDTO2);
        dVoitRowDTO1.setId(null);
        assertThat(dVoitRowDTO1).isNotEqualTo(dVoitRowDTO2);
    }
}
