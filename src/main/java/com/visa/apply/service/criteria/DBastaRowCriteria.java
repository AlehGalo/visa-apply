package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DBastaRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DBastaRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-basta-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DBastaRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter dcFamil;

    private StringFilter dcImena;

    private StringFilter dcPol;

    private StringFilter dcGrad;

    private StringFilter dcUlica;

    private Boolean distinct;

    public DBastaRowCriteria() {}

    public DBastaRowCriteria(DBastaRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.dcFamil = other.optionalDcFamil().map(StringFilter::copy).orElse(null);
        this.dcImena = other.optionalDcImena().map(StringFilter::copy).orElse(null);
        this.dcPol = other.optionalDcPol().map(StringFilter::copy).orElse(null);
        this.dcGrad = other.optionalDcGrad().map(StringFilter::copy).orElse(null);
        this.dcUlica = other.optionalDcUlica().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DBastaRowCriteria copy() {
        return new DBastaRowCriteria(this);
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

    public StringFilter getDcFamil() {
        return dcFamil;
    }

    public Optional<StringFilter> optionalDcFamil() {
        return Optional.ofNullable(dcFamil);
    }

    public StringFilter dcFamil() {
        if (dcFamil == null) {
            setDcFamil(new StringFilter());
        }
        return dcFamil;
    }

    public void setDcFamil(StringFilter dcFamil) {
        this.dcFamil = dcFamil;
    }

    public StringFilter getDcImena() {
        return dcImena;
    }

    public Optional<StringFilter> optionalDcImena() {
        return Optional.ofNullable(dcImena);
    }

    public StringFilter dcImena() {
        if (dcImena == null) {
            setDcImena(new StringFilter());
        }
        return dcImena;
    }

    public void setDcImena(StringFilter dcImena) {
        this.dcImena = dcImena;
    }

    public StringFilter getDcPol() {
        return dcPol;
    }

    public Optional<StringFilter> optionalDcPol() {
        return Optional.ofNullable(dcPol);
    }

    public StringFilter dcPol() {
        if (dcPol == null) {
            setDcPol(new StringFilter());
        }
        return dcPol;
    }

    public void setDcPol(StringFilter dcPol) {
        this.dcPol = dcPol;
    }

    public StringFilter getDcGrad() {
        return dcGrad;
    }

    public Optional<StringFilter> optionalDcGrad() {
        return Optional.ofNullable(dcGrad);
    }

    public StringFilter dcGrad() {
        if (dcGrad == null) {
            setDcGrad(new StringFilter());
        }
        return dcGrad;
    }

    public void setDcGrad(StringFilter dcGrad) {
        this.dcGrad = dcGrad;
    }

    public StringFilter getDcUlica() {
        return dcUlica;
    }

    public Optional<StringFilter> optionalDcUlica() {
        return Optional.ofNullable(dcUlica);
    }

    public StringFilter dcUlica() {
        if (dcUlica == null) {
            setDcUlica(new StringFilter());
        }
        return dcUlica;
    }

    public void setDcUlica(StringFilter dcUlica) {
        this.dcUlica = dcUlica;
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
        final DBastaRowCriteria that = (DBastaRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(dcFamil, that.dcFamil) &&
            Objects.equals(dcImena, that.dcImena) &&
            Objects.equals(dcPol, that.dcPol) &&
            Objects.equals(dcGrad, that.dcGrad) &&
            Objects.equals(dcUlica, that.dcUlica) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dcFamil, dcImena, dcPol, dcGrad, dcUlica, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DBastaRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalDcFamil().map(f -> "dcFamil=" + f + ", ").orElse("") +
            optionalDcImena().map(f -> "dcImena=" + f + ", ").orElse("") +
            optionalDcPol().map(f -> "dcPol=" + f + ", ").orElse("") +
            optionalDcGrad().map(f -> "dcGrad=" + f + ", ").orElse("") +
            optionalDcUlica().map(f -> "dcUlica=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
