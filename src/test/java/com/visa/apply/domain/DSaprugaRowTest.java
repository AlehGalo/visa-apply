package com.visa.apply.domain;

import static com.visa.apply.domain.DSaprugaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DSaprugaRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DSaprugaRow.class);
        DSaprugaRow dSaprugaRow1 = getDSaprugaRowSample1();
        DSaprugaRow dSaprugaRow2 = new DSaprugaRow();
        assertThat(dSaprugaRow1).isNotEqualTo(dSaprugaRow2);

        dSaprugaRow2.setId(dSaprugaRow1.getId());
        assertThat(dSaprugaRow1).isEqualTo(dSaprugaRow2);

        dSaprugaRow2 = getDSaprugaRowSample2();
        assertThat(dSaprugaRow1).isNotEqualTo(dSaprugaRow2);
    }
}
