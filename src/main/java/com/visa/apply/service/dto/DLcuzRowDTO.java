package com.visa.apply.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.visa.apply.domain.DLcuzRow} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DLcuzRowDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String vidZp;

    @NotNull
    @Size(max = 255)
    private String nacBel;

    private Integer nacPasp;

    @NotNull
    private LocalDate paspVal;

    @NotNull
    @Size(max = 255)
    private String graj;

    @NotNull
    @Size(max = 255)
    private String famil;

    @NotNull
    @Size(max = 255)
    private String imena;

    @NotNull
    @Size(max = 255)
    private String datRaj;

    @NotNull
    @Size(max = 255)
    private String pol;

    @NotNull
    private LocalDate datIzd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVidZp() {
        return vidZp;
    }

    public void setVidZp(String vidZp) {
        this.vidZp = vidZp;
    }

    public String getNacBel() {
        return nacBel;
    }

    public void setNacBel(String nacBel) {
        this.nacBel = nacBel;
    }

    public Integer getNacPasp() {
        return nacPasp;
    }

    public void setNacPasp(Integer nacPasp) {
        this.nacPasp = nacPasp;
    }

    public LocalDate getPaspVal() {
        return paspVal;
    }

    public void setPaspVal(LocalDate paspVal) {
        this.paspVal = paspVal;
    }

    public String getGraj() {
        return graj;
    }

    public void setGraj(String graj) {
        this.graj = graj;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public String getImena() {
        return imena;
    }

    public void setImena(String imena) {
        this.imena = imena;
    }

    public String getDatRaj() {
        return datRaj;
    }

    public void setDatRaj(String datRaj) {
        this.datRaj = datRaj;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public LocalDate getDatIzd() {
        return datIzd;
    }

    public void setDatIzd(LocalDate datIzd) {
        this.datIzd = datIzd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DLcuzRowDTO)) {
            return false;
        }

        DLcuzRowDTO dLcuzRowDTO = (DLcuzRowDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dLcuzRowDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DLcuzRowDTO{" +
            "id=" + getId() +
            ", vidZp='" + getVidZp() + "'" +
            ", nacBel='" + getNacBel() + "'" +
            ", nacPasp=" + getNacPasp() +
            ", paspVal='" + getPaspVal() + "'" +
            ", graj='" + getGraj() + "'" +
            ", famil='" + getFamil() + "'" +
            ", imena='" + getImena() + "'" +
            ", datRaj='" + getDatRaj() + "'" +
            ", pol='" + getPol() + "'" +
            ", datIzd='" + getDatIzd() + "'" +
            "}";
    }
}
