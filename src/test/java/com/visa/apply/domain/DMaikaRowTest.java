package com.visa.apply.domain;

import static com.visa.apply.domain.DMaikaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DMaikaRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMaikaRow.class);
        DMaikaRow dMaikaRow1 = getDMaikaRowSample1();
        DMaikaRow dMaikaRow2 = new DMaikaRow();
        assertThat(dMaikaRow1).isNotEqualTo(dMaikaRow2);

        dMaikaRow2.setId(dMaikaRow1.getId());
        assertThat(dMaikaRow1).isEqualTo(dMaikaRow2);

        dMaikaRow2 = getDMaikaRowSample2();
        assertThat(dMaikaRow1).isNotEqualTo(dMaikaRow2);
    }
}
