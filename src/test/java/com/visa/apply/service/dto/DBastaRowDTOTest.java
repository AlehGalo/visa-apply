package com.visa.apply.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DBastaRowDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DBastaRowDTO.class);
        DBastaRowDTO dBastaRowDTO1 = new DBastaRowDTO();
        dBastaRowDTO1.setId(1L);
        DBastaRowDTO dBastaRowDTO2 = new DBastaRowDTO();
        assertThat(dBastaRowDTO1).isNotEqualTo(dBastaRowDTO2);
        dBastaRowDTO2.setId(dBastaRowDTO1.getId());
        assertThat(dBastaRowDTO1).isEqualTo(dBastaRowDTO2);
        dBastaRowDTO2.setId(2L);
        assertThat(dBastaRowDTO1).isNotEqualTo(dBastaRowDTO2);
        dBastaRowDTO1.setId(null);
        assertThat(dBastaRowDTO1).isNotEqualTo(dBastaRowDTO2);
    }
}
