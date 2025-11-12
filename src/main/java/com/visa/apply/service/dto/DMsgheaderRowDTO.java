package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DMsgheaderRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DMsgheaderRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String mhKscreated;

    private Integer mhRegnom;

    @NotNull
    @Size(max = 255)
    private String mhVfsrefno;

    @NotNull
    @Size(max = 255)
    private String mhUsera;

    @NotNull
    @Size(max = 255)
    private String mhDatvav;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMhKscreated() {
        return mhKscreated;
    }

    public void setMhKscreated(String mhKscreated) {
        this.mhKscreated = mhKscreated;
    }

    public Integer getMhRegnom() {
        return mhRegnom;
    }

    public void setMhRegnom(Integer mhRegnom) {
        this.mhRegnom = mhRegnom;
    }

    public String getMhVfsrefno() {
        return mhVfsrefno;
    }

    public void setMhVfsrefno(String mhVfsrefno) {
        this.mhVfsrefno = mhVfsrefno;
    }

    public String getMhUsera() {
        return mhUsera;
    }

    public void setMhUsera(String mhUsera) {
        this.mhUsera = mhUsera;
    }

    public String getMhDatvav() {
        return mhDatvav;
    }

    public void setMhDatvav(String mhDatvav) {
        this.mhDatvav = mhDatvav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DMsgheaderRowDTO)) {
            return false;
        }

        DMsgheaderRowDTO dMsgheaderRowDTO = (DMsgheaderRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dMsgheaderRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DMsgheaderRowDTO{" +
            "id=" + getId() +
            ", mhKscreated='" + getMhKscreated() + "'" +
            ", mhRegnom=" + getMhRegnom() +
            ", mhVfsrefno='" + getMhVfsrefno() + "'" +
            ", mhUsera='" + getMhUsera() + "'" +
            ", mhDatvav='" + getMhDatvav() + "'" +
            "}";
    }
}
