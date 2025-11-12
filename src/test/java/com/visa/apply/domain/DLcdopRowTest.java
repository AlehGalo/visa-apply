package com.visa.apply.domain;

import static com.visa.apply.domain.DLcdopRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLcdopRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLcdopRow.class);
        DLcdopRow dLcdopRow1 = getDLcdopRowSample1();
        DLcdopRow dLcdopRow2 = new DLcdopRow();
        assertThat(dLcdopRow1).isNotEqualTo(dLcdopRow2);

        dLcdopRow2.setId(dLcdopRow1.getId());
        assertThat(dLcdopRow1).isEqualTo(dLcdopRow2);

        dLcdopRow2 = getDLcdopRowSample2();
        assertThat(dLcdopRow1).isNotEqualTo(dLcdopRow2);
    }
}
