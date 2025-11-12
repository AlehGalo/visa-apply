package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DVoitRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DVoitRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-voit-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DVoitRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter vnom;

    private StringFilter vime;

    private StringFilter bgime;

    private StringFilter bgadres;

    private Boolean distinct;

    public DVoitRowCriteria() {}

    public DVoitRowCriteria(DVoitRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.vnom = other.optionalVnom().map(StringFilter::copy).orElse(null);
        this.vime = other.optionalVime().map(StringFilter::copy).orElse(null);
        this.bgime = other.optionalBgime().map(StringFilter::copy).orElse(null);
        this.bgadres = other.optionalBgadres().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DVoitRowCriteria copy() {
        return new DVoitRowCriteria(this);
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

    public StringFilter getVnom() {
        return vnom;
    }

    public Optional<StringFilter> optionalVnom() {
        return Optional.ofNullable(vnom);
    }

    public StringFilter vnom() {
        if (vnom == null) {
            setVnom(new StringFilter());
        }
        return vnom;
    }

    public void setVnom(StringFilter vnom) {
        this.vnom = vnom;
    }

    public StringFilter getVime() {
        return vime;
    }

    public Optional<StringFilter> optionalVime() {
        return Optional.ofNullable(vime);
    }

    public StringFilter vime() {
        if (vime == null) {
            setVime(new StringFilter());
        }
        return vime;
    }

    public void setVime(StringFilter vime) {
        this.vime = vime;
    }

    public StringFilter getBgime() {
        return bgime;
    }

    public Optional<StringFilter> optionalBgime() {
        return Optional.ofNullable(bgime);
    }

    public StringFilter bgime() {
        if (bgime == null) {
            setBgime(new StringFilter());
        }
        return bgime;
    }

    public void setBgime(StringFilter bgime) {
        this.bgime = bgime;
    }

    public StringFilter getBgadres() {
        return bgadres;
    }

    public Optional<StringFilter> optionalBgadres() {
        return Optional.ofNullable(bgadres);
    }

    public StringFilter bgadres() {
        if (bgadres == null) {
            setBgadres(new StringFilter());
        }
        return bgadres;
    }

    public void setBgadres(StringFilter bgadres) {
        this.bgadres = bgadres;
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
        final DVoitRowCriteria that = (DVoitRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(vnom, that.vnom) &&
            Objects.equals(vime, that.vime) &&
            Objects.equals(bgime, that.bgime) &&
            Objects.equals(bgadres, that.bgadres) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vnom, vime, bgime, bgadres, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DVoitRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalVnom().map(f -> "vnom=" + f + ", ").orElse("") +
            optionalVime().map(f -> "vime=" + f + ", ").orElse("") +
            optionalBgime().map(f -> "bgime=" + f + ", ").orElse("") +
            optionalBgadres().map(f -> "bgadres=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
