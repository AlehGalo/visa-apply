package com.visa.apply.domain;

import static com.visa.apply.domain.DFingersRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DFingersRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFingersRow.class);
        DFingersRow dFingersRow1 = getDFingersRowSample1();
        DFingersRow dFingersRow2 = new DFingersRow();
        assertThat(dFingersRow1).isNotEqualTo(dFingersRow2);

        dFingersRow2.setId(dFingersRow1.getId());
        assertThat(dFingersRow1).isEqualTo(dFingersRow2);

        dFingersRow2 = getDFingersRowSample2();
        assertThat(dFingersRow1).isNotEqualTo(dFingersRow2);
    }
}
