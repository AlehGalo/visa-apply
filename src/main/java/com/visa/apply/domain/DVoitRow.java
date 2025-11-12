package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DVoitRow.
 */
@Entity
@Table(name = "d_voit_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DVoitRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "vnom", length = 255, nullable = false)
    private String vnom;

    @NotNull
    @Size(max = 255)
    @Column(name = "vime", length = 255, nullable = false)
    private String vime;

    @NotNull
    @Size(max = 255)
    @Column(name = "bgime", length = 255, nullable = false)
    private String bgime;

    @NotNull
    @Size(max = 255)
    @Column(name = "bgadres", length = 255, nullable = false)
    private String bgadres;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DVoitRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnom() {
        return this.vnom;
    }

    public DVoitRow vnom(String vnom) {
        this.setVnom(vnom);
        return this;
    }

    public void setVnom(String vnom) {
        this.vnom = vnom;
    }

    public String getVime() {
        return this.vime;
    }

    public DVoitRow vime(String vime) {
        this.setVime(vime);
        return this;
    }

    public void setVime(String vime) {
        this.vime = vime;
    }

    public String getBgime() {
        return this.bgime;
    }

    public DVoitRow bgime(String bgime) {
        this.setBgime(bgime);
        return this;
    }

    public void setBgime(String bgime) {
        this.bgime = bgime;
    }

    public String getBgadres() {
        return this.bgadres;
    }

    public DVoitRow bgadres(String bgadres) {
        this.setBgadres(bgadres);
        return this;
    }

    public void setBgadres(String bgadres) {
        this.bgadres = bgadres;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DVoitRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DVoitRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DVoitRow{" +
            "id=" + getId() +
            ", vnom='" + getVnom() + "'" +
            ", vime='" + getVime() + "'" +
            ", bgime='" + getBgime() + "'" +
            ", bgadres='" + getBgadres() + "'" +
            "}";
    }
}
