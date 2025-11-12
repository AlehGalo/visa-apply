package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MolbaRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MolbaRowDTO.class);
        MolbaRowDTO molbaRowDTO1 = new MolbaRowDTO();
        molbaRowDTO1.setId(1L);
        MolbaRowDTO molbaRowDTO2 = new MolbaRowDTO();
        assertThat(molbaRowDTO1).isNotEqualTo(molbaRowDTO2);
        molbaRowDTO2.setId(molbaRowDTO1.getId());
        assertThat(molbaRowDTO1).isEqualTo(molbaRowDTO2);
        molbaRowDTO2.setId(2L);
        assertThat(molbaRowDTO1).isNotEqualTo(molbaRowDTO2);
        molbaRowDTO1.setId(null);
        assertThat(molbaRowDTO1).isNotEqualTo(molbaRowDTO2);
    }
}
