package com.visa.apply.domain;

import static com.visa.apply.domain.DBastaRowTestSamples.*;
import static com.visa.apply.domain.DDomakinRowTestSamples.*;
import static com.visa.apply.domain.DEurodaRowTestSamples.*;
import static com.visa.apply.domain.DFingersRowTestSamples.*;
import static com.visa.apply.domain.DImagesRowTestSamples.*;
import static com.visa.apply.domain.DLcdopRowTestSamples.*;
import static com.visa.apply.domain.DLcuzRowTestSamples.*;
import static com.visa.apply.domain.DLoadosfEntityTestSamples.*;
import static com.visa.apply.domain.DMaikaRowTestSamples.*;
import static com.visa.apply.domain.DMsgheaderRowTestSamples.*;
import static com.visa.apply.domain.DSaprugaRowTestSamples.*;
import static com.visa.apply.domain.DVoitRowTestSamples.*;
import static com.visa.apply.domain.MolbaRowTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.visa.apply.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DLoadosfEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DLoadosfEntity.class);
        DLoadosfEntity dLoadosfEntity1 = getDLoadosfEntitySample1();
        DLoadosfEntity dLoadosfEntity2 = new DLoadosfEntity();
        assertThat(dLoadosfEntity1).isNotEqualTo(dLoadosfEntity2);

        dLoadosfEntity2.setId(dLoadosfEntity1.getId());
        assertThat(dLoadosfEntity1).isEqualTo(dLoadosfEntity2);

        dLoadosfEntity2 = getDLoadosfEntitySample2();
        assertThat(dLoadosfEntity1).isNotEqualTo(dLoadosfEntity2);
    }

    @Test
    void msgheaderTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DMsgheaderRow dMsgheaderRowBack = getDMsgheaderRowRandomSampleGenerator();

        dLoadosfEntity.setMsgheader(dMsgheaderRowBack);
        assertThat(dLoadosfEntity.getMsgheader()).isEqualTo(dMsgheaderRowBack);

        dLoadosfEntity.msgheader(null);
        assertThat(dLoadosfEntity.getMsgheader()).isNull();
    }

    @Test
    void lcuzTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DLcuzRow dLcuzRowBack = getDLcuzRowRandomSampleGenerator();

        dLoadosfEntity.setLcuz(dLcuzRowBack);
        assertThat(dLoadosfEntity.getLcuz()).isEqualTo(dLcuzRowBack);

        dLoadosfEntity.lcuz(null);
        assertThat(dLoadosfEntity.getLcuz()).isNull();
    }

    @Test
    void lcdopTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DLcdopRow dLcdopRowBack = getDLcdopRowRandomSampleGenerator();

        dLoadosfEntity.setLcdop(dLcdopRowBack);
        assertThat(dLoadosfEntity.getLcdop()).isEqualTo(dLcdopRowBack);

        dLoadosfEntity.lcdop(null);
        assertThat(dLoadosfEntity.getLcdop()).isNull();
    }

    @Test
    void bastaEntityTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DBastaRow dBastaRowBack = getDBastaRowRandomSampleGenerator();

        dLoadosfEntity.setBastaEntity(dBastaRowBack);
        assertThat(dLoadosfEntity.getBastaEntity()).isEqualTo(dBastaRowBack);

        dLoadosfEntity.bastaEntity(null);
        assertThat(dLoadosfEntity.getBastaEntity()).isNull();
    }

    @Test
    void maikaTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DMaikaRow dMaikaRowBack = getDMaikaRowRandomSampleGenerator();

        dLoadosfEntity.setMaika(dMaikaRowBack);
        assertThat(dLoadosfEntity.getMaika()).isEqualTo(dMaikaRowBack);

        dLoadosfEntity.maika(null);
        assertThat(dLoadosfEntity.getMaika()).isNull();
    }

    @Test
    void saprugaTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DSaprugaRow dSaprugaRowBack = getDSaprugaRowRandomSampleGenerator();

        dLoadosfEntity.setSapruga(dSaprugaRowBack);
        assertThat(dLoadosfEntity.getSapruga()).isEqualTo(dSaprugaRowBack);

        dLoadosfEntity.sapruga(null);
        assertThat(dLoadosfEntity.getSapruga()).isNull();
    }

    @Test
    void molbaTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        MolbaRow molbaRowBack = getMolbaRowRandomSampleGenerator();

        dLoadosfEntity.setMolba(molbaRowBack);
        assertThat(dLoadosfEntity.getMolba()).isEqualTo(molbaRowBack);

        dLoadosfEntity.molba(null);
        assertThat(dLoadosfEntity.getMolba()).isNull();
    }

    @Test
    void domakinTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DDomakinRow dDomakinRowBack = getDDomakinRowRandomSampleGenerator();

        dLoadosfEntity.setDomakin(dDomakinRowBack);
        assertThat(dLoadosfEntity.getDomakin()).isEqualTo(dDomakinRowBack);

        dLoadosfEntity.domakin(null);
        assertThat(dLoadosfEntity.getDomakin()).isNull();
    }

    @Test
    void eurodaTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DEurodaRow dEurodaRowBack = getDEurodaRowRandomSampleGenerator();

        dLoadosfEntity.setEuroda(dEurodaRowBack);
        assertThat(dLoadosfEntity.getEuroda()).isEqualTo(dEurodaRowBack);

        dLoadosfEntity.euroda(null);
        assertThat(dLoadosfEntity.getEuroda()).isNull();
    }

    @Test
    void voitTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DVoitRow dVoitRowBack = getDVoitRowRandomSampleGenerator();

        dLoadosfEntity.setVoit(dVoitRowBack);
        assertThat(dLoadosfEntity.getVoit()).isEqualTo(dVoitRowBack);

        dLoadosfEntity.voit(null);
        assertThat(dLoadosfEntity.getVoit()).isNull();
    }

    @Test
    void imagesTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DImagesRow dImagesRowBack = getDImagesRowRandomSampleGenerator();

        dLoadosfEntity.setImages(dImagesRowBack);
        assertThat(dLoadosfEntity.getImages()).isEqualTo(dImagesRowBack);

        dLoadosfEntity.images(null);
        assertThat(dLoadosfEntity.getImages()).isNull();
    }

    @Test
    void fingersTest() {
        DLoadosfEntity dLoadosfEntity = getDLoadosfEntityRandomSampleGenerator();
        DFingersRow dFingersRowBack = getDFingersRowRandomSampleGenerator();

        dLoadosfEntity.setFingers(dFingersRowBack);
        assertThat(dLoadosfEntity.getFingers()).isEqualTo(dFingersRowBack);

        dLoadosfEntity.fingers(null);
        assertThat(dLoadosfEntity.getFingers()).isNull();
    }
}
