package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DEurodaRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DEurodaRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-euroda-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DEurodaRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter euFamil;

    private StringFilter euImena;

    private StringFilter euNacBel;

    private StringFilter euRodstvo;

    private Boolean distinct;

    public DEurodaRowCriteria() {}

    public DEurodaRowCriteria(DEurodaRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.euFamil = other.optionalEuFamil().map(StringFilter::copy).orElse(null);
        this.euImena = other.optionalEuImena().map(StringFilter::copy).orElse(null);
        this.euNacBel = other.optionalEuNacBel().map(StringFilter::copy).orElse(null);
        this.euRodstvo = other.optionalEuRodstvo().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DEurodaRowCriteria copy() {
        return new DEurodaRowCriteria(this);
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

    public StringFilter getEuFamil() {
        return euFamil;
    }

    public Optional<StringFilter> optionalEuFamil() {
        return Optional.ofNullable(euFamil);
    }

    public StringFilter euFamil() {
        if (euFamil == null) {
            setEuFamil(new StringFilter());
        }
        return euFamil;
    }

    public void setEuFamil(StringFilter euFamil) {
        this.euFamil = euFamil;
    }

    public StringFilter getEuImena() {
        return euImena;
    }

    public Optional<StringFilter> optionalEuImena() {
        return Optional.ofNullable(euImena);
    }

    public StringFilter euImena() {
        if (euImena == null) {
            setEuImena(new StringFilter());
        }
        return euImena;
    }

    public void setEuImena(StringFilter euImena) {
        this.euImena = euImena;
    }

    public StringFilter getEuNacBel() {
        return euNacBel;
    }

    public Optional<StringFilter> optionalEuNacBel() {
        return Optional.ofNullable(euNacBel);
    }

    public StringFilter euNacBel() {
        if (euNacBel == null) {
            setEuNacBel(new StringFilter());
        }
        return euNacBel;
    }

    public void setEuNacBel(StringFilter euNacBel) {
        this.euNacBel = euNacBel;
    }

    public StringFilter getEuRodstvo() {
        return euRodstvo;
    }

    public Optional<StringFilter> optionalEuRodstvo() {
        return Optional.ofNullable(euRodstvo);
    }

    public StringFilter euRodstvo() {
        if (euRodstvo == null) {
            setEuRodstvo(new StringFilter());
        }
        return euRodstvo;
    }

    public void setEuRodstvo(StringFilter euRodstvo) {
        this.euRodstvo = euRodstvo;
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
        final DEurodaRowCriteria that = (DEurodaRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(euFamil, that.euFamil) &&
            Objects.equals(euImena, that.euImena) &&
            Objects.equals(euNacBel, that.euNacBel) &&
            Objects.equals(euRodstvo, that.euRodstvo) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, euFamil, euImena, euNacBel, euRodstvo, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DEurodaRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalEuFamil().map(f -> "euFamil=" + f + ", ").orElse("") +
            optionalEuImena().map(f -> "euImena=" + f + ", ").orElse("") +
            optionalEuNacBel().map(f -> "euNacBel=" + f + ", ").orElse("") +
            optionalEuRodstvo().map(f -> "euRodstvo=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
