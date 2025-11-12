package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLoadosfEntityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLoadosfEntityDTO.class);
        DLoadosfEntityDTO dLoadosfEntityDTO1 = new DLoadosfEntityDTO();
        dLoadosfEntityDTO1.setId(1L);
        DLoadosfEntityDTO dLoadosfEntityDTO2 = new DLoadosfEntityDTO();
        assertThat(dLoadosfEntityDTO1).isNotEqualTo(dLoadosfEntityDTO2);
        dLoadosfEntityDTO2.setId(dLoadosfEntityDTO1.getId());
        assertThat(dLoadosfEntityDTO1).isEqualTo(dLoadosfEntityDTO2);
        dLoadosfEntityDTO2.setId(2L);
        assertThat(dLoadosfEntityDTO1).isNotEqualTo(dLoadosfEntityDTO2);
        dLoadosfEntityDTO1.setId(null);
        assertThat(dLoadosfEntityDTO1).isNotEqualTo(dLoadosfEntityDTO2);
    }
}
