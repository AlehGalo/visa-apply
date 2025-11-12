package com.visa.apply.domain;

import static com.visa.apply.domain.DEurodaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DEurodaRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DEurodaRow.class);
        DEurodaRow dEurodaRow1 = getDEurodaRowSample1();
        DEurodaRow dEurodaRow2 = new DEurodaRow();
        assertThat(dEurodaRow1).isNotEqualTo(dEurodaRow2);

        dEurodaRow2.setId(dEurodaRow1.getId());
        assertThat(dEurodaRow1).isEqualTo(dEurodaRow2);

        dEurodaRow2 = getDEurodaRowSample2();
        assertThat(dEurodaRow1).isNotEqualTo(dEurodaRow2);
    }
}
