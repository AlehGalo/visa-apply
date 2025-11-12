package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A DFingersRow.
 */
@Entity
@Table(name = "d_fingers_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DFingersRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "fn_datreg", nullable = false)
    private LocalDate fnDatreg;

    @NotNull
    @Column(name = "fn_datvav", nullable = false)
    private Instant fnDatvav;

    @NotNull
    @Size(max = 255)
    @Column(name = "fn_usera", length = 255, nullable = false)
    private String fnUsera;

    @Column(name = "fn_sid")
    private Integer fnSid;

    @Column(name = "fn_pnr")
    private Integer fnPnr;

    @NotNull
    @Size(max = 255)
    @Column(name = "fn_vidmol", length = 255, nullable = false)
    private String fnVidmol;

    @NotNull
    @Size(max = 255)
    @Column(name = "fn_drugi", length = 255, nullable = false)
    private String fnDrugi;

    @Column(name = "fn_deviceid")
    private Integer fnDeviceid;

    @Column(name = "fn_scanres")
    private Integer fnScanres;

    @Column(name = "fn_width")
    private Integer fnWidth;

    @Column(name = "fn_height")
    private Integer fnHeight;

    @Column(name = "fn_pixeldepth")
    private Integer fnPixeldepth;

    @Column(name = "fn_compressalgo")
    private Integer fnCompressalgo;

    @NotNull
    @Size(max = 255)
    @Column(name = "fn_fingerposition", length = 255, nullable = false)
    private String fnFingerposition;

    @Column(name = "fn_imagequality")
    private Integer fnImagequality;

    @Size(max = 255)
    @Column(name = "fn_image", length = 255)
    private String fnImage;

    @Column(name = "fn_imglen")
    private Integer fnImglen;

    @Size(max = 255)
    @Column(name = "fn_nottakenreason", length = 255)
    private String fnNottakenreason;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DFingersRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFnDatreg() {
        return this.fnDatreg;
    }

    public DFingersRow fnDatreg(LocalDate fnDatreg) {
        this.setFnDatreg(fnDatreg);
        return this;
    }

    public void setFnDatreg(LocalDate fnDatreg) {
        this.fnDatreg = fnDatreg;
    }

    public Instant getFnDatvav() {
        return this.fnDatvav;
    }

    public DFingersRow fnDatvav(Instant fnDatvav) {
        this.setFnDatvav(fnDatvav);
        return this;
    }

    public void setFnDatvav(Instant fnDatvav) {
        this.fnDatvav = fnDatvav;
    }

    public String getFnUsera() {
        return this.fnUsera;
    }

    public DFingersRow fnUsera(String fnUsera) {
        this.setFnUsera(fnUsera);
        return this;
    }

    public void setFnUsera(String fnUsera) {
        this.fnUsera = fnUsera;
    }

    public Integer getFnSid() {
        return this.fnSid;
    }

    public DFingersRow fnSid(Integer fnSid) {
        this.setFnSid(fnSid);
        return this;
    }

    public void setFnSid(Integer fnSid) {
        this.fnSid = fnSid;
    }

    public Integer getFnPnr() {
        return this.fnPnr;
    }

    public DFingersRow fnPnr(Integer fnPnr) {
        this.setFnPnr(fnPnr);
        return this;
    }

    public void setFnPnr(Integer fnPnr) {
        this.fnPnr = fnPnr;
    }

    public String getFnVidmol() {
        return this.fnVidmol;
    }

    public DFingersRow fnVidmol(String fnVidmol) {
        this.setFnVidmol(fnVidmol);
        return this;
    }

    public void setFnVidmol(String fnVidmol) {
        this.fnVidmol = fnVidmol;
    }

    public String getFnDrugi() {
        return this.fnDrugi;
    }

    public DFingersRow fnDrugi(String fnDrugi) {
        this.setFnDrugi(fnDrugi);
        return this;
    }

    public void setFnDrugi(String fnDrugi) {
        this.fnDrugi = fnDrugi;
    }

    public Integer getFnDeviceid() {
        return this.fnDeviceid;
    }

    public DFingersRow fnDeviceid(Integer fnDeviceid) {
        this.setFnDeviceid(fnDeviceid);
        return this;
    }

    public void setFnDeviceid(Integer fnDeviceid) {
        this.fnDeviceid = fnDeviceid;
    }

    public Integer getFnScanres() {
        return this.fnScanres;
    }

    public DFingersRow fnScanres(Integer fnScanres) {
        this.setFnScanres(fnScanres);
        return this;
    }

    public void setFnScanres(Integer fnScanres) {
        this.fnScanres = fnScanres;
    }

    public Integer getFnWidth() {
        return this.fnWidth;
    }

    public DFingersRow fnWidth(Integer fnWidth) {
        this.setFnWidth(fnWidth);
        return this;
    }

    public void setFnWidth(Integer fnWidth) {
        this.fnWidth = fnWidth;
    }

    public Integer getFnHeight() {
        return this.fnHeight;
    }

    public DFingersRow fnHeight(Integer fnHeight) {
        this.setFnHeight(fnHeight);
        return this;
    }

    public void setFnHeight(Integer fnHeight) {
        this.fnHeight = fnHeight;
    }

    public Integer getFnPixeldepth() {
        return this.fnPixeldepth;
    }

    public DFingersRow fnPixeldepth(Integer fnPixeldepth) {
        this.setFnPixeldepth(fnPixeldepth);
        return this;
    }

    public void setFnPixeldepth(Integer fnPixeldepth) {
        this.fnPixeldepth = fnPixeldepth;
    }

    public Integer getFnCompressalgo() {
        return this.fnCompressalgo;
    }

    public DFingersRow fnCompressalgo(Integer fnCompressalgo) {
        this.setFnCompressalgo(fnCompressalgo);
        return this;
    }

    public void setFnCompressalgo(Integer fnCompressalgo) {
        this.fnCompressalgo = fnCompressalgo;
    }

    public String getFnFingerposition() {
        return this.fnFingerposition;
    }

    public DFingersRow fnFingerposition(String fnFingerposition) {
        this.setFnFingerposition(fnFingerposition);
        return this;
    }

    public void setFnFingerposition(String fnFingerposition) {
        this.fnFingerposition = fnFingerposition;
    }

    public Integer getFnImagequality() {
        return this.fnImagequality;
    }

    public DFingersRow fnImagequality(Integer fnImagequality) {
        this.setFnImagequality(fnImagequality);
        return this;
    }

    public void setFnImagequality(Integer fnImagequality) {
        this.fnImagequality = fnImagequality;
    }

    public String getFnImage() {
        return this.fnImage;
    }

    public DFingersRow fnImage(String fnImage) {
        this.setFnImage(fnImage);
        return this;
    }

    public void setFnImage(String fnImage) {
        this.fnImage = fnImage;
    }

    public Integer getFnImglen() {
        return this.fnImglen;
    }

    public DFingersRow fnImglen(Integer fnImglen) {
        this.setFnImglen(fnImglen);
        return this;
    }

    public void setFnImglen(Integer fnImglen) {
        this.fnImglen = fnImglen;
    }

    public String getFnNottakenreason() {
        return this.fnNottakenreason;
    }

    public DFingersRow fnNottakenreason(String fnNottakenreason) {
        this.setFnNottakenreason(fnNottakenreason);
        return this;
    }

    public void setFnNottakenreason(String fnNottakenreason) {
        this.fnNottakenreason = fnNottakenreason;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DFingersRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DFingersRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DFingersRow{" +
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
