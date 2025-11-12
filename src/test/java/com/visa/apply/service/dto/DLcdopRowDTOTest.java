package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLcdopRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLcdopRowDTO.class);
        DLcdopRowDTO dLcdopRowDTO1 = new DLcdopRowDTO();
        dLcdopRowDTO1.setId(1L);
        DLcdopRowDTO dLcdopRowDTO2 = new DLcdopRowDTO();
        assertThat(dLcdopRowDTO1).isNotEqualTo(dLcdopRowDTO2);
        dLcdopRowDTO2.setId(dLcdopRowDTO1.getId());
        assertThat(dLcdopRowDTO1).isEqualTo(dLcdopRowDTO2);
        dLcdopRowDTO2.setId(2L);
        assertThat(dLcdopRowDTO1).isNotEqualTo(dLcdopRowDTO2);
        dLcdopRowDTO1.setId(null);
        assertThat(dLcdopRowDTO1).isNotEqualTo(dLcdopRowDTO2);
    }
}
