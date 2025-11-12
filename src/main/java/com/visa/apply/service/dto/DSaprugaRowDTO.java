package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DSaprugaRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DSaprugaRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String spMrjdarj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpMrjdarj() {
        return spMrjdarj;
    }

    public void setSpMrjdarj(String spMrjdarj) {
        this.spMrjdarj = spMrjdarj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DSaprugaRowDTO)) {
            return false;
        }

        DSaprugaRowDTO dSaprugaRowDTO = (DSaprugaRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dSaprugaRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DSaprugaRowDTO{" +
            "id=" + getId() +
            ", spMrjdarj='" + getSpMrjdarj() + "'" +
            "}";
    }
}
