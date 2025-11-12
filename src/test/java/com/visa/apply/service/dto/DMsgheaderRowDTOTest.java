package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DMsgheaderRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMsgheaderRowDTO.class);
        DMsgheaderRowDTO dMsgheaderRowDTO1 = new DMsgheaderRowDTO();
        dMsgheaderRowDTO1.setId(1L);
        DMsgheaderRowDTO dMsgheaderRowDTO2 = new DMsgheaderRowDTO();
        assertThat(dMsgheaderRowDTO1).isNotEqualTo(dMsgheaderRowDTO2);
        dMsgheaderRowDTO2.setId(dMsgheaderRowDTO1.getId());
        assertThat(dMsgheaderRowDTO1).isEqualTo(dMsgheaderRowDTO2);
        dMsgheaderRowDTO2.setId(2L);
        assertThat(dMsgheaderRowDTO1).isNotEqualTo(dMsgheaderRowDTO2);
        dMsgheaderRowDTO1.setId(null);
        assertThat(dMsgheaderRowDTO1).isNotEqualTo(dMsgheaderRowDTO2);
    }
}
