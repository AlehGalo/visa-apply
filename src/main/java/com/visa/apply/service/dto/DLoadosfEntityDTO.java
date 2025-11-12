package com.visa.apply.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DLoadosfEntity} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLoadosfEntityDTO implements Serializable {

    private Long id;

    private Double version;

    private DMsgheaderRowDTO msgheader;

    private DLcuzRowDTO lcuz;

    private DLcdopRowDTO lcdop;

    private DBastaRowDTO bastaEntity;

    private DMaikaRowDTO maika;

    private DSaprugaRowDTO sapruga;

    private MolbaRowDTO molba;

    private DDomakinRowDTO domakin;

    private DEurodaRowDTO euroda;

    private DVoitRowDTO voit;

    private DImagesRowDTO images;

    private DFingersRowDTO fingers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public DMsgheaderRowDTO getMsgheader() {
        return msgheader;
    }

    public void setMsgheader(DMsgheaderRowDTO msgheader) {
        this.msgheader = msgheader;
    }

    public DLcuzRowDTO getLcuz() {
        return lcuz;
    }

    public void setLcuz(DLcuzRowDTO lcuz) {
        this.lcuz = lcuz;
    }

    public DLcdopRowDTO getLcdop() {
        return lcdop;
    }

    public void setLcdop(DLcdopRowDTO lcdop) {
        this.lcdop = lcdop;
    }

    public DBastaRowDTO getBastaEntity() {
        return bastaEntity;
    }

    public void setBastaEntity(DBastaRowDTO bastaEntity) {
        this.bastaEntity = bastaEntity;
    }

    public DMaikaRowDTO getMaika() {
        return maika;
    }

    public void setMaika(DMaikaRowDTO maika) {
        this.maika = maika;
    }

    public DSaprugaRowDTO getSapruga() {
        return sapruga;
    }

    public void setSapruga(DSaprugaRowDTO sapruga) {
        this.sapruga = sapruga;
    }

    public MolbaRowDTO getMolba() {
        return molba;
    }

    public void setMolba(MolbaRowDTO molba) {
        this.molba = molba;
    }

    public DDomakinRowDTO getDomakin() {
        return domakin;
    }

    public void setDomakin(DDomakinRowDTO domakin) {
        this.domakin = domakin;
    }

    public DEurodaRowDTO getEuroda() {
        return euroda;
    }

    public void setEuroda(DEurodaRowDTO euroda) {
        this.euroda = euroda;
    }

    public DVoitRowDTO getVoit() {
        return voit;
    }

    public void setVoit(DVoitRowDTO voit) {
        this.voit = voit;
    }

    public DImagesRowDTO getImages() {
        return images;
    }

    public void setImages(DImagesRowDTO images) {
        this.images = images;
    }

    public DFingersRowDTO getFingers() {
        return fingers;
    }

    public void setFingers(DFingersRowDTO fingers) {
        this.fingers = fingers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLoadosfEntityDTO)) {
            return false;
        }

        DLoadosfEntityDTO dLoadosfEntityDTO = (DLoadosfEntityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dLoadosfEntityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLoadosfEntityDTO{" +
            "id=" + getId() +
            ", version=" + getVersion() +
            ", msgheader=" + getMsgheader() +
            ", lcuz=" + getLcuz() +
            ", lcdop=" + getLcdop() +
            ", bastaEntity=" + getBastaEntity() +
            ", maika=" + getMaika() +
            ", sapruga=" + getSapruga() +
            ", molba=" + getMolba() +
            ", domakin=" + getDomakin() +
            ", euroda=" + getEuroda() +
            ", voit=" + getVoit() +
            ", images=" + getImages() +
            ", fingers=" + getFingers() +
            "}";
    }
}
