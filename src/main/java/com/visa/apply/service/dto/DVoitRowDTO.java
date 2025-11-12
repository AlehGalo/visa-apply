package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DVoitRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DVoitRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String vnom;

    @NotNull
    @Size(max = 255)
    private String vime;

    @NotNull
    @Size(max = 255)
    private String bgime;

    @NotNull
    @Size(max = 255)
    private String bgadres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnom() {
        return vnom;
    }

    public void setVnom(String vnom) {
        this.vnom = vnom;
    }

    public String getVime() {
        return vime;
    }

    public void setVime(String vime) {
        this.vime = vime;
    }

    public String getBgime() {
        return bgime;
    }

    public void setBgime(String bgime) {
        this.bgime = bgime;
    }

    public String getBgadres() {
        return bgadres;
    }

    public void setBgadres(String bgadres) {
        this.bgadres = bgadres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DVoitRowDTO)) {
            return false;
        }

        DVoitRowDTO dVoitRowDTO = (DVoitRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dVoitRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DVoitRowDTO{" +
            "id=" + getId() +
            ", vnom='" + getVnom() + "'" +
            ", vime='" + getVime() + "'" +
            ", bgime='" + getBgime() + "'" +
            ", bgadres='" + getBgadres() + "'" +
            "}";
    }
}
