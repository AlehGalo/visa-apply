package com.visa.apply.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.visa.apply.entity.Lcuz}
 */
public record LcuzDto(Long id, String vidZp, String nacBel, int nacPasp, LocalDate paspVal, String graj, String famil,
                      String imena, String datRaj, String pol, LocalDate datIzd) implements Serializable {
}