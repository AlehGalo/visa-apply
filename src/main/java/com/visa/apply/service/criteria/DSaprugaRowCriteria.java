package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DSaprugaRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DSaprugaRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-sapruga-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DSaprugaRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter spMrjdarj;

    private Boolean distinct;

    public DSaprugaRowCriteria() {}

    public DSaprugaRowCriteria(DSaprugaRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.spMrjdarj = other.optionalSpMrjdarj().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DSaprugaRowCriteria copy() {
        return new DSaprugaRowCriteria(this);
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

    public StringFilter getSpMrjdarj() {
        return spMrjdarj;
    }

    public Optional<StringFilter> optionalSpMrjdarj() {
        return Optional.ofNullable(spMrjdarj);
    }

    public StringFilter spMrjdarj() {
        if (spMrjdarj == null) {
            setSpMrjdarj(new StringFilter());
        }
        return spMrjdarj;
    }

    public void setSpMrjdarj(StringFilter spMrjdarj) {
        this.spMrjdarj = spMrjdarj;
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
        final DSaprugaRowCriteria that = (DSaprugaRowCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(spMrjdarj, that.spMrjdarj) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spMrjdarj, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DSaprugaRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalSpMrjdarj().map(f -> "spMrjdarj=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
