package com.visa.apply.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.visa.apply.entity.Molba}
 */
public record MolbaDto(Long id, LocalDate datVli, LocalDate datIzl, String vidvis, byte brvl, String vidus,
                       String valvis, byte brdni, byte cel, LocalDateTime molDatVav, String gratis, String imavisa,
                       byte cenamol, String cenacurr, String maindest, String maindestnm, String gkppDarj,
                       String gkppText, String textIni) implements Serializable {
}