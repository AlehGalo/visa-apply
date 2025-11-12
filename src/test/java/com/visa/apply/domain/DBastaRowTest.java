package com.visa.apply.domain;

import static com.visa.apply.domain.DBastaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DBastaRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DBastaRow.class);
        DBastaRow dBastaRow1 = getDBastaRowSample1();
        DBastaRow dBastaRow2 = new DBastaRow();
        assertThat(dBastaRow1).isNotEqualTo(dBastaRow2);

        dBastaRow2.setId(dBastaRow1.getId());
        assertThat(dBastaRow1).isEqualTo(dBastaRow2);

        dBastaRow2 = getDBastaRowSample2();
        assertThat(dBastaRow1).isNotEqualTo(dBastaRow2);
    }
}
