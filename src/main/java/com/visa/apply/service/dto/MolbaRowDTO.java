package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.MolbaRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MolbaRowDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate datVli;

    @NotNull
    private LocalDate datIzl;

    @NotNull
    @Size(max = 255)
    private String vidvis;

    private Integer brvl;

    @NotNull
    @Size(max = 255)
    private String vidus;

    @NotNull
    @Size(max = 255)
    private String valvis;

    private Integer brdni;

    private Integer cel;

    @NotNull
    private Instant molDatVav;

    @NotNull
    @Size(max = 255)
    private String gratis;

    @NotNull
    @Size(max = 255)
    private String imavisa;

    private Integer cenamol;

    @NotNull
    @Size(max = 255)
    private String cenacurr;

    @NotNull
    @Size(max = 255)
    private String maindest;

    @NotNull
    @Size(max = 255)
    private String maindestnm;

    @NotNull
    @Size(max = 255)
    private String gkppDarj;

    @NotNull
    @Size(max = 255)
    private String gkppText;

    @NotNull
    @Size(max = 255)
    private String textIni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatVli() {
        return datVli;
    }

    public void setDatVli(LocalDate datVli) {
        this.datVli = datVli;
    }

    public LocalDate getDatIzl() {
        return datIzl;
    }

    public void setDatIzl(LocalDate datIzl) {
        this.datIzl = datIzl;
    }

    public String getVidvis() {
        return vidvis;
    }

    public void setVidvis(String vidvis) {
        this.vidvis = vidvis;
    }

    public Integer getBrvl() {
        return brvl;
    }

    public void setBrvl(Integer brvl) {
        this.brvl = brvl;
    }

    public String getVidus() {
        return vidus;
    }

    public void setVidus(String vidus) {
        this.vidus = vidus;
    }

    public String getValvis() {
        return valvis;
    }

    public void setValvis(String valvis) {
        this.valvis = valvis;
    }

    public Integer getBrdni() {
        return brdni;
    }

    public void setBrdni(Integer brdni) {
        this.brdni = brdni;
    }

    public Integer getCel() {
        return cel;
    }

    public void setCel(Integer cel) {
        this.cel = cel;
    }

    public Instant getMolDatVav() {
        return molDatVav;
    }

    public void setMolDatVav(Instant molDatVav) {
        this.molDatVav = molDatVav;
    }

    public String getGratis() {
        return gratis;
    }

    public void setGratis(String gratis) {
        this.gratis = gratis;
    }

    public String getImavisa() {
        return imavisa;
    }

    public void setImavisa(String imavisa) {
        this.imavisa = imavisa;
    }

    public Integer getCenamol() {
        return cenamol;
    }

    public void setCenamol(Integer cenamol) {
        this.cenamol = cenamol;
    }

    public String getCenacurr() {
        return cenacurr;
    }

    public void setCenacurr(String cenacurr) {
        this.cenacurr = cenacurr;
    }

    public String getMaindest() {
        return maindest;
    }

    public void setMaindest(String maindest) {
        this.maindest = maindest;
    }

    public String getMaindestnm() {
        return maindestnm;
    }

    public void setMaindestnm(String maindestnm) {
        this.maindestnm = maindestnm;
    }

    public String getGkppDarj() {
        return gkppDarj;
    }

    public void setGkppDarj(String gkppDarj) {
        this.gkppDarj = gkppDarj;
    }

    public String getGkppText() {
        return gkppText;
    }

    public void setGkppText(String gkppText) {
        this.gkppText = gkppText;
    }

    public String getTextIni() {
        return textIni;
    }

    public void setTextIni(String textIni) {
        this.textIni = textIni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MolbaRowDTO)) {
            return false;
        }

        MolbaRowDTO molbaRowDTO = (MolbaRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, molbaRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MolbaRowDTO{" +
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
