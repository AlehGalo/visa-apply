package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DFingersRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DFingersRowDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fnDatreg;

    @NotNull
    private Instant fnDatvav;

    @NotNull
    @Size(max = 255)
    private String fnUsera;

    private Integer fnSid;

    private Integer fnPnr;

    @NotNull
    @Size(max = 255)
    private String fnVidmol;

    @NotNull
    @Size(max = 255)
    private String fnDrugi;

    private Integer fnDeviceid;

    private Integer fnScanres;

    private Integer fnWidth;

    private Integer fnHeight;

    private Integer fnPixeldepth;

    private Integer fnCompressalgo;

    @NotNull
    @Size(max = 255)
    private String fnFingerposition;

    private Integer fnImagequality;

    @Size(max = 255)
    private String fnImage;

    private Integer fnImglen;

    @Size(max = 255)
    private String fnNottakenreason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFnDatreg() {
        return fnDatreg;
    }

    public void setFnDatreg(LocalDate fnDatreg) {
        this.fnDatreg = fnDatreg;
    }

    public Instant getFnDatvav() {
        return fnDatvav;
    }

    public void setFnDatvav(Instant fnDatvav) {
        this.fnDatvav = fnDatvav;
    }

    public String getFnUsera() {
        return fnUsera;
    }

    public void setFnUsera(String fnUsera) {
        this.fnUsera = fnUsera;
    }

    public Integer getFnSid() {
        return fnSid;
    }

    public void setFnSid(Integer fnSid) {
        this.fnSid = fnSid;
    }

    public Integer getFnPnr() {
        return fnPnr;
    }

    public void setFnPnr(Integer fnPnr) {
        this.fnPnr = fnPnr;
    }

    public String getFnVidmol() {
        return fnVidmol;
    }

    public void setFnVidmol(String fnVidmol) {
        this.fnVidmol = fnVidmol;
    }

    public String getFnDrugi() {
        return fnDrugi;
    }

    public void setFnDrugi(String fnDrugi) {
        this.fnDrugi = fnDrugi;
    }

    public Integer getFnDeviceid() {
        return fnDeviceid;
    }

    public void setFnDeviceid(Integer fnDeviceid) {
        this.fnDeviceid = fnDeviceid;
    }

    public Integer getFnScanres() {
        return fnScanres;
    }

    public void setFnScanres(Integer fnScanres) {
        this.fnScanres = fnScanres;
    }

    public Integer getFnWidth() {
        return fnWidth;
    }

    public void setFnWidth(Integer fnWidth) {
        this.fnWidth = fnWidth;
    }

    public Integer getFnHeight() {
        return fnHeight;
    }

    public void setFnHeight(Integer fnHeight) {
        this.fnHeight = fnHeight;
    }

    public Integer getFnPixeldepth() {
        return fnPixeldepth;
    }

    public void setFnPixeldepth(Integer fnPixeldepth) {
        this.fnPixeldepth = fnPixeldepth;
    }

    public Integer getFnCompressalgo() {
        return fnCompressalgo;
    }

    public void setFnCompressalgo(Integer fnCompressalgo) {
        this.fnCompressalgo = fnCompressalgo;
    }

    public String getFnFingerposition() {
        return fnFingerposition;
    }

    public void setFnFingerposition(String fnFingerposition) {
        this.fnFingerposition = fnFingerposition;
    }

    public Integer getFnImagequality() {
        return fnImagequality;
    }

    public void setFnImagequality(Integer fnImagequality) {
        this.fnImagequality = fnImagequality;
    }

    public String getFnImage() {
        return fnImage;
    }

    public void setFnImage(String fnImage) {
        this.fnImage = fnImage;
    }

    public Integer getFnImglen() {
        return fnImglen;
    }

    public void setFnImglen(Integer fnImglen) {
        this.fnImglen = fnImglen;
    }

    public String getFnNottakenreason() {
        return fnNottakenreason;
    }

    public void setFnNottakenreason(String fnNottakenreason) {
        this.fnNottakenreason = fnNottakenreason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DFingersRowDTO)) {
            return false;
        }

        DFingersRowDTO dFingersRowDTO = (DFingersRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dFingersRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DFingersRowDTO{" +
            "id=" + getId() +
            ", fnDatreg='" + getFnDatreg() + "'" +
            ", fnDatvav='" + getFnDatvav() + "'" +
            ", fnUsera='" + getFnUsera() + "'" +
            ", fnSid=" + getFnSid() +
            ", fnPnr=" + getFnPnr() +
            ", fnVidmol='" + getFnVidmol() + "'" +
            ", fnDrugi='" + getFnDrugi() + "'" +
            ", fnDeviceid=" + getFnDeviceid() +
            ", fnScanres=" + getFnScanres() +
            ", fnWidth=" + getFnWidth() +
            ", fnHeight=" + getFnHeight() +
            ", fnPixeldepth=" + getFnPixeldepth() +
            ", fnCompressalgo=" + getFnCompressalgo() +
            ", fnFingerposition='" + getFnFingerposition() + "'" +
            ", fnImagequality=" + getFnImagequality() +
            ", fnImage='" + getFnImage() + "'" +
            ", fnImglen=" + getFnImglen() +
            ", fnNottakenreason='" + getFnNottakenreason() + "'" +
            "}";
    }
}
