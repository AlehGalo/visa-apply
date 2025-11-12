package com.visa.apply.domain;

import static com.visa.apply.domain.MolbaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MolbaRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MolbaRow.class);
        MolbaRow molbaRow1 = getMolbaRowSample1();
        MolbaRow molbaRow2 = new MolbaRow();
        assertThat(molbaRow1).isNotEqualTo(molbaRow2);

        molbaRow2.setId(molbaRow1.getId());
        assertThat(molbaRow1).isEqualTo(molbaRow2);

        molbaRow2 = getMolbaRowSample2();
        assertThat(molbaRow1).isNotEqualTo(molbaRow2);
    }
}
