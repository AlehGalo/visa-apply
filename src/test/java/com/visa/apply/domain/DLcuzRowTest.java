package com.visa.apply.domain;

import static com.visa.apply.domain.DLcuzRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLcuzRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLcuzRow.class);
        DLcuzRow dLcuzRow1 = getDLcuzRowSample1();
        DLcuzRow dLcuzRow2 = new DLcuzRow();
        assertThat(dLcuzRow1).isNotEqualTo(dLcuzRow2);

        dLcuzRow2.setId(dLcuzRow1.getId());
        assertThat(dLcuzRow1).isEqualTo(dLcuzRow2);

        dLcuzRow2 = getDLcuzRowSample2();
        assertThat(dLcuzRow1).isNotEqualTo(dLcuzRow2);
    }
}
