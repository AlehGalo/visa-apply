package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DLcuzRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DLcuzRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-lcuz-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcuzRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter vidZp;

    private StringFilter nacBel;

    private IntegerFilter nacPasp;

    private LocalDateFilter paspVal;

    private StringFilter graj;

    private StringFilter famil;

    private StringFilter imena;

    private StringFilter datRaj;

    private StringFilter pol;

    private LocalDateFilter datIzd;

    private Boolean distinct;

    public DLcuzRowCriteria() {}

    public DLcuzRowCriteria(DLcuzRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.vidZp = other.optionalVidZp().map(StringFilter::copy).orElse(null);
        this.nacBel = other.optionalNacBel().map(StringFilter::copy).orElse(null);
        this.nacPasp = other.optionalNacPasp().map(IntegerFilter::copy).orElse(null);
        this.paspVal = other.optionalPaspVal().map(LocalDateFilter::copy).orElse(null);
        this.graj = other.optionalGraj().map(StringFilter::copy).orElse(null);
        this.famil = other.optionalFamil().map(StringFilter::copy).orElse(null);
        this.imena = other.optionalImena().map(StringFilter::copy).orElse(null);
        this.datRaj = other.optionalDatRaj().map(StringFilter::copy).orElse(null);
        this.pol = other.optionalPol().map(StringFilter::copy).orElse(null);
        this.datIzd = other.optionalDatIzd().map(LocalDateFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DLcuzRowCriteria copy() {
        return new DLcuzRowCriteria(this);
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

    public StringFilter getVidZp() {
        return vidZp;
    }

    public Optional<StringFilter> optionalVidZp() {
        return Optional.ofNullable(vidZp);
    }

    public StringFilter vidZp() {
        if (vidZp == null) {
            setVidZp(new StringFilter());
        }
        return vidZp;
    }

    public void setVidZp(StringFilter vidZp) {
        this.vidZp = vidZp;
    }

    public StringFilter getNacBel() {
        return nacBel;
    }

    public Optional<StringFilter> optionalNacBel() {
        return Optional.ofNullable(nacBel);
    }

    public StringFilter nacBel() {
        if (nacBel == null) {
            setNacBel(new StringFilter());
        }
        return nacBel;
    }

    public void setNacBel(StringFilter nacBel) {
        this.nacBel = nacBel;
    }

    public IntegerFilter getNacPasp() {
        return nacPasp;
    }

    public Optional<IntegerFilter> optionalNacPasp() {
        return Optional.ofNullable(nacPasp);
    }

    public IntegerFilter nacPasp() {
        if (nacPasp == null) {
            setNacPasp(new IntegerFilter());
        }
        return nacPasp;
    }

    public void setNacPasp(IntegerFilter nacPasp) {
        this.nacPasp = nacPasp;
    }

    public LocalDateFilter getPaspVal() {
        return paspVal;
    }

    public Optional<LocalDateFilter> optionalPaspVal() {
        return Optional.ofNullable(paspVal);
    }

    public LocalDateFilter paspVal() {
        if (paspVal == null) {
            setPaspVal(new LocalDateFilter());
        }
        return paspVal;
    }

    public void setPaspVal(LocalDateFilter paspVal) {
        this.paspVal = paspVal;
    }

    public StringFilter getGraj() {
        return graj;
    }

    public Optional<StringFilter> optionalGraj() {
        return Optional.ofNullable(graj);
    }

    public StringFilter graj() {
        if (graj == null) {
            setGraj(new StringFilter());
        }
        return graj;
    }

    public void setGraj(StringFilter graj) {
        this.graj = graj;
    }

    public StringFilter getFamil() {
        return famil;
    }

    public Optional<StringFilter> optionalFamil() {
        return Optional.ofNullable(famil);
    }

    public StringFilter famil() {
        if (famil == null) {
            setFamil(new StringFilter());
        }
        return famil;
    }

    public void setFamil(StringFilter famil) {
        this.famil = famil;
    }

    public StringFilter getImena() {
        return imena;
    }

    public Optional<StringFilter> optionalImena() {
        return Optional.ofNullable(imena);
    }

    public StringFilter imena() {
        if (imena == null) {
            setImena(new StringFilter());
        }
        return imena;
    }

    public void setImena(StringFilter imena) {
        this.imena = imena;
    }

    public StringFilter getDatRaj() {
        return datRaj;
    }

    public Optional<StringFilter> optionalDatRaj() {
        return Optional.ofNullable(datRaj);
    }

    public StringFilter datRaj() {
        if (datRaj == null) {
            setDatRaj(new StringFilter());
        }
        return datRaj;
    }

    public void setDatRaj(StringFilter datRaj) {
        this.datRaj = datRaj;
    }

    public StringFilter getPol() {
        return pol;
    }

    public Optional<StringFilter> optionalPol() {
        return Optional.ofNullable(pol);
    }

    public StringFilter pol() {
        if (pol == null) {
            setPol(new StringFilter());
        }
        return pol;
    }

    public void setPol(StringFilter pol) {
        this.pol = pol;
    }

    public LocalDateFilter getDatIzd() {
        return datIzd;
    }

    public Optional<LocalDateFilter> optionalDatIzd() {
        return Optional.ofNullable(datIzd);
    }

    public LocalDateFilter datIzd() {
        if (datIzd == null) {
            setDatIzd(new LocalDateFilter());
        }
        return datIzd;
    }

    public void setDatIzd(LocalDateFilter datIzd) {
        this.datIzd = datIzd;
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
        final DLcuzRowCriteria that = (DLcuzRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(vidZp, that.vidZp) &&
            Objects.equals(nacBel, that.nacBel) &&
            Objects.equals(nacPasp, that.nacPasp) &&
            Objects.equals(paspVal, that.paspVal) &&
            Objects.equals(graj, that.graj) &&
            Objects.equals(famil, that.famil) &&
            Objects.equals(imena, that.imena) &&
            Objects.equals(datRaj, that.datRaj) &&
            Objects.equals(pol, that.pol) &&
            Objects.equals(datIzd, that.datIzd) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vidZp, nacBel, nacPasp, paspVal, graj, famil, imena, datRaj, pol, datIzd, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcuzRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalVidZp().map(f -> "vidZp=" + f + ", ").orElse("") +
            optionalNacBel().map(f -> "nacBel=" + f + ", ").orElse("") +
            optionalNacPasp().map(f -> "nacPasp=" + f + ", ").orElse("") +
            optionalPaspVal().map(f -> "paspVal=" + f + ", ").orElse("") +
            optionalGraj().map(f -> "graj=" + f + ", ").orElse("") +
            optionalFamil().map(f -> "famil=" + f + ", ").orElse("") +
            optionalImena().map(f -> "imena=" + f + ", ").orElse("") +
            optionalDatRaj().map(f -> "datRaj=" + f + ", ").orElse("") +
            optionalPol().map(f -> "pol=" + f + ", ").orElse("") +
            optionalDatIzd().map(f -> "datIzd=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
