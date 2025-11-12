package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DDomakinRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DDomakinRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String dmVid;

    @NotNull
    @Size(max = 255)
    private String nomPok;

    @NotNull
    @Size(max = 255)
    private String domGraj;

    @NotNull
    @Size(max = 255)
    private String domFamil;

    @NotNull
    @Size(max = 255)
    private String domIme;

    @NotNull
    @Size(max = 255)
    private String domDarj;

    private Integer domNm;

    @NotNull
    @Size(max = 255)
    private String domAdres;

    @NotNull
    @Size(max = 255)
    private String vedDarj;

    @NotNull
    @Size(max = 255)
    private String vedNm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDmVid() {
        return dmVid;
    }

    public void setDmVid(String dmVid) {
        this.dmVid = dmVid;
    }

    public String getNomPok() {
        return nomPok;
    }

    public void setNomPok(String nomPok) {
        this.nomPok = nomPok;
    }

    public String getDomGraj() {
        return domGraj;
    }

    public void setDomGraj(String domGraj) {
        this.domGraj = domGraj;
    }

    public String getDomFamil() {
        return domFamil;
    }

    public void setDomFamil(String domFamil) {
        this.domFamil = domFamil;
    }

    public String getDomIme() {
        return domIme;
    }

    public void setDomIme(String domIme) {
        this.domIme = domIme;
    }

    public String getDomDarj() {
        return domDarj;
    }

    public void setDomDarj(String domDarj) {
        this.domDarj = domDarj;
    }

    public Integer getDomNm() {
        return domNm;
    }

    public void setDomNm(Integer domNm) {
        this.domNm = domNm;
    }

    public String getDomAdres() {
        return domAdres;
    }

    public void setDomAdres(String domAdres) {
        this.domAdres = domAdres;
    }

    public String getVedDarj() {
        return vedDarj;
    }

    public void setVedDarj(String vedDarj) {
        this.vedDarj = vedDarj;
    }

    public String getVedNm() {
        return vedNm;
    }

    public void setVedNm(String vedNm) {
        this.vedNm = vedNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DDomakinRowDTO)) {
            return false;
        }

        DDomakinRowDTO dDomakinRowDTO = (DDomakinRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dDomakinRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DDomakinRowDTO{" +
            "id=" + getId() +
            ", dmVid='" + getDmVid() + "'" +
            ", nomPok='" + getNomPok() + "'" +
            ", domGraj='" + getDomGraj() + "'" +
            ", domFamil='" + getDomFamil() + "'" +
            ", domIme='" + getDomIme() + "'" +
            ", domDarj='" + getDomDarj() + "'" +
            ", domNm=" + getDomNm() +
            ", domAdres='" + getDomAdres() + "'" +
            ", vedDarj='" + getVedDarj() + "'" +
            ", vedNm='" + getVedNm() + "'" +
            "}";
    }
}
