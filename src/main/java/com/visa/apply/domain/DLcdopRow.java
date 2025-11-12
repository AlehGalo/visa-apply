package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DLcdopRow.
 */
@Entity
@Table(name = "d_lcdop_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcdopRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_mrjdarj", length = 255, nullable = false)
    private String ldMrjdarj;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_mrjnm", length = 255, nullable = false)
    private String ldMrjnm;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_mrjgraj", length = 255, nullable = false)
    private String ldMrjgraj;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_zenen", length = 255, nullable = false)
    private String ldZenen;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_jit_darj", length = 255, nullable = false)
    private String ldJitDarj;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_jit_nm", length = 255, nullable = false)
    private String ldJitNm;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_jit_ul", length = 255, nullable = false)
    private String ldJitUl;

    @Column(name = "ld_tel")
    private Long ldTel;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_rabota", length = 255, nullable = false)
    private String ldRabota;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_profkod", length = 255, nullable = false)
    private String ldProfkod;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_profesia", length = 255, nullable = false)
    private String ldProfesia;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_sl_darj", length = 255, nullable = false)
    private String ldSlDarj;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_sl_nm", length = 255, nullable = false)
    private String ldSlNm;

    @NotNull
    @Size(max = 255)
    @Column(name = "ld_sl_ul", length = 255, nullable = false)
    private String ldSlUl;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DLcdopRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLdMrjdarj() {
        return this.ldMrjdarj;
    }

    public DLcdopRow ldMrjdarj(String ldMrjdarj) {
        this.setLdMrjdarj(ldMrjdarj);
        return this;
    }

    public void setLdMrjdarj(String ldMrjdarj) {
        this.ldMrjdarj = ldMrjdarj;
    }

    public String getLdMrjnm() {
        return this.ldMrjnm;
    }

    public DLcdopRow ldMrjnm(String ldMrjnm) {
        this.setLdMrjnm(ldMrjnm);
        return this;
    }

    public void setLdMrjnm(String ldMrjnm) {
        this.ldMrjnm = ldMrjnm;
    }

    public String getLdMrjgraj() {
        return this.ldMrjgraj;
    }

    public DLcdopRow ldMrjgraj(String ldMrjgraj) {
        this.setLdMrjgraj(ldMrjgraj);
        return this;
    }

    public void setLdMrjgraj(String ldMrjgraj) {
        this.ldMrjgraj = ldMrjgraj;
    }

    public String getLdZenen() {
        return this.ldZenen;
    }

    public DLcdopRow ldZenen(String ldZenen) {
        this.setLdZenen(ldZenen);
        return this;
    }

    public void setLdZenen(String ldZenen) {
        this.ldZenen = ldZenen;
    }

    public String getLdJitDarj() {
        return this.ldJitDarj;
    }

    public DLcdopRow ldJitDarj(String ldJitDarj) {
        this.setLdJitDarj(ldJitDarj);
        return this;
    }

    public void setLdJitDarj(String ldJitDarj) {
        this.ldJitDarj = ldJitDarj;
    }

    public String getLdJitNm() {
        return this.ldJitNm;
    }

    public DLcdopRow ldJitNm(String ldJitNm) {
        this.setLdJitNm(ldJitNm);
        return this;
    }

    public void setLdJitNm(String ldJitNm) {
        this.ldJitNm = ldJitNm;
    }

    public String getLdJitUl() {
        return this.ldJitUl;
    }

    public DLcdopRow ldJitUl(String ldJitUl) {
        this.setLdJitUl(ldJitUl);
        return this;
    }

    public void setLdJitUl(String ldJitUl) {
        this.ldJitUl = ldJitUl;
    }

    public Long getLdTel() {
        return this.ldTel;
    }

    public DLcdopRow ldTel(Long ldTel) {
        this.setLdTel(ldTel);
        return this;
    }

    public void setLdTel(Long ldTel) {
        this.ldTel = ldTel;
    }

    public String getLdRabota() {
        return this.ldRabota;
    }

    public DLcdopRow ldRabota(String ldRabota) {
        this.setLdRabota(ldRabota);
        return this;
    }

    public void setLdRabota(String ldRabota) {
        this.ldRabota = ldRabota;
    }

    public String getLdProfkod() {
        return this.ldProfkod;
    }

    public DLcdopRow ldProfkod(String ldProfkod) {
        this.setLdProfkod(ldProfkod);
        return this;
    }

    public void setLdProfkod(String ldProfkod) {
        this.ldProfkod = ldProfkod;
    }

    public String getLdProfesia() {
        return this.ldProfesia;
    }

    public DLcdopRow ldProfesia(String ldProfesia) {
        this.setLdProfesia(ldProfesia);
        return this;
    }

    public void setLdProfesia(String ldProfesia) {
        this.ldProfesia = ldProfesia;
    }

    public String getLdSlDarj() {
        return this.ldSlDarj;
    }

    public DLcdopRow ldSlDarj(String ldSlDarj) {
        this.setLdSlDarj(ldSlDarj);
        return this;
    }

    public void setLdSlDarj(String ldSlDarj) {
        this.ldSlDarj = ldSlDarj;
    }

    public String getLdSlNm() {
        return this.ldSlNm;
    }

    public DLcdopRow ldSlNm(String ldSlNm) {
        this.setLdSlNm(ldSlNm);
        return this;
    }

    public void setLdSlNm(String ldSlNm) {
        this.ldSlNm = ldSlNm;
    }

    public String getLdSlUl() {
        return this.ldSlUl;
    }

    public DLcdopRow ldSlUl(String ldSlUl) {
        this.setLdSlUl(ldSlUl);
        return this;
    }

    public void setLdSlUl(String ldSlUl) {
        this.ldSlUl = ldSlUl;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLcdopRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DLcdopRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcdopRow{" +
            "id=" + getId() +
            ", ldMrjdarj='" + getLdMrjdarj() + "'" +
            ", ldMrjnm='" + getLdMrjnm() + "'" +
            ", ldMrjgraj='" + getLdMrjgraj() + "'" +
            ", ldZenen='" + getLdZenen() + "'" +
            ", ldJitDarj='" + getLdJitDarj() + "'" +
            ", ldJitNm='" + getLdJitNm() + "'" +
            ", ldJitUl='" + getLdJitUl() + "'" +
            ", ldTel=" + getLdTel() +
            ", ldRabota='" + getLdRabota() + "'" +
            ", ldProfkod='" + getLdProfkod() + "'" +
            ", ldProfesia='" + getLdProfesia() + "'" +
            ", ldSlDarj='" + getLdSlDarj() + "'" +
            ", ldSlNm='" + getLdSlNm() + "'" +
            ", ldSlUl='" + getLdSlUl() + "'" +
            "}";
    }
}
