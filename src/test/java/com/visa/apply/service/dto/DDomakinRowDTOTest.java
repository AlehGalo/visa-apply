package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DDomakinRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DDomakinRowDTO.class);
        DDomakinRowDTO dDomakinRowDTO1 = new DDomakinRowDTO();
        dDomakinRowDTO1.setId(1L);
        DDomakinRowDTO dDomakinRowDTO2 = new DDomakinRowDTO();
        assertThat(dDomakinRowDTO1).isNotEqualTo(dDomakinRowDTO2);
        dDomakinRowDTO2.setId(dDomakinRowDTO1.getId());
        assertThat(dDomakinRowDTO1).isEqualTo(dDomakinRowDTO2);
        dDomakinRowDTO2.setId(2L);
        assertThat(dDomakinRowDTO1).isNotEqualTo(dDomakinRowDTO2);
        dDomakinRowDTO1.setId(null);
        assertThat(dDomakinRowDTO1).isNotEqualTo(dDomakinRowDTO2);
    }
}
