package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DImagesRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DImagesRowDTO implements Serializable {

    private Long id;

    private Integer imDevicetype;

    private Integer imWidth;

    private Integer imHeight;

    private Integer imImglen;

    @NotNull
    @Size(max = 255)
    private String imImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getImDevicetype() {
        return imDevicetype;
    }

    public void setImDevicetype(Integer imDevicetype) {
        this.imDevicetype = imDevicetype;
    }

    public Integer getImWidth() {
        return imWidth;
    }

    public void setImWidth(Integer imWidth) {
        this.imWidth = imWidth;
    }

    public Integer getImHeight() {
        return imHeight;
    }

    public void setImHeight(Integer imHeight) {
        this.imHeight = imHeight;
    }

    public Integer getImImglen() {
        return imImglen;
    }

    public void setImImglen(Integer imImglen) {
        this.imImglen = imImglen;
    }

    public String getImImage() {
        return imImage;
    }

    public void setImImage(String imImage) {
        this.imImage = imImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DImagesRowDTO)) {
            return false;
        }

        DImagesRowDTO dImagesRowDTO = (DImagesRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dImagesRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DImagesRowDTO{" +
            "id=" + getId() +
            ", imDevicetype=" + getImDevicetype() +
            ", imWidth=" + getImWidth() +
            ", imHeight=" + getImHeight() +
            ", imImglen=" + getImImglen() +
            ", imImage='" + getImImage() + "'" +
            "}";
    }
}
