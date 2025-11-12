package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DImagesRow.
 */
@Entity
@Table(name = "d_images_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DImagesRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "im_devicetype")
    private Integer imDevicetype;

    @Column(name = "im_width")
    private Integer imWidth;

    @Column(name = "im_height")
    private Integer imHeight;

    @Column(name = "im_imglen")
    private Integer imImglen;

    @NotNull
    @Size(max = 255)
    @Column(name = "im_image", length = 255, nullable = false)
    private String imImage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DImagesRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getImDevicetype() {
        return this.imDevicetype;
    }

    public DImagesRow imDevicetype(Integer imDevicetype) {
        this.setImDevicetype(imDevicetype);
        return this;
    }

    public void setImDevicetype(Integer imDevicetype) {
        this.imDevicetype = imDevicetype;
    }

    public Integer getImWidth() {
        return this.imWidth;
    }

    public DImagesRow imWidth(Integer imWidth) {
        this.setImWidth(imWidth);
        return this;
    }

    public void setImWidth(Integer imWidth) {
        this.imWidth = imWidth;
    }

    public Integer getImHeight() {
        return this.imHeight;
    }

    public DImagesRow imHeight(Integer imHeight) {
        this.setImHeight(imHeight);
        return this;
    }

    public void setImHeight(Integer imHeight) {
        this.imHeight = imHeight;
    }

    public Integer getImImglen() {
        return this.imImglen;
    }

    public DImagesRow imImglen(Integer imImglen) {
        this.setImImglen(imImglen);
        return this;
    }

    public void setImImglen(Integer imImglen) {
        this.imImglen = imImglen;
    }

    public String getImImage() {
        return this.imImage;
    }

    public DImagesRow imImage(String imImage) {
        this.setImImage(imImage);
        return this;
    }

    public void setImImage(String imImage) {
        this.imImage = imImage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DImagesRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DImagesRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DImagesRow{" +
            "id=" + getId() +
            ", imDevicetype=" + getImDevicetype() +
            ", imWidth=" + getImWidth() +
            ", imHeight=" + getImHeight() +
            ", imImglen=" + getImImglen() +
            ", imImage='" + getImImage() + "'" +
            "}";
    }
}
