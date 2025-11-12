package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DEurodaRow.
 */
@Entity
@Table(name = "d_euroda_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DEurodaRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "eu_famil", length = 255, nullable = false)
    private String euFamil;

    @NotNull
    @Size(max = 255)
    @Column(name = "eu_imena", length = 255, nullable = false)
    private String euImena;

    @NotNull
    @Size(max = 255)
    @Column(name = "eu_nac_bel", length = 255, nullable = false)
    private String euNacBel;

    @NotNull
    @Size(max = 255)
    @Column(name = "eu_rodstvo", length = 255, nullable = false)
    private String euRodstvo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DEurodaRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEuFamil() {
        return this.euFamil;
    }

    public DEurodaRow euFamil(String euFamil) {
        this.setEuFamil(euFamil);
        return this;
    }

    public void setEuFamil(String euFamil) {
        this.euFamil = euFamil;
    }

    public String getEuImena() {
        return this.euImena;
    }

    public DEurodaRow euImena(String euImena) {
        this.setEuImena(euImena);
        return this;
    }

    public void setEuImena(String euImena) {
        this.euImena = euImena;
    }

    public String getEuNacBel() {
        return this.euNacBel;
    }

    public DEurodaRow euNacBel(String euNacBel) {
        this.setEuNacBel(euNacBel);
        return this;
    }

    public void setEuNacBel(String euNacBel) {
        this.euNacBel = euNacBel;
    }

    public String getEuRodstvo() {
        return this.euRodstvo;
    }

    public DEurodaRow euRodstvo(String euRodstvo) {
        this.setEuRodstvo(euRodstvo);
        return this;
    }

    public void setEuRodstvo(String euRodstvo) {
        this.euRodstvo = euRodstvo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DEurodaRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DEurodaRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DEurodaRow{" +
            "id=" + getId() +
            ", euFamil='" + getEuFamil() + "'" +
            ", euImena='" + getEuImena() + "'" +
            ", euNacBel='" + getEuNacBel() + "'" +
            ", euRodstvo='" + getEuRodstvo() + "'" +
            "}";
    }
}
