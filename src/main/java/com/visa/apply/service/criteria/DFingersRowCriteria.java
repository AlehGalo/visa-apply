package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DFingersRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DFingersRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-fingers-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DFingersRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fnDatreg;

    private InstantFilter fnDatvav;

    private StringFilter fnUsera;

    private IntegerFilter fnSid;

    private IntegerFilter fnPnr;

    private StringFilter fnVidmol;

    private StringFilter fnDrugi;

    private IntegerFilter fnDeviceid;

    private IntegerFilter fnScanres;

    private IntegerFilter fnWidth;

    private IntegerFilter fnHeight;

    private IntegerFilter fnPixeldepth;

    private IntegerFilter fnCompressalgo;

    private StringFilter fnFingerposition;

    private IntegerFilter fnImagequality;

    private StringFilter fnImage;

    private IntegerFilter fnImglen;

    private StringFilter fnNottakenreason;

    private Boolean distinct;

    public DFingersRowCriteria() {}

    public DFingersRowCriteria(DFingersRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.fnDatreg = other.optionalFnDatreg().map(LocalDateFilter::copy).orElse(null);
        this.fnDatvav = other.optionalFnDatvav().map(InstantFilter::copy).orElse(null);
        this.fnUsera = other.optionalFnUsera().map(StringFilter::copy).orElse(null);
        this.fnSid = other.optionalFnSid().map(IntegerFilter::copy).orElse(null);
        this.fnPnr = other.optionalFnPnr().map(IntegerFilter::copy).orElse(null);
        this.fnVidmol = other.optionalFnVidmol().map(StringFilter::copy).orElse(null);
        this.fnDrugi = other.optionalFnDrugi().map(StringFilter::copy).orElse(null);
        this.fnDeviceid = other.optionalFnDeviceid().map(IntegerFilter::copy).orElse(null);
        this.fnScanres = other.optionalFnScanres().map(IntegerFilter::copy).orElse(null);
        this.fnWidth = other.optionalFnWidth().map(IntegerFilter::copy).orElse(null);
        this.fnHeight = other.optionalFnHeight().map(IntegerFilter::copy).orElse(null);
        this.fnPixeldepth = other.optionalFnPixeldepth().map(IntegerFilter::copy).orElse(null);
        this.fnCompressalgo = other.optionalFnCompressalgo().map(IntegerFilter::copy).orElse(null);
        this.fnFingerposition = other.optionalFnFingerposition().map(StringFilter::copy).orElse(null);
        this.fnImagequality = other.optionalFnImagequality().map(IntegerFilter::copy).orElse(null);
        this.fnImage = other.optionalFnImage().map(StringFilter::copy).orElse(null);
        this.fnImglen = other.optionalFnImglen().map(IntegerFilter::copy).orElse(null);
        this.fnNottakenreason = other.optionalFnNottakenreason().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DFingersRowCriteria copy() {
        return new DFingersRowCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFnDatreg() {
        return fnDatreg;
    }

    public Optional<LocalDateFilter> optionalFnDatreg() {
        return Optional.ofNullable(fnDatreg);
    }

    public LocalDateFilter fnDatreg() {
        if (fnDatreg == null) {
            setFnDatreg(new LocalDateFilter());
        }
        return fnDatreg;
    }

    public void setFnDatreg(LocalDateFilter fnDatreg) {
        this.fnDatreg = fnDatreg;
    }

    public InstantFilter getFnDatvav() {
        return fnDatvav;
    }

    public Optional<InstantFilter> optionalFnDatvav() {
        return Optional.ofNullable(fnDatvav);
    }

    public InstantFilter fnDatvav() {
        if (fnDatvav == null) {
            setFnDatvav(new InstantFilter());
        }
        return fnDatvav;
    }

    public void setFnDatvav(InstantFilter fnDatvav) {
        this.fnDatvav = fnDatvav;
    }

    public StringFilter getFnUsera() {
        return fnUsera;
    }

    public Optional<StringFilter> optionalFnUsera() {
        return Optional.ofNullable(fnUsera);
    }

    public StringFilter fnUsera() {
        if (fnUsera == null) {
            setFnUsera(new StringFilter());
        }
        return fnUsera;
    }

    public void setFnUsera(StringFilter fnUsera) {
        this.fnUsera = fnUsera;
    }

    public IntegerFilter getFnSid() {
        return fnSid;
    }

    public Optional<IntegerFilter> optionalFnSid() {
        return Optional.ofNullable(fnSid);
    }

    public IntegerFilter fnSid() {
        if (fnSid == null) {
            setFnSid(new IntegerFilter());
        }
        return fnSid;
    }

    public void setFnSid(IntegerFilter fnSid) {
        this.fnSid = fnSid;
    }

    public IntegerFilter getFnPnr() {
        return fnPnr;
    }

    public Optional<IntegerFilter> optionalFnPnr() {
        return Optional.ofNullable(fnPnr);
    }

    public IntegerFilter fnPnr() {
        if (fnPnr == null) {
            setFnPnr(new IntegerFilter());
        }
        return fnPnr;
    }

    public void setFnPnr(IntegerFilter fnPnr) {
        this.fnPnr = fnPnr;
    }

    public StringFilter getFnVidmol() {
        return fnVidmol;
    }

    public Optional<StringFilter> optionalFnVidmol() {
        return Optional.ofNullable(fnVidmol);
    }

    public StringFilter fnVidmol() {
        if (fnVidmol == null) {
            setFnVidmol(new StringFilter());
        }
        return fnVidmol;
    }

    public void setFnVidmol(StringFilter fnVidmol) {
        this.fnVidmol = fnVidmol;
    }

    public StringFilter getFnDrugi() {
        return fnDrugi;
    }

    public Optional<StringFilter> optionalFnDrugi() {
        return Optional.ofNullable(fnDrugi);
    }

    public StringFilter fnDrugi() {
        if (fnDrugi == null) {
            setFnDrugi(new StringFilter());
        }
        return fnDrugi;
    }

    public void setFnDrugi(StringFilter fnDrugi) {
        this.fnDrugi = fnDrugi;
    }

    public IntegerFilter getFnDeviceid() {
        return fnDeviceid;
    }

    public Optional<IntegerFilter> optionalFnDeviceid() {
        return Optional.ofNullable(fnDeviceid);
    }

    public IntegerFilter fnDeviceid() {
        if (fnDeviceid == null) {
            setFnDeviceid(new IntegerFilter());
        }
        return fnDeviceid;
    }

    public void setFnDeviceid(IntegerFilter fnDeviceid) {
        this.fnDeviceid = fnDeviceid;
    }

    public IntegerFilter getFnScanres() {
        return fnScanres;
    }

    public Optional<IntegerFilter> optionalFnScanres() {
        return Optional.ofNullable(fnScanres);
    }

    public IntegerFilter fnScanres() {
        if (fnScanres == null) {
            setFnScanres(new IntegerFilter());
        }
        return fnScanres;
    }

    public void setFnScanres(IntegerFilter fnScanres) {
        this.fnScanres = fnScanres;
    }

    public IntegerFilter getFnWidth() {
        return fnWidth;
    }

    public Optional<IntegerFilter> optionalFnWidth() {
        return Optional.ofNullable(fnWidth);
    }

    public IntegerFilter fnWidth() {
        if (fnWidth == null) {
            setFnWidth(new IntegerFilter());
        }
        return fnWidth;
    }

    public void setFnWidth(IntegerFilter fnWidth) {
        this.fnWidth = fnWidth;
    }

    public IntegerFilter getFnHeight() {
        return fnHeight;
    }

    public Optional<IntegerFilter> optionalFnHeight() {
        return Optional.ofNullable(fnHeight);
    }

    public IntegerFilter fnHeight() {
        if (fnHeight == null) {
            setFnHeight(new IntegerFilter());
        }
        return fnHeight;
    }

    public void setFnHeight(IntegerFilter fnHeight) {
        this.fnHeight = fnHeight;
    }

    public IntegerFilter getFnPixeldepth() {
        return fnPixeldepth;
    }

    public Optional<IntegerFilter> optionalFnPixeldepth() {
        return Optional.ofNullable(fnPixeldepth);
    }

    public IntegerFilter fnPixeldepth() {
        if (fnPixeldepth == null) {
            setFnPixeldepth(new IntegerFilter());
        }
        return fnPixeldepth;
    }

    public void setFnPixeldepth(IntegerFilter fnPixeldepth) {
        this.fnPixeldepth = fnPixeldepth;
    }

    public IntegerFilter getFnCompressalgo() {
        return fnCompressalgo;
    }

    public Optional<IntegerFilter> optionalFnCompressalgo() {
        return Optional.ofNullable(fnCompressalgo);
    }

    public IntegerFilter fnCompressalgo() {
        if (fnCompressalgo == null) {
            setFnCompressalgo(new IntegerFilter());
        }
        return fnCompressalgo;
    }

    public void setFnCompressalgo(IntegerFilter fnCompressalgo) {
        this.fnCompressalgo = fnCompressalgo;
    }

    public StringFilter getFnFingerposition() {
        return fnFingerposition;
    }

    public Optional<StringFilter> optionalFnFingerposition() {
        return Optional.ofNullable(fnFingerposition);
    }

    public StringFilter fnFingerposition() {
        if (fnFingerposition == null) {
            setFnFingerposition(new StringFilter());
        }
        return fnFingerposition;
    }

    public void setFnFingerposition(StringFilter fnFingerposition) {
        this.fnFingerposition = fnFingerposition;
    }

    public IntegerFilter getFnImagequality() {
        return fnImagequality;
    }

    public Optional<IntegerFilter> optionalFnImagequality() {
        return Optional.ofNullable(fnImagequality);
    }

    public IntegerFilter fnImagequality() {
        if (fnImagequality == null) {
            setFnImagequality(new IntegerFilter());
        }
        return fnImagequality;
    }

    public void setFnImagequality(IntegerFilter fnImagequality) {
        this.fnImagequality = fnImagequality;
    }

    public StringFilter getFnImage() {
        return fnImage;
    }

    public Optional<StringFilter> optionalFnImage() {
        return Optional.ofNullable(fnImage);
    }

    public StringFilter fnImage() {
        if (fnImage == null) {
            setFnImage(new StringFilter());
        }
        return fnImage;
    }

    public void setFnImage(StringFilter fnImage) {
        this.fnImage = fnImage;
    }

    public IntegerFilter getFnImglen() {
        return fnImglen;
    }

    public Optional<IntegerFilter> optionalFnImglen() {
        return Optional.ofNullable(fnImglen);
    }

    public IntegerFilter fnImglen() {
        if (fnImglen == null) {
            setFnImglen(new IntegerFilter());
        }
        return fnImglen;
    }

    public void setFnImglen(IntegerFilter fnImglen) {
        this.fnImglen = fnImglen;
    }

    public StringFilter getFnNottakenreason() {
        return fnNottakenreason;
    }

    public Optional<StringFilter> optionalFnNottakenreason() {
        return Optional.ofNullable(fnNottakenreason);
    }

    public StringFilter fnNottakenreason() {
        if (fnNottakenreason == null) {
            setFnNottakenreason(new StringFilter());
        }
        return fnNottakenreason;
    }

    public void setFnNottakenreason(StringFilter fnNottakenreason) {
        this.fnNottakenreason = fnNottakenreason;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DFingersRowCriteria that = (DFingersRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fnDatreg, that.fnDatreg) &&
            Objects.equals(fnDatvav, that.fnDatvav) &&
            Objects.equals(fnUsera, that.fnUsera) &&
            Objects.equals(fnSid, that.fnSid) &&
            Objects.equals(fnPnr, that.fnPnr) &&
            Objects.equals(fnVidmol, that.fnVidmol) &&
            Objects.equals(fnDrugi, that.fnDrugi) &&
            Objects.equals(fnDeviceid, that.fnDeviceid) &&
            Objects.equals(fnScanres, that.fnScanres) &&
            Objects.equals(fnWidth, that.fnWidth) &&
            Objects.equals(fnHeight, that.fnHeight) &&
            Objects.equals(fnPixeldepth, that.fnPixeldepth) &&
            Objects.equals(fnCompressalgo, that.fnCompressalgo) &&
            Objects.equals(fnFingerposition, that.fnFingerposition) &&
            Objects.equals(fnImagequality, that.fnImagequality) &&
            Objects.equals(fnImage, that.fnImage) &&
            Objects.equals(fnImglen, that.fnImglen) &&
            Objects.equals(fnNottakenreason, that.fnNottakenreason) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fnDatreg,
            fnDatvav,
            fnUsera,
            fnSid,
            fnPnr,
            fnVidmol,
            fnDrugi,
            fnDeviceid,
            fnScanres,
            fnWidth,
            fnHeight,
            fnPixeldepth,
            fnCompressalgo,
            fnFingerposition,
            fnImagequality,
            fnImage,
            fnImglen,
            fnNottakenreason,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DFingersRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalFnDatreg().map(f -> "fnDatreg=" + f + ", ").orElse("") +
            optionalFnDatvav().map(f -> "fnDatvav=" + f + ", ").orElse("") +
            optionalFnUsera().map(f -> "fnUsera=" + f + ", ").orElse("") +
            optionalFnSid().map(f -> "fnSid=" + f + ", ").orElse("") +
            optionalFnPnr().map(f -> "fnPnr=" + f + ", ").orElse("") +
            optionalFnVidmol().map(f -> "fnVidmol=" + f + ", ").orElse("") +
            optionalFnDrugi().map(f -> "fnDrugi=" + f + ", ").orElse("") +
            optionalFnDeviceid().map(f -> "fnDeviceid=" + f + ", ").orElse("") +
            optionalFnScanres().map(f -> "fnScanres=" + f + ", ").orElse("") +
            optionalFnWidth().map(f -> "fnWidth=" + f + ", ").orElse("") +
            optionalFnHeight().map(f -> "fnHeight=" + f + ", ").orElse("") +
            optionalFnPixeldepth().map(f -> "fnPixeldepth=" + f + ", ").orElse("") +
            optionalFnCompressalgo().map(f -> "fnCompressalgo=" + f + ", ").orElse("") +
            optionalFnFingerposition().map(f -> "fnFingerposition=" + f + ", ").orElse("") +
            optionalFnImagequality().map(f -> "fnImagequality=" + f + ", ").orElse("") +
            optionalFnImage().map(f -> "fnImage=" + f + ", ").orElse("") +
            optionalFnImglen().map(f -> "fnImglen=" + f + ", ").orElse("") +
            optionalFnNottakenreason().map(f -> "fnNottakenreason=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
