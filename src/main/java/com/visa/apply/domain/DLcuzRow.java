package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DLcuzRow.
 */
@Entity
@Table(name = "d_lcuz_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcuzRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "vid_zp", length = 255, nullable = false)
    private String vidZp;

    @NotNull
    @Size(max = 255)
    @Column(name = "nac_bel", length = 255, nullable = false)
    private String nacBel;

    @Column(name = "nac_pasp")
    private Integer nacPasp;

    @NotNull
    @Column(name = "pasp_val", nullable = false)
    private LocalDate paspVal;

    @NotNull
    @Size(max = 255)
    @Column(name = "graj", length = 255, nullable = false)
    private String graj;

    @NotNull
    @Size(max = 255)
    @Column(name = "famil", length = 255, nullable = false)
    private String famil;

    @NotNull
    @Size(max = 255)
    @Column(name = "imena", length = 255, nullable = false)
    private String imena;

    @NotNull
    @Size(max = 255)
    @Column(name = "dat_raj", length = 255, nullable = false)
    private String datRaj;

    @NotNull
    @Size(max = 255)
    @Column(name = "pol", length = 255, nullable = false)
    private String pol;

    @NotNull
    @Column(name = "dat_izd", nullable = false)
    private LocalDate datIzd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DLcuzRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVidZp() {
        return this.vidZp;
    }

    public DLcuzRow vidZp(String vidZp) {
        this.setVidZp(vidZp);
        return this;
    }

    public void setVidZp(String vidZp) {
        this.vidZp = vidZp;
    }

    public String getNacBel() {
        return this.nacBel;
    }

    public DLcuzRow nacBel(String nacBel) {
        this.setNacBel(nacBel);
        return this;
    }

    public void setNacBel(String nacBel) {
        this.nacBel = nacBel;
    }

    public Integer getNacPasp() {
        return this.nacPasp;
    }

    public DLcuzRow nacPasp(Integer nacPasp) {
        this.setNacPasp(nacPasp);
        return this;
    }

    public void setNacPasp(Integer nacPasp) {
        this.nacPasp = nacPasp;
    }

    public LocalDate getPaspVal() {
        return this.paspVal;
    }

    public DLcuzRow paspVal(LocalDate paspVal) {
        this.setPaspVal(paspVal);
        return this;
    }

    public void setPaspVal(LocalDate paspVal) {
        this.paspVal = paspVal;
    }

    public String getGraj() {
        return this.graj;
    }

    public DLcuzRow graj(String graj) {
        this.setGraj(graj);
        return this;
    }

    public void setGraj(String graj) {
        this.graj = graj;
    }

    public String getFamil() {
        return this.famil;
    }

    public DLcuzRow famil(String famil) {
        this.setFamil(famil);
        return this;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public String getImena() {
        return this.imena;
    }

    public DLcuzRow imena(String imena) {
        this.setImena(imena);
        return this;
    }

    public void setImena(String imena) {
        this.imena = imena;
    }

    public String getDatRaj() {
        return this.datRaj;
    }

    public DLcuzRow datRaj(String datRaj) {
        this.setDatRaj(datRaj);
        return this;
    }

    public void setDatRaj(String datRaj) {
        this.datRaj = datRaj;
    }

    public String getPol() {
        return this.pol;
    }

    public DLcuzRow pol(String pol) {
        this.setPol(pol);
        return this;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public LocalDate getDatIzd() {
        return this.datIzd;
    }

    public DLcuzRow datIzd(LocalDate datIzd) {
        this.setDatIzd(datIzd);
        return this;
    }

    public void setDatIzd(LocalDate datIzd) {
        this.datIzd = datIzd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLcuzRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DLcuzRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcuzRow{" +
            "id=" + getId() +
            ", vidZp='" + getVidZp() + "'" +
            ", nacBel='" + getNacBel() + "'" +
            ", nacPasp=" + getNacPasp() +
            ", paspVal='" + getPaspVal() + "'" +
            ", graj='" + getGraj() + "'" +
            ", famil='" + getFamil() + "'" +
            ", imena='" + getImena() + "'" +
            ", datRaj='" + getDatRaj() + "'" +
            ", pol='" + getPol() + "'" +
            ", datIzd='" + getDatIzd() + "'" +
            "}";
    }
}
