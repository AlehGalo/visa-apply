package com.visa.apply.dto.jaxb;

import com.visa.apply.entity.Basta;
import java.io.Serializable;

/**
 * DTO for {@link Basta}
 */
public record BastaEntityDto(Long id, String dcFamil, String dcImena, String dcPol, String dcGrad, String dcUlica)
    implements Serializable {}
