package com.visa.apply.domain;

import static com.visa.apply.domain.DVoitRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DVoitRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DVoitRow.class);
        DVoitRow dVoitRow1 = getDVoitRowSample1();
        DVoitRow dVoitRow2 = new DVoitRow();
        assertThat(dVoitRow1).isNotEqualTo(dVoitRow2);

        dVoitRow2.setId(dVoitRow1.getId());
        assertThat(dVoitRow1).isEqualTo(dVoitRow2);

        dVoitRow2 = getDVoitRowSample2();
        assertThat(dVoitRow1).isNotEqualTo(dVoitRow2);
    }
}
