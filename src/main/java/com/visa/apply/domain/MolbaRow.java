package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A MolbaRow.
 */
@Entity
@Table(name = "molba_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MolbaRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dat_vli", nullable = false)
    private LocalDate datVli;

    @NotNull
    @Column(name = "dat_izl", nullable = false)
    private LocalDate datIzl;

    @NotNull
    @Size(max = 255)
    @Column(name = "vidvis", length = 255, nullable = false)
    private String vidvis;

    @Column(name = "brvl")
    private Integer brvl;

    @NotNull
    @Size(max = 255)
    @Column(name = "vidus", length = 255, nullable = false)
    private String vidus;

    @NotNull
    @Size(max = 255)
    @Column(name = "valvis", length = 255, nullable = false)
    private String valvis;

    @Column(name = "brdni")
    private Integer brdni;

    @Column(name = "cel")
    private Integer cel;

    @NotNull
    @Column(name = "mol_dat_vav", nullable = false)
    private Instant molDatVav;

    @NotNull
    @Size(max = 255)
    @Column(name = "gratis", length = 255, nullable = false)
    private String gratis;

    @NotNull
    @Size(max = 255)
    @Column(name = "imavisa", length = 255, nullable = false)
    private String imavisa;

    @Column(name = "cenamol")
    private Integer cenamol;

    @NotNull
    @Size(max = 255)
    @Column(name = "cenacurr", length = 255, nullable = false)
    private String cenacurr;

    @NotNull
    @Size(max = 255)
    @Column(name = "maindest", length = 255, nullable = false)
    private String maindest;

    @NotNull
    @Size(max = 255)
    @Column(name = "maindestnm", length = 255, nullable = false)
    private String maindestnm;

    @NotNull
    @Size(max = 255)
    @Column(name = "gkpp_darj", length = 255, nullable = false)
    private String gkppDarj;

    @NotNull
    @Size(max = 255)
    @Column(name = "gkpp_text", length = 255, nullable = false)
    private String gkppText;

    @NotNull
    @Size(max = 255)
    @Column(name = "text_ini", length = 255, nullable = false)
    private String textIni;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MolbaRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatVli() {
        return this.datVli;
    }

    public MolbaRow datVli(LocalDate datVli) {
        this.setDatVli(datVli);
        return this;
    }

    public void setDatVli(LocalDate datVli) {
        this.datVli = datVli;
    }

    public LocalDate getDatIzl() {
        return this.datIzl;
    }

    public MolbaRow datIzl(LocalDate datIzl) {
        this.setDatIzl(datIzl);
        return this;
    }

    public void setDatIzl(LocalDate datIzl) {
        this.datIzl = datIzl;
    }

    public String getVidvis() {
        return this.vidvis;
    }

    public MolbaRow vidvis(String vidvis) {
        this.setVidvis(vidvis);
        return this;
    }

    public void setVidvis(String vidvis) {
        this.vidvis = vidvis;
    }

    public Integer getBrvl() {
        return this.brvl;
    }

    public MolbaRow brvl(Integer brvl) {
        this.setBrvl(brvl);
        return this;
    }

    public void setBrvl(Integer brvl) {
        this.brvl = brvl;
    }

    public String getVidus() {
        return this.vidus;
    }

    public MolbaRow vidus(String vidus) {
        this.setVidus(vidus);
        return this;
    }

    public void setVidus(String vidus) {
        this.vidus = vidus;
    }

    public String getValvis() {
        return this.valvis;
    }

    public MolbaRow valvis(String valvis) {
        this.setValvis(valvis);
        return this;
    }

    public void setValvis(String valvis) {
        this.valvis = valvis;
    }

    public Integer getBrdni() {
        return this.brdni;
    }

    public MolbaRow brdni(Integer brdni) {
        this.setBrdni(brdni);
        return this;
    }

    public void setBrdni(Integer brdni) {
        this.brdni = brdni;
    }

    public Integer getCel() {
        return this.cel;
    }

    public MolbaRow cel(Integer cel) {
        this.setCel(cel);
        return this;
    }

    public void setCel(Integer cel) {
        this.cel = cel;
    }

    public Instant getMolDatVav() {
        return this.molDatVav;
    }

    public MolbaRow molDatVav(Instant molDatVav) {
        this.setMolDatVav(molDatVav);
        return this;
    }

    public void setMolDatVav(Instant molDatVav) {
        this.molDatVav = molDatVav;
    }

    public String getGratis() {
        return this.gratis;
    }

    public MolbaRow gratis(String gratis) {
        this.setGratis(gratis);
        return this;
    }

    public void setGratis(String gratis) {
        this.gratis = gratis;
    }

    public String getImavisa() {
        return this.imavisa;
    }

    public MolbaRow imavisa(String imavisa) {
        this.setImavisa(imavisa);
        return this;
    }

    public void setImavisa(String imavisa) {
        this.imavisa = imavisa;
    }

    public Integer getCenamol() {
        return this.cenamol;
    }

    public MolbaRow cenamol(Integer cenamol) {
        this.setCenamol(cenamol);
        return this;
    }

    public void setCenamol(Integer cenamol) {
        this.cenamol = cenamol;
    }

    public String getCenacurr() {
        return this.cenacurr;
    }

    public MolbaRow cenacurr(String cenacurr) {
        this.setCenacurr(cenacurr);
        return this;
    }

    public void setCenacurr(String cenacurr) {
        this.cenacurr = cenacurr;
    }

    public String getMaindest() {
        return this.maindest;
    }

    public MolbaRow maindest(String maindest) {
        this.setMaindest(maindest);
        return this;
    }

    public void setMaindest(String maindest) {
        this.maindest = maindest;
    }

    public String getMaindestnm() {
        return this.maindestnm;
    }

    public MolbaRow maindestnm(String maindestnm) {
        this.setMaindestnm(maindestnm);
        return this;
    }

    public void setMaindestnm(String maindestnm) {
        this.maindestnm = maindestnm;
    }

    public String getGkppDarj() {
        return this.gkppDarj;
    }

    public MolbaRow gkppDarj(String gkppDarj) {
        this.setGkppDarj(gkppDarj);
        return this;
    }

    public void setGkppDarj(String gkppDarj) {
        this.gkppDarj = gkppDarj;
    }

    public String getGkppText() {
        return this.gkppText;
    }

    public MolbaRow gkppText(String gkppText) {
        this.setGkppText(gkppText);
        return this;
    }

    public void setGkppText(String gkppText) {
        this.gkppText = gkppText;
    }

    public String getTextIni() {
        return this.textIni;
    }

    public MolbaRow textIni(String textIni) {
        this.setTextIni(textIni);
        return this;
    }

    public void setTextIni(String textIni) {
        this.textIni = textIni;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MolbaRow)) {
            return false;
        }
        return getId() != null && getId().equals(((MolbaRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MolbaRow{" +
            "id=" + getId() +
            ", datVli='" + getDatVli() + "'" +
            ", datIzl='" + getDatIzl() + "'" +
            ", vidvis='" + getVidvis() + "'" +
            ", brvl=" + getBrvl() +
            ", vidus='" + getVidus() + "'" +
            ", valvis='" + getValvis() + "'" +
            ", brdni=" + getBrdni() +
            ", cel=" + getCel() +
            ", molDatVav='" + getMolDatVav() + "'" +
            ", gratis='" + getGratis() + "'" +
            ", imavisa='" + getImavisa() + "'" +
            ", cenamol=" + getCenamol() +
            ", cenacurr='" + getCenacurr() + "'" +
            ", maindest='" + getMaindest() + "'" +
            ", maindestnm='" + getMaindestnm() + "'" +
            ", gkppDarj='" + getGkppDarj() + "'" +
            ", gkppText='" + getGkppText() + "'" +
            ", textIni='" + getTextIni() + "'" +
            "}";
    }
}
