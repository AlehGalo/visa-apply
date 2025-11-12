package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DMsgheaderRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DMsgheaderRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-msgheader-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DMsgheaderRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter mhKscreated;

    private IntegerFilter mhRegnom;

    private StringFilter mhVfsrefno;

    private StringFilter mhUsera;

    private StringFilter mhDatvav;

    private Boolean distinct;

    public DMsgheaderRowCriteria() {}

    public DMsgheaderRowCriteria(DMsgheaderRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.mhKscreated = other.optionalMhKscreated().map(StringFilter::copy).orElse(null);
        this.mhRegnom = other.optionalMhRegnom().map(IntegerFilter::copy).orElse(null);
        this.mhVfsrefno = other.optionalMhVfsrefno().map(StringFilter::copy).orElse(null);
        this.mhUsera = other.optionalMhUsera().map(StringFilter::copy).orElse(null);
        this.mhDatvav = other.optionalMhDatvav().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DMsgheaderRowCriteria copy() {
        return new DMsgheaderRowCriteria(this);
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

    public StringFilter getMhKscreated() {
        return mhKscreated;
    }

    public Optional<StringFilter> optionalMhKscreated() {
        return Optional.ofNullable(mhKscreated);
    }

    public StringFilter mhKscreated() {
        if (mhKscreated == null) {
            setMhKscreated(new StringFilter());
        }
        return mhKscreated;
    }

    public void setMhKscreated(StringFilter mhKscreated) {
        this.mhKscreated = mhKscreated;
    }

    public IntegerFilter getMhRegnom() {
        return mhRegnom;
    }

    public Optional<IntegerFilter> optionalMhRegnom() {
        return Optional.ofNullable(mhRegnom);
    }

    public IntegerFilter mhRegnom() {
        if (mhRegnom == null) {
            setMhRegnom(new IntegerFilter());
        }
        return mhRegnom;
    }

    public void setMhRegnom(IntegerFilter mhRegnom) {
        this.mhRegnom = mhRegnom;
    }

    public StringFilter getMhVfsrefno() {
        return mhVfsrefno;
    }

    public Optional<StringFilter> optionalMhVfsrefno() {
        return Optional.ofNullable(mhVfsrefno);
    }

    public StringFilter mhVfsrefno() {
        if (mhVfsrefno == null) {
            setMhVfsrefno(new StringFilter());
        }
        return mhVfsrefno;
    }

    public void setMhVfsrefno(StringFilter mhVfsrefno) {
        this.mhVfsrefno = mhVfsrefno;
    }

    public StringFilter getMhUsera() {
        return mhUsera;
    }

    public Optional<StringFilter> optionalMhUsera() {
        return Optional.ofNullable(mhUsera);
    }

    public StringFilter mhUsera() {
        if (mhUsera == null) {
            setMhUsera(new StringFilter());
        }
        return mhUsera;
    }

    public void setMhUsera(StringFilter mhUsera) {
        this.mhUsera = mhUsera;
    }

    public StringFilter getMhDatvav() {
        return mhDatvav;
    }

    public Optional<StringFilter> optionalMhDatvav() {
        return Optional.ofNullable(mhDatvav);
    }

    public StringFilter mhDatvav() {
        if (mhDatvav == null) {
            setMhDatvav(new StringFilter());
        }
        return mhDatvav;
    }

    public void setMhDatvav(StringFilter mhDatvav) {
        this.mhDatvav = mhDatvav;
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
        final DMsgheaderRowCriteria that = (DMsgheaderRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(mhKscreated, that.mhKscreated) &&
            Objects.equals(mhRegnom, that.mhRegnom) &&
            Objects.equals(mhVfsrefno, that.mhVfsrefno) &&
            Objects.equals(mhUsera, that.mhUsera) &&
            Objects.equals(mhDatvav, that.mhDatvav) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mhKscreated, mhRegnom, mhVfsrefno, mhUsera, mhDatvav, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DMsgheaderRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalMhKscreated().map(f -> "mhKscreated=" + f + ", ").orElse("") +
            optionalMhRegnom().map(f -> "mhRegnom=" + f + ", ").orElse("") +
            optionalMhVfsrefno().map(f -> "mhVfsrefno=" + f + ", ").orElse("") +
            optionalMhUsera().map(f -> "mhUsera=" + f + ", ").orElse("") +
            optionalMhDatvav().map(f -> "mhDatvav=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
