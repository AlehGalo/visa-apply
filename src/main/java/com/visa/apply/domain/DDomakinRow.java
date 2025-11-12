package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DDomakinRow.
 */
@Entity
@Table(name = "d_domakin_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DDomakinRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "dm_vid", length = 255, nullable = false)
    private String dmVid;

    @NotNull
    @Size(max = 255)
    @Column(name = "nom_pok", length = 255, nullable = false)
    private String nomPok;

    @NotNull
    @Size(max = 255)
    @Column(name = "dom_graj", length = 255, nullable = false)
    private String domGraj;

    @NotNull
    @Size(max = 255)
    @Column(name = "dom_famil", length = 255, nullable = false)
    private String domFamil;

    @NotNull
    @Size(max = 255)
    @Column(name = "dom_ime", length = 255, nullable = false)
    private String domIme;

    @NotNull
    @Size(max = 255)
    @Column(name = "dom_darj", length = 255, nullable = false)
    private String domDarj;

    @Column(name = "dom_nm")
    private Integer domNm;

    @NotNull
    @Size(max = 255)
    @Column(name = "dom_adres", length = 255, nullable = false)
    private String domAdres;

    @NotNull
    @Size(max = 255)
    @Column(name = "ved_darj", length = 255, nullable = false)
    private String vedDarj;

    @NotNull
    @Size(max = 255)
    @Column(name = "ved_nm", length = 255, nullable = false)
    private String vedNm;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DDomakinRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDmVid() {
        return this.dmVid;
    }

    public DDomakinRow dmVid(String dmVid) {
        this.setDmVid(dmVid);
        return this;
    }

    public void setDmVid(String dmVid) {
        this.dmVid = dmVid;
    }

    public String getNomPok() {
        return this.nomPok;
    }

    public DDomakinRow nomPok(String nomPok) {
        this.setNomPok(nomPok);
        return this;
    }

    public void setNomPok(String nomPok) {
        this.nomPok = nomPok;
    }

    public String getDomGraj() {
        return this.domGraj;
    }

    public DDomakinRow domGraj(String domGraj) {
        this.setDomGraj(domGraj);
        return this;
    }

    public void setDomGraj(String domGraj) {
        this.domGraj = domGraj;
    }

    public String getDomFamil() {
        return this.domFamil;
    }

    public DDomakinRow domFamil(String domFamil) {
        this.setDomFamil(domFamil);
        return this;
    }

    public void setDomFamil(String domFamil) {
        this.domFamil = domFamil;
    }

    public String getDomIme() {
        return this.domIme;
    }

    public DDomakinRow domIme(String domIme) {
        this.setDomIme(domIme);
        return this;
    }

    public void setDomIme(String domIme) {
        this.domIme = domIme;
    }

    public String getDomDarj() {
        return this.domDarj;
    }

    public DDomakinRow domDarj(String domDarj) {
        this.setDomDarj(domDarj);
        return this;
    }

    public void setDomDarj(String domDarj) {
        this.domDarj = domDarj;
    }

    public Integer getDomNm() {
        return this.domNm;
    }

    public DDomakinRow domNm(Integer domNm) {
        this.setDomNm(domNm);
        return this;
    }

    public void setDomNm(Integer domNm) {
        this.domNm = domNm;
    }

    public String getDomAdres() {
        return this.domAdres;
    }

    public DDomakinRow domAdres(String domAdres) {
        this.setDomAdres(domAdres);
        return this;
    }

    public void setDomAdres(String domAdres) {
        this.domAdres = domAdres;
    }

    public String getVedDarj() {
        return this.vedDarj;
    }

    public DDomakinRow vedDarj(String vedDarj) {
        this.setVedDarj(vedDarj);
        return this;
    }

    public void setVedDarj(String vedDarj) {
        this.vedDarj = vedDarj;
    }

    public String getVedNm() {
        return this.vedNm;
    }

    public DDomakinRow vedNm(String vedNm) {
        this.setVedNm(vedNm);
        return this;
    }

    public void setVedNm(String vedNm) {
        this.vedNm = vedNm;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DDomakinRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DDomakinRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DDomakinRow{" +
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
