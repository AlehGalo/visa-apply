package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DSaprugaRow.
 */
@Entity
@Table(name = "d_sapruga_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DSaprugaRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "sp_mrjdarj", length = 255, nullable = false)
    private String spMrjdarj;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DSaprugaRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpMrjdarj() {
        return this.spMrjdarj;
    }

    public DSaprugaRow spMrjdarj(String spMrjdarj) {
        this.setSpMrjdarj(spMrjdarj);
        return this;
    }

    public void setSpMrjdarj(String spMrjdarj) {
        this.spMrjdarj = spMrjdarj;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DSaprugaRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DSaprugaRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DSaprugaRow{" +
            "id=" + getId() +
            ", spMrjdarj='" + getSpMrjdarj() + "'" +
            "}";
    }
}
