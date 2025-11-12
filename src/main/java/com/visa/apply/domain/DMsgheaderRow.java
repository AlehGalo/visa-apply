package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DMsgheaderRow.
 */
@Entity
@Table(name = "d_msgheader_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DMsgheaderRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "mh_kscreated", length = 255, nullable = false)
    private String mhKscreated;

    @Column(name = "mh_regnom")
    private Integer mhRegnom;

    @NotNull
    @Size(max = 255)
    @Column(name = "mh_vfsrefno", length = 255, nullable = false)
    private String mhVfsrefno;

    @NotNull
    @Size(max = 255)
    @Column(name = "mh_usera", length = 255, nullable = false)
    private String mhUsera;

    @NotNull
    @Size(max = 255)
    @Column(name = "mh_datvav", length = 255, nullable = false)
    private String mhDatvav;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DMsgheaderRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMhKscreated() {
        return this.mhKscreated;
    }

    public DMsgheaderRow mhKscreated(String mhKscreated) {
        this.setMhKscreated(mhKscreated);
        return this;
    }

    public void setMhKscreated(String mhKscreated) {
        this.mhKscreated = mhKscreated;
    }

    public Integer getMhRegnom() {
        return this.mhRegnom;
    }

    public DMsgheaderRow mhRegnom(Integer mhRegnom) {
        this.setMhRegnom(mhRegnom);
        return this;
    }

    public void setMhRegnom(Integer mhRegnom) {
        this.mhRegnom = mhRegnom;
    }

    public String getMhVfsrefno() {
        return this.mhVfsrefno;
    }

    public DMsgheaderRow mhVfsrefno(String mhVfsrefno) {
        this.setMhVfsrefno(mhVfsrefno);
        return this;
    }

    public void setMhVfsrefno(String mhVfsrefno) {
        this.mhVfsrefno = mhVfsrefno;
    }

    public String getMhUsera() {
        return this.mhUsera;
    }

    public DMsgheaderRow mhUsera(String mhUsera) {
        this.setMhUsera(mhUsera);
        return this;
    }

    public void setMhUsera(String mhUsera) {
        this.mhUsera = mhUsera;
    }

    public String getMhDatvav() {
        return this.mhDatvav;
    }

    public DMsgheaderRow mhDatvav(String mhDatvav) {
        this.setMhDatvav(mhDatvav);
        return this;
    }

    public void setMhDatvav(String mhDatvav) {
        this.mhDatvav = mhDatvav;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DMsgheaderRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DMsgheaderRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DMsgheaderRow{" +
            "id=" + getId() +
            ", mhKscreated='" + getMhKscreated() + "'" +
            ", mhRegnom=" + getMhRegnom() +
            ", mhVfsrefno='" + getMhVfsrefno() + "'" +
            ", mhUsera='" + getMhUsera() + "'" +
            ", mhDatvav='" + getMhDatvav() + "'" +
            "}";
    }
}
