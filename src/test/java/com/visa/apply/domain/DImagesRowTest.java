package com.visa.apply.domain;

import static com.visa.apply.domain.DImagesRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DImagesRowTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DImagesRow.class);
        DImagesRow dImagesRow1 = getDImagesRowSample1();
        DImagesRow dImagesRow2 = new DImagesRow();
        assertThat(dImagesRow1).isNotEqualTo(dImagesRow2);

        dImagesRow2.setId(dImagesRow1.getId());
        assertThat(dImagesRow1).isEqualTo(dImagesRow2);

        dImagesRow2 = getDImagesRowSample2();
        assertThat(dImagesRow1).isNotEqualTo(dImagesRow2);
    }
}
