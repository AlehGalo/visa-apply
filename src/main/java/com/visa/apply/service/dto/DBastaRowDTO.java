package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DBastaRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DBastaRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String dcFamil;

    @NotNull
    @Size(max = 255)
    private String dcImena;

    @NotNull
    @Size(max = 255)
    private String dcPol;

    @NotNull
    @Size(max = 255)
    private String dcGrad;

    @NotNull
    @Size(max = 255)
    private String dcUlica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDcFamil() {
        return dcFamil;
    }

    public void setDcFamil(String dcFamil) {
        this.dcFamil = dcFamil;
    }

    public String getDcImena() {
        return dcImena;
    }

    public void setDcImena(String dcImena) {
        this.dcImena = dcImena;
    }

    public String getDcPol() {
        return dcPol;
    }

    public void setDcPol(String dcPol) {
        this.dcPol = dcPol;
    }

    public String getDcGrad() {
        return dcGrad;
    }

    public void setDcGrad(String dcGrad) {
        this.dcGrad = dcGrad;
    }

    public String getDcUlica() {
        return dcUlica;
    }

    public void setDcUlica(String dcUlica) {
        this.dcUlica = dcUlica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DBastaRowDTO)) {
            return false;
        }

        DBastaRowDTO dBastaRowDTO = (DBastaRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dBastaRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DBastaRowDTO{" +
            "id=" + getId() +
            ", dcFamil='" + getDcFamil() + "'" +
            ", dcImena='" + getDcImena() + "'" +
            ", dcPol='" + getDcPol() + "'" +
            ", dcGrad='" + getDcGrad() + "'" +
            ", dcUlica='" + getDcUlica() + "'" +
            "}";
    }
}
