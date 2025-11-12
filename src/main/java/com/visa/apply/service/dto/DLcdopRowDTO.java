package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DLcdopRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcdopRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String ldMrjdarj;

    @NotNull
    @Size(max = 255)
    private String ldMrjnm;

    @NotNull
    @Size(max = 255)
    private String ldMrjgraj;

    @NotNull
    @Size(max = 255)
    private String ldZenen;

    @NotNull
    @Size(max = 255)
    private String ldJitDarj;

    @NotNull
    @Size(max = 255)
    private String ldJitNm;

    @NotNull
    @Size(max = 255)
    private String ldJitUl;

    private Long ldTel;

    @NotNull
    @Size(max = 255)
    private String ldRabota;

    @NotNull
    @Size(max = 255)
    private String ldProfkod;

    @NotNull
    @Size(max = 255)
    private String ldProfesia;

    @NotNull
    @Size(max = 255)
    private String ldSlDarj;

    @NotNull
    @Size(max = 255)
    private String ldSlNm;

    @NotNull
    @Size(max = 255)
    private String ldSlUl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLdMrjdarj() {
        return ldMrjdarj;
    }

    public void setLdMrjdarj(String ldMrjdarj) {
        this.ldMrjdarj = ldMrjdarj;
    }

    public String getLdMrjnm() {
        return ldMrjnm;
    }

    public void setLdMrjnm(String ldMrjnm) {
        this.ldMrjnm = ldMrjnm;
    }

    public String getLdMrjgraj() {
        return ldMrjgraj;
    }

    public void setLdMrjgraj(String ldMrjgraj) {
        this.ldMrjgraj = ldMrjgraj;
    }

    public String getLdZenen() {
        return ldZenen;
    }

    public void setLdZenen(String ldZenen) {
        this.ldZenen = ldZenen;
    }

    public String getLdJitDarj() {
        return ldJitDarj;
    }

    public void setLdJitDarj(String ldJitDarj) {
        this.ldJitDarj = ldJitDarj;
    }

    public String getLdJitNm() {
        return ldJitNm;
    }

    public void setLdJitNm(String ldJitNm) {
        this.ldJitNm = ldJitNm;
    }

    public String getLdJitUl() {
        return ldJitUl;
    }

    public void setLdJitUl(String ldJitUl) {
        this.ldJitUl = ldJitUl;
    }

    public Long getLdTel() {
        return ldTel;
    }

    public void setLdTel(Long ldTel) {
        this.ldTel = ldTel;
    }

    public String getLdRabota() {
        return ldRabota;
    }

    public void setLdRabota(String ldRabota) {
        this.ldRabota = ldRabota;
    }

    public String getLdProfkod() {
        return ldProfkod;
    }

    public void setLdProfkod(String ldProfkod) {
        this.ldProfkod = ldProfkod;
    }

    public String getLdProfesia() {
        return ldProfesia;
    }

    public void setLdProfesia(String ldProfesia) {
        this.ldProfesia = ldProfesia;
    }

    public String getLdSlDarj() {
        return ldSlDarj;
    }

    public void setLdSlDarj(String ldSlDarj) {
        this.ldSlDarj = ldSlDarj;
    }

    public String getLdSlNm() {
        return ldSlNm;
    }

    public void setLdSlNm(String ldSlNm) {
        this.ldSlNm = ldSlNm;
    }

    public String getLdSlUl() {
        return ldSlUl;
    }

    public void setLdSlUl(String ldSlUl) {
        this.ldSlUl = ldSlUl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLcdopRowDTO)) {
            return false;
        }

        DLcdopRowDTO dLcdopRowDTO = (DLcdopRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dLcdopRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcdopRowDTO{" +
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
