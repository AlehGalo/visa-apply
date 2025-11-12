package com.visa.apply.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DLoadosfEntity.
 */
@Entity
@Table(name = "d_loadosf_entity")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLoadosfEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "version")
    private Double version;

    @ManyToOne(fetch = FetchType.LAZY)
    private DMsgheaderRow msgheader;

    @ManyToOne(fetch = FetchType.LAZY)
    private DLcuzRow lcuz;

    @ManyToOne(fetch = FetchType.LAZY)
    private DLcdopRow lcdop;

    @ManyToOne(fetch = FetchType.LAZY)
    private DBastaRow bastaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private DMaikaRow maika;

    @ManyToOne(fetch = FetchType.LAZY)
    private DSaprugaRow sapruga;

    @ManyToOne(fetch = FetchType.LAZY)
    private MolbaRow molba;

    @ManyToOne(fetch = FetchType.LAZY)
    private DDomakinRow domakin;

    @ManyToOne(fetch = FetchType.LAZY)
    private DEurodaRow euroda;

    @ManyToOne(fetch = FetchType.LAZY)
    private DVoitRow voit;

    @ManyToOne(fetch = FetchType.LAZY)
    private DImagesRow images;

    @ManyToOne(fetch = FetchType.LAZY)
    private DFingersRow fingers;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DLoadosfEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVersion() {
        return this.version;
    }

    public DLoadosfEntity version(Double version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public DMsgheaderRow getMsgheader() {
        return this.msgheader;
    }

    public void setMsgheader(DMsgheaderRow dMsgheaderRow) {
        this.msgheader = dMsgheaderRow;
    }

    public DLoadosfEntity msgheader(DMsgheaderRow dMsgheaderRow) {
        this.setMsgheader(dMsgheaderRow);
        return this;
    }

    public DLcuzRow getLcuz() {
        return this.lcuz;
    }

    public void setLcuz(DLcuzRow dLcuzRow) {
        this.lcuz = dLcuzRow;
    }

    public DLoadosfEntity lcuz(DLcuzRow dLcuzRow) {
        this.setLcuz(dLcuzRow);
        return this;
    }

    public DLcdopRow getLcdop() {
        return this.lcdop;
    }

    public void setLcdop(DLcdopRow dLcdopRow) {
        this.lcdop = dLcdopRow;
    }

    public DLoadosfEntity lcdop(DLcdopRow dLcdopRow) {
        this.setLcdop(dLcdopRow);
        return this;
    }

    public DBastaRow getBastaEntity() {
        return this.bastaEntity;
    }

    public void setBastaEntity(DBastaRow dBastaRow) {
        this.bastaEntity = dBastaRow;
    }

    public DLoadosfEntity bastaEntity(DBastaRow dBastaRow) {
        this.setBastaEntity(dBastaRow);
        return this;
    }

    public DMaikaRow getMaika() {
        return this.maika;
    }

    public void setMaika(DMaikaRow dMaikaRow) {
        this.maika = dMaikaRow;
    }

    public DLoadosfEntity maika(DMaikaRow dMaikaRow) {
        this.setMaika(dMaikaRow);
        return this;
    }

    public DSaprugaRow getSapruga() {
        return this.sapruga;
    }

    public void setSapruga(DSaprugaRow dSaprugaRow) {
        this.sapruga = dSaprugaRow;
    }

    public DLoadosfEntity sapruga(DSaprugaRow dSaprugaRow) {
        this.setSapruga(dSaprugaRow);
        return this;
    }

    public MolbaRow getMolba() {
        return this.molba;
    }

    public void setMolba(MolbaRow molbaRow) {
        this.molba = molbaRow;
    }

    public DLoadosfEntity molba(MolbaRow molbaRow) {
        this.setMolba(molbaRow);
        return this;
    }

    public DDomakinRow getDomakin() {
        return this.domakin;
    }

    public void setDomakin(DDomakinRow dDomakinRow) {
        this.domakin = dDomakinRow;
    }

    public DLoadosfEntity domakin(DDomakinRow dDomakinRow) {
        this.setDomakin(dDomakinRow);
        return this;
    }

    public DEurodaRow getEuroda() {
        return this.euroda;
    }

    public void setEuroda(DEurodaRow dEurodaRow) {
        this.euroda = dEurodaRow;
    }

    public DLoadosfEntity euroda(DEurodaRow dEurodaRow) {
        this.setEuroda(dEurodaRow);
        return this;
    }

    public DVoitRow getVoit() {
        return this.voit;
    }

    public void setVoit(DVoitRow dVoitRow) {
        this.voit = dVoitRow;
    }

    public DLoadosfEntity voit(DVoitRow dVoitRow) {
        this.setVoit(dVoitRow);
        return this;
    }

    public DImagesRow getImages() {
        return this.images;
    }

    public void setImages(DImagesRow dImagesRow) {
        this.images = dImagesRow;
    }

    public DLoadosfEntity images(DImagesRow dImagesRow) {
        this.setImages(dImagesRow);
        return this;
    }

    public DFingersRow getFingers() {
        return this.fingers;
    }

    public void setFingers(DFingersRow dFingersRow) {
        this.fingers = dFingersRow;
    }

    public DLoadosfEntity fingers(DFingersRow dFingersRow) {
        this.setFingers(dFingersRow);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLoadosfEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((DLoadosfEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLoadosfEntity{" +
            "id=" + getId() +
            ", version=" + getVersion() +
            "}";
    }
}
