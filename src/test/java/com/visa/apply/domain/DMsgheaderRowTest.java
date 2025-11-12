package com.visa.apply.domain;

import static com.visa.apply.domain.DMsgheaderRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DMsgheaderRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DMsgheaderRow.class);
        DMsgheaderRow dMsgheaderRow1 = getDMsgheaderRowSample1();
        DMsgheaderRow dMsgheaderRow2 = new DMsgheaderRow();
        assertThat(dMsgheaderRow1).isNotEqualTo(dMsgheaderRow2);

        dMsgheaderRow2.setId(dMsgheaderRow1.getId());
        assertThat(dMsgheaderRow1).isEqualTo(dMsgheaderRow2);

        dMsgheaderRow2 = getDMsgheaderRowSample2();
        assertThat(dMsgheaderRow1).isNotEqualTo(dMsgheaderRow2);
    }
}
