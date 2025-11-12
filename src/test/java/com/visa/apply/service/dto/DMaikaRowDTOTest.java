package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DMaikaRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMaikaRowDTO.class);
        DMaikaRowDTO dMaikaRowDTO1 = new DMaikaRowDTO();
        dMaikaRowDTO1.setId(1L);
        DMaikaRowDTO dMaikaRowDTO2 = new DMaikaRowDTO();
        assertThat(dMaikaRowDTO1).isNotEqualTo(dMaikaRowDTO2);
        dMaikaRowDTO2.setId(dMaikaRowDTO1.getId());
        assertThat(dMaikaRowDTO1).isEqualTo(dMaikaRowDTO2);
        dMaikaRowDTO2.setId(2L);
        assertThat(dMaikaRowDTO1).isNotEqualTo(dMaikaRowDTO2);
        dMaikaRowDTO1.setId(null);
        assertThat(dMaikaRowDTO1).isNotEqualTo(dMaikaRowDTO2);
    }
}
