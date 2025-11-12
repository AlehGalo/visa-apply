package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DLcdopRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DLcdopRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-lcdop-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcdopRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ldMrjdarj;

    private StringFilter ldMrjnm;

    private StringFilter ldMrjgraj;

    private StringFilter ldZenen;

    private StringFilter ldJitDarj;

    private StringFilter ldJitNm;

    private StringFilter ldJitUl;

    private LongFilter ldTel;

    private StringFilter ldRabota;

    private StringFilter ldProfkod;

    private StringFilter ldProfesia;

    private StringFilter ldSlDarj;

    private StringFilter ldSlNm;

    private StringFilter ldSlUl;

    private Boolean distinct;

    public DLcdopRowCriteria() {}

    public DLcdopRowCriteria(DLcdopRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.ldMrjdarj = other.optionalLdMrjdarj().map(StringFilter::copy).orElse(null);
        this.ldMrjnm = other.optionalLdMrjnm().map(StringFilter::copy).orElse(null);
        this.ldMrjgraj = other.optionalLdMrjgraj().map(StringFilter::copy).orElse(null);
        this.ldZenen = other.optionalLdZenen().map(StringFilter::copy).orElse(null);
        this.ldJitDarj = other.optionalLdJitDarj().map(StringFilter::copy).orElse(null);
        this.ldJitNm = other.optionalLdJitNm().map(StringFilter::copy).orElse(null);
        this.ldJitUl = other.optionalLdJitUl().map(StringFilter::copy).orElse(null);
        this.ldTel = other.optionalLdTel().map(LongFilter::copy).orElse(null);
        this.ldRabota = other.optionalLdRabota().map(StringFilter::copy).orElse(null);
        this.ldProfkod = other.optionalLdProfkod().map(StringFilter::copy).orElse(null);
        this.ldProfesia = other.optionalLdProfesia().map(StringFilter::copy).orElse(null);
        this.ldSlDarj = other.optionalLdSlDarj().map(StringFilter::copy).orElse(null);
        this.ldSlNm = other.optionalLdSlNm().map(StringFilter::copy).orElse(null);
        this.ldSlUl = other.optionalLdSlUl().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DLcdopRowCriteria copy() {
        return new DLcdopRowCriteria(this);
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

    public StringFilter getLdMrjdarj() {
        return ldMrjdarj;
    }

    public Optional<StringFilter> optionalLdMrjdarj() {
        return Optional.ofNullable(ldMrjdarj);
    }

    public StringFilter ldMrjdarj() {
        if (ldMrjdarj == null) {
            setLdMrjdarj(new StringFilter());
        }
        return ldMrjdarj;
    }

    public void setLdMrjdarj(StringFilter ldMrjdarj) {
        this.ldMrjdarj = ldMrjdarj;
    }

    public StringFilter getLdMrjnm() {
        return ldMrjnm;
    }

    public Optional<StringFilter> optionalLdMrjnm() {
        return Optional.ofNullable(ldMrjnm);
    }

    public StringFilter ldMrjnm() {
        if (ldMrjnm == null) {
            setLdMrjnm(new StringFilter());
        }
        return ldMrjnm;
    }

    public void setLdMrjnm(StringFilter ldMrjnm) {
        this.ldMrjnm = ldMrjnm;
    }

    public StringFilter getLdMrjgraj() {
        return ldMrjgraj;
    }

    public Optional<StringFilter> optionalLdMrjgraj() {
        return Optional.ofNullable(ldMrjgraj);
    }

    public StringFilter ldMrjgraj() {
        if (ldMrjgraj == null) {
            setLdMrjgraj(new StringFilter());
        }
        return ldMrjgraj;
    }

    public void setLdMrjgraj(StringFilter ldMrjgraj) {
        this.ldMrjgraj = ldMrjgraj;
    }

    public StringFilter getLdZenen() {
        return ldZenen;
    }

    public Optional<StringFilter> optionalLdZenen() {
        return Optional.ofNullable(ldZenen);
    }

    public StringFilter ldZenen() {
        if (ldZenen == null) {
            setLdZenen(new StringFilter());
        }
        return ldZenen;
    }

    public void setLdZenen(StringFilter ldZenen) {
        this.ldZenen = ldZenen;
    }

    public StringFilter getLdJitDarj() {
        return ldJitDarj;
    }

    public Optional<StringFilter> optionalLdJitDarj() {
        return Optional.ofNullable(ldJitDarj);
    }

    public StringFilter ldJitDarj() {
        if (ldJitDarj == null) {
            setLdJitDarj(new StringFilter());
        }
        return ldJitDarj;
    }

    public void setLdJitDarj(StringFilter ldJitDarj) {
        this.ldJitDarj = ldJitDarj;
    }

    public StringFilter getLdJitNm() {
        return ldJitNm;
    }

    public Optional<StringFilter> optionalLdJitNm() {
        return Optional.ofNullable(ldJitNm);
    }

    public StringFilter ldJitNm() {
        if (ldJitNm == null) {
            setLdJitNm(new StringFilter());
        }
        return ldJitNm;
    }

    public void setLdJitNm(StringFilter ldJitNm) {
        this.ldJitNm = ldJitNm;
    }

    public StringFilter getLdJitUl() {
        return ldJitUl;
    }

    public Optional<StringFilter> optionalLdJitUl() {
        return Optional.ofNullable(ldJitUl);
    }

    public StringFilter ldJitUl() {
        if (ldJitUl == null) {
            setLdJitUl(new StringFilter());
        }
        return ldJitUl;
    }

    public void setLdJitUl(StringFilter ldJitUl) {
        this.ldJitUl = ldJitUl;
    }

    public LongFilter getLdTel() {
        return ldTel;
    }

    public Optional<LongFilter> optionalLdTel() {
        return Optional.ofNullable(ldTel);
    }

    public LongFilter ldTel() {
        if (ldTel == null) {
            setLdTel(new LongFilter());
        }
        return ldTel;
    }

    public void setLdTel(LongFilter ldTel) {
        this.ldTel = ldTel;
    }

    public StringFilter getLdRabota() {
        return ldRabota;
    }

    public Optional<StringFilter> optionalLdRabota() {
        return Optional.ofNullable(ldRabota);
    }

    public StringFilter ldRabota() {
        if (ldRabota == null) {
            setLdRabota(new StringFilter());
        }
        return ldRabota;
    }

    public void setLdRabota(StringFilter ldRabota) {
        this.ldRabota = ldRabota;
    }

    public StringFilter getLdProfkod() {
        return ldProfkod;
    }

    public Optional<StringFilter> optionalLdProfkod() {
        return Optional.ofNullable(ldProfkod);
    }

    public StringFilter ldProfkod() {
        if (ldProfkod == null) {
            setLdProfkod(new StringFilter());
        }
        return ldProfkod;
    }

    public void setLdProfkod(StringFilter ldProfkod) {
        this.ldProfkod = ldProfkod;
    }

    public StringFilter getLdProfesia() {
        return ldProfesia;
    }

    public Optional<StringFilter> optionalLdProfesia() {
        return Optional.ofNullable(ldProfesia);
    }

    public StringFilter ldProfesia() {
        if (ldProfesia == null) {
            setLdProfesia(new StringFilter());
        }
        return ldProfesia;
    }

    public void setLdProfesia(StringFilter ldProfesia) {
        this.ldProfesia = ldProfesia;
    }

    public StringFilter getLdSlDarj() {
        return ldSlDarj;
    }

    public Optional<StringFilter> optionalLdSlDarj() {
        return Optional.ofNullable(ldSlDarj);
    }

    public StringFilter ldSlDarj() {
        if (ldSlDarj == null) {
            setLdSlDarj(new StringFilter());
        }
        return ldSlDarj;
    }

    public void setLdSlDarj(StringFilter ldSlDarj) {
        this.ldSlDarj = ldSlDarj;
    }

    public StringFilter getLdSlNm() {
        return ldSlNm;
    }

    public Optional<StringFilter> optionalLdSlNm() {
        return Optional.ofNullable(ldSlNm);
    }

    public StringFilter ldSlNm() {
        if (ldSlNm == null) {
            setLdSlNm(new StringFilter());
        }
        return ldSlNm;
    }

    public void setLdSlNm(StringFilter ldSlNm) {
        this.ldSlNm = ldSlNm;
    }

    public StringFilter getLdSlUl() {
        return ldSlUl;
    }

    public Optional<StringFilter> optionalLdSlUl() {
        return Optional.ofNullable(ldSlUl);
    }

    public StringFilter ldSlUl() {
        if (ldSlUl == null) {
            setLdSlUl(new StringFilter());
        }
        return ldSlUl;
    }

    public void setLdSlUl(StringFilter ldSlUl) {
        this.ldSlUl = ldSlUl;
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
        final DLcdopRowCriteria that = (DLcdopRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(ldMrjdarj, that.ldMrjdarj) &&
            Objects.equals(ldMrjnm, that.ldMrjnm) &&
            Objects.equals(ldMrjgraj, that.ldMrjgraj) &&
            Objects.equals(ldZenen, that.ldZenen) &&
            Objects.equals(ldJitDarj, that.ldJitDarj) &&
            Objects.equals(ldJitNm, that.ldJitNm) &&
            Objects.equals(ldJitUl, that.ldJitUl) &&
            Objects.equals(ldTel, that.ldTel) &&
            Objects.equals(ldRabota, that.ldRabota) &&
            Objects.equals(ldProfkod, that.ldProfkod) &&
            Objects.equals(ldProfesia, that.ldProfesia) &&
            Objects.equals(ldSlDarj, that.ldSlDarj) &&
            Objects.equals(ldSlNm, that.ldSlNm) &&
            Objects.equals(ldSlUl, that.ldSlUl) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            ldMrjdarj,
            ldMrjnm,
            ldMrjgraj,
            ldZenen,
            ldJitDarj,
            ldJitNm,
            ldJitUl,
            ldTel,
            ldRabota,
            ldProfkod,
            ldProfesia,
            ldSlDarj,
            ldSlNm,
            ldSlUl,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcdopRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalLdMrjdarj().map(f -> "ldMrjdarj=" + f + ", ").orElse("") +
            optionalLdMrjnm().map(f -> "ldMrjnm=" + f + ", ").orElse("") +
            optionalLdMrjgraj().map(f -> "ldMrjgraj=" + f + ", ").orElse("") +
            optionalLdZenen().map(f -> "ldZenen=" + f + ", ").orElse("") +
            optionalLdJitDarj().map(f -> "ldJitDarj=" + f + ", ").orElse("") +
            optionalLdJitNm().map(f -> "ldJitNm=" + f + ", ").orElse("") +
            optionalLdJitUl().map(f -> "ldJitUl=" + f + ", ").orElse("") +
            optionalLdTel().map(f -> "ldTel=" + f + ", ").orElse("") +
            optionalLdRabota().map(f -> "ldRabota=" + f + ", ").orElse("") +
            optionalLdProfkod().map(f -> "ldProfkod=" + f + ", ").orElse("") +
            optionalLdProfesia().map(f -> "ldProfesia=" + f + ", ").orElse("") +
            optionalLdSlDarj().map(f -> "ldSlDarj=" + f + ", ").orElse("") +
            optionalLdSlNm().map(f -> "ldSlNm=" + f + ", ").orElse("") +
            optionalLdSlUl().map(f -> "ldSlUl=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
