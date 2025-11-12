package com.visa.apply.domain;

import static com.visa.apply.domain.DDomakinRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DDomakinRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DDomakinRow.class);
        DDomakinRow dDomakinRow1 = getDDomakinRowSample1();
        DDomakinRow dDomakinRow2 = new DDomakinRow();
        assertThat(dDomakinRow1).isNotEqualTo(dDomakinRow2);

        dDomakinRow2.setId(dDomakinRow1.getId());
        assertThat(dDomakinRow1).isEqualTo(dDomakinRow2);

        dDomakinRow2 = getDDomakinRowSample2();
        assertThat(dDomakinRow1).isNotEqualTo(dDomakinRow2);
    }
}
