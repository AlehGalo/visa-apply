package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DImagesRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DImagesRowDTO.class);
        DImagesRowDTO dImagesRowDTO1 = new DImagesRowDTO();
        dImagesRowDTO1.setId(1L);
        DImagesRowDTO dImagesRowDTO2 = new DImagesRowDTO();
        assertThat(dImagesRowDTO1).isNotEqualTo(dImagesRowDTO2);
        dImagesRowDTO2.setId(dImagesRowDTO1.getId());
        assertThat(dImagesRowDTO1).isEqualTo(dImagesRowDTO2);
        dImagesRowDTO2.setId(2L);
        assertThat(dImagesRowDTO1).isNotEqualTo(dImagesRowDTO2);
        dImagesRowDTO1.setId(null);
        assertThat(dImagesRowDTO1).isNotEqualTo(dImagesRowDTO2);
    }
}
