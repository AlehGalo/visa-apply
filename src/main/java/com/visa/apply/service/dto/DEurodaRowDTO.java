package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DEurodaRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DEurodaRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String euFamil;

    @NotNull
    @Size(max = 255)
    private String euImena;

    @NotNull
    @Size(max = 255)
    private String euNacBel;

    @NotNull
    @Size(max = 255)
    private String euRodstvo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEuFamil() {
        return euFamil;
    }

    public void setEuFamil(String euFamil) {
        this.euFamil = euFamil;
    }

    public String getEuImena() {
        return euImena;
    }

    public void setEuImena(String euImena) {
        this.euImena = euImena;
    }

    public String getEuNacBel() {
        return euNacBel;
    }

    public void setEuNacBel(String euNacBel) {
        this.euNacBel = euNacBel;
    }

    public String getEuRodstvo() {
        return euRodstvo;
    }

    public void setEuRodstvo(String euRodstvo) {
        this.euRodstvo = euRodstvo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DEurodaRowDTO)) {
            return false;
        }

        DEurodaRowDTO dEurodaRowDTO = (DEurodaRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dEurodaRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DEurodaRowDTO{" +
            "id=" + getId() +
            ", euFamil='" + getEuFamil() + "'" +
            ", euImena='" + getEuImena() + "'" +
            ", euNacBel='" + getEuNacBel() + "'" +
            ", euRodstvo='" + getEuRodstvo() + "'" +
            "}";
    }
}
