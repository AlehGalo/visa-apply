package com.visa.apply.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Maika}
 */
public record MaikaDto(Long id, String dcFamil, String dcImena, String dcPol, String dcDarj, String dcGrad,
                       String dcUlica) implements Serializable {
}