package com.visa.apply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A DBastaRow.
 */
@Entity
@Table(name = "d_basta_row")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DBastaRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "dc_famil", length = 255, nullable = false)
    private String dcFamil;

    @NotNull
    @Size(max = 255)
    @Column(name = "dc_imena", length = 255, nullable = false)
    private String dcImena;

    @NotNull
    @Size(max = 255)
    @Column(name = "dc_pol", length = 255, nullable = false)
    private String dcPol;

    @NotNull
    @Size(max = 255)
    @Column(name = "dc_grad", length = 255, nullable = false)
    private String dcGrad;

    @NotNull
    @Size(max = 255)
    @Column(name = "dc_ulica", length = 255, nullable = false)
    private String dcUlica;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DBastaRow id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDcFamil() {
        return this.dcFamil;
    }

    public DBastaRow dcFamil(String dcFamil) {
        this.setDcFamil(dcFamil);
        return this;
    }

    public void setDcFamil(String dcFamil) {
        this.dcFamil = dcFamil;
    }

    public String getDcImena() {
        return this.dcImena;
    }

    public DBastaRow dcImena(String dcImena) {
        this.setDcImena(dcImena);
        return this;
    }

    public void setDcImena(String dcImena) {
        this.dcImena = dcImena;
    }

    public String getDcPol() {
        return this.dcPol;
    }

    public DBastaRow dcPol(String dcPol) {
        this.setDcPol(dcPol);
        return this;
    }

    public void setDcPol(String dcPol) {
        this.dcPol = dcPol;
    }

    public String getDcGrad() {
        return this.dcGrad;
    }

    public DBastaRow dcGrad(String dcGrad) {
        this.setDcGrad(dcGrad);
        return this;
    }

    public void setDcGrad(String dcGrad) {
        this.dcGrad = dcGrad;
    }

    public String getDcUlica() {
        return this.dcUlica;
    }

    public DBastaRow dcUlica(String dcUlica) {
        this.setDcUlica(dcUlica);
        return this;
    }

    public void setDcUlica(String dcUlica) {
        this.dcUlica = dcUlica;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DBastaRow)) {
            return false;
        }
        return getId() != null && getId().equals(((DBastaRow) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DBastaRow{" +
            "id=" + getId() +
            ", dcFamil='" + getDcFamil() + "'" +
            ", dcImena='" + getDcImena() + "'" +
            ", dcPol='" + getDcPol() + "'" +
            ", dcGrad='" + getDcGrad() + "'" +
            ", dcUlica='" + getDcUlica() + "'" +
            "}";
    }
}
