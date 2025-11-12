package com.visa.apply.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.visa.apply.domain.DImagesRow} entity. This class is used
 * in {@link com.visa.apply.web.rest.DImagesRowResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /d-images-rows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DImagesRowCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter imDevicetype;

    private IntegerFilter imWidth;

    private IntegerFilter imHeight;

    private IntegerFilter imImglen;

    private StringFilter imImage;

    private Boolean distinct;

    public DImagesRowCriteria() {}

    public DImagesRowCriteria(DImagesRowCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.imDevicetype = other.optionalImDevicetype().map(IntegerFilter::copy).orElse(null);
        this.imWidth = other.optionalImWidth().map(IntegerFilter::copy).orElse(null);
        this.imHeight = other.optionalImHeight().map(IntegerFilter::copy).orElse(null);
        this.imImglen = other.optionalImImglen().map(IntegerFilter::copy).orElse(null);
        this.imImage = other.optionalImImage().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DImagesRowCriteria copy() {
        return new DImagesRowCriteria(this);
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

    public IntegerFilter getImDevicetype() {
        return imDevicetype;
    }

    public Optional<IntegerFilter> optionalImDevicetype() {
        return Optional.ofNullable(imDevicetype);
    }

    public IntegerFilter imDevicetype() {
        if (imDevicetype == null) {
            setImDevicetype(new IntegerFilter());
        }
        return imDevicetype;
    }

    public void setImDevicetype(IntegerFilter imDevicetype) {
        this.imDevicetype = imDevicetype;
    }

    public IntegerFilter getImWidth() {
        return imWidth;
    }

    public Optional<IntegerFilter> optionalImWidth() {
        return Optional.ofNullable(imWidth);
    }

    public IntegerFilter imWidth() {
        if (imWidth == null) {
            setImWidth(new IntegerFilter());
        }
        return imWidth;
    }

    public void setImWidth(IntegerFilter imWidth) {
        this.imWidth = imWidth;
    }

    public IntegerFilter getImHeight() {
        return imHeight;
    }

    public Optional<IntegerFilter> optionalImHeight() {
        return Optional.ofNullable(imHeight);
    }

    public IntegerFilter imHeight() {
        if (imHeight == null) {
            setImHeight(new IntegerFilter());
        }
        return imHeight;
    }

    public void setImHeight(IntegerFilter imHeight) {
        this.imHeight = imHeight;
    }

    public IntegerFilter getImImglen() {
        return imImglen;
    }

    public Optional<IntegerFilter> optionalImImglen() {
        return Optional.ofNullable(imImglen);
    }

    public IntegerFilter imImglen() {
        if (imImglen == null) {
            setImImglen(new IntegerFilter());
        }
        return imImglen;
    }

    public void setImImglen(IntegerFilter imImglen) {
        this.imImglen = imImglen;
    }

    public StringFilter getImImage() {
        return imImage;
    }

    public Optional<StringFilter> optionalImImage() {
        return Optional.ofNullable(imImage);
    }

    public StringFilter imImage() {
        if (imImage == null) {
            setImImage(new StringFilter());
        }
        return imImage;
    }

    public void setImImage(StringFilter imImage) {
        this.imImage = imImage;
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
        final DImagesRowCriteria that = (DImagesRowCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(imDevicetype, that.imDevicetype) &&
            Objects.equals(imWidth, that.imWidth) &&
            Objects.equals(imHeight, that.imHeight) &&
            Objects.equals(imImglen, that.imImglen) &&
            Objects.equals(imImage, that.imImage) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imDevicetype, imWidth, imHeight, imImglen, imImage, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DImagesRowCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalImDevicetype().map(f -> "imDevicetype=" + f + ", ").orElse("") +
            optionalImWidth().map(f -> "imWidth=" + f + ", ").orElse("") +
            optionalImHeight().map(f -> "imHeight=" + f + ", ").orElse("") +
            optionalImImglen().map(f -> "imImglen=" + f + ", ").orElse("") +
            optionalImImage().map(f -> "imImage=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
