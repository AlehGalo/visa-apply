package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DDomakinRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DDomakinRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-domakin-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DDomakinRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter dmVid;

    private StringFilter nomPok;

    private StringFilter domGraj;

    private StringFilter domFamil;

    private StringFilter domIme;

    private StringFilter domDarj;

    private IntegerFilter domNm;

    private StringFilter domAdres;

    private StringFilter vedDarj;

    private StringFilter vedNm;

    private Boolean distinct;

    public DDomakinRowCriteria() {}

    public DDomakinRowCriteria(DDomakinRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.dmVid = other.optionalDmVid().map(StringFilter::copy).orElse(null);
        this.nomPok = other.optionalNomPok().map(StringFilter::copy).orElse(null);
        this.domGraj = other.optionalDomGraj().map(StringFilter::copy).orElse(null);
        this.domFamil = other.optionalDomFamil().map(StringFilter::copy).orElse(null);
        this.domIme = other.optionalDomIme().map(StringFilter::copy).orElse(null);
        this.domDarj = other.optionalDomDarj().map(StringFilter::copy).orElse(null);
        this.domNm = other.optionalDomNm().map(IntegerFilter::copy).orElse(null);
        this.domAdres = other.optionalDomAdres().map(StringFilter::copy).orElse(null);
        this.vedDarj = other.optionalVedDarj().map(StringFilter::copy).orElse(null);
        this.vedNm = other.optionalVedNm().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DDomakinRowCriteria copy() {
        return new DDomakinRowCriteria(this);
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

    public StringFilter getDmVid() {
        return dmVid;
    }

    public Optional<StringFilter> optionalDmVid() {
        return Optional.ofNullable(dmVid);
    }

    public StringFilter dmVid() {
        if (dmVid == null) {
            setDmVid(new StringFilter());
        }
        return dmVid;
    }

    public void setDmVid(StringFilter dmVid) {
        this.dmVid = dmVid;
    }

    public StringFilter getNomPok() {
        return nomPok;
    }

    public Optional<StringFilter> optionalNomPok() {
        return Optional.ofNullable(nomPok);
    }

    public StringFilter nomPok() {
        if (nomPok == null) {
            setNomPok(new StringFilter());
        }
        return nomPok;
    }

    public void setNomPok(StringFilter nomPok) {
        this.nomPok = nomPok;
    }

    public StringFilter getDomGraj() {
        return domGraj;
    }

    public Optional<StringFilter> optionalDomGraj() {
        return Optional.ofNullable(domGraj);
    }

    public StringFilter domGraj() {
        if (domGraj == null) {
            setDomGraj(new StringFilter());
        }
        return domGraj;
    }

    public void setDomGraj(StringFilter domGraj) {
        this.domGraj = domGraj;
    }

    public StringFilter getDomFamil() {
        return domFamil;
    }

    public Optional<StringFilter> optionalDomFamil() {
        return Optional.ofNullable(domFamil);
    }

    public StringFilter domFamil() {
        if (domFamil == null) {
            setDomFamil(new StringFilter());
        }
        return domFamil;
    }

    public void setDomFamil(StringFilter domFamil) {
        this.domFamil = domFamil;
    }

    public StringFilter getDomIme() {
        return domIme;
    }

    public Optional<StringFilter> optionalDomIme() {
        return Optional.ofNullable(domIme);
    }

    public StringFilter domIme() {
        if (domIme == null) {
            setDomIme(new StringFilter());
        }
        return domIme;
    }

    public void setDomIme(StringFilter domIme) {
        this.domIme = domIme;
    }

    public StringFilter getDomDarj() {
        return domDarj;
    }

    public Optional<StringFilter> optionalDomDarj() {
        return Optional.ofNullable(domDarj);
    }

    public StringFilter domDarj() {
        if (domDarj == null) {
            setDomDarj(new StringFilter());
        }
        return domDarj;
    }

    public void setDomDarj(StringFilter domDarj) {
        this.domDarj = domDarj;
    }

    public IntegerFilter getDomNm() {
        return domNm;
    }

    public Optional<IntegerFilter> optionalDomNm() {
        return Optional.ofNullable(domNm);
    }

    public IntegerFilter domNm() {
        if (domNm == null) {
            setDomNm(new IntegerFilter());
        }
        return domNm;
    }

    public void setDomNm(IntegerFilter domNm) {
        this.domNm = domNm;
    }

    public StringFilter getDomAdres() {
        return domAdres;
    }

    public Optional<StringFilter> optionalDomAdres() {
        return Optional.ofNullable(domAdres);
    }

    public StringFilter domAdres() {
        if (domAdres == null) {
            setDomAdres(new StringFilter());
        }
        return domAdres;
    }

    public void setDomAdres(StringFilter domAdres) {
        this.domAdres = domAdres;
    }

    public StringFilter getVedDarj() {
        return vedDarj;
    }

    public Optional<StringFilter> optionalVedDarj() {
        return Optional.ofNullable(vedDarj);
    }

    public StringFilter vedDarj() {
        if (vedDarj == null) {
            setVedDarj(new StringFilter());
        }
        return vedDarj;
    }

    public void setVedDarj(StringFilter vedDarj) {
        this.vedDarj = vedDarj;
    }

    public StringFilter getVedNm() {
        return vedNm;
    }

    public Optional<StringFilter> optionalVedNm() {
        return Optional.ofNullable(vedNm);
    }

    public StringFilter vedNm() {
        if (vedNm == null) {
            setVedNm(new StringFilter());
        }
        return vedNm;
    }

    public void setVedNm(StringFilter vedNm) {
        this.vedNm = vedNm;
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
        final DDomakinRowCriteria that = (DDomakinRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(dmVid, that.dmVid) &&
            Objects.equals(nomPok, that.nomPok) &&
            Objects.equals(domGraj, that.domGraj) &&
            Objects.equals(domFamil, that.domFamil) &&
            Objects.equals(domIme, that.domIme) &&
            Objects.equals(domDarj, that.domDarj) &&
            Objects.equals(domNm, that.domNm) &&
            Objects.equals(domAdres, that.domAdres) &&
            Objects.equals(vedDarj, that.vedDarj) &&
            Objects.equals(vedNm, that.vedNm) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dmVid, nomPok, domGraj, domFamil, domIme, domDarj, domNm, domAdres, vedDarj, vedNm, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DDomakinRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalDmVid().map(f -> "dmVid=" + f + ", ").orElse("") +
            optionalNomPok().map(f -> "nomPok=" + f + ", ").orElse("") +
            optionalDomGraj().map(f -> "domGraj=" + f + ", ").orElse("") +
            optionalDomFamil().map(f -> "domFamil=" + f + ", ").orElse("") +
            optionalDomIme().map(f -> "domIme=" + f + ", ").orElse("") +
            optionalDomDarj().map(f -> "domDarj=" + f + ", ").orElse("") +
            optionalDomNm().map(f -> "domNm=" + f + ", ").orElse("") +
            optionalDomAdres().map(f -> "domAdres=" + f + ", ").orElse("") +
            optionalVedDarj().map(f -> "vedDarj=" + f + ", ").orElse("") +
            optionalVedNm().map(f -> "vedNm=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
