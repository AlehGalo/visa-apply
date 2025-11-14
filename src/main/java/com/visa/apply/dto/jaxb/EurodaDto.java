package com.visa.apply.dto.jaxb;

import com.visa.apply.entity.Euroda;
import java.io.Serializable;

/**
 * DTO for {@link Euroda}
 */
public record EurodaDto(Long id, String euFamil, String euImena, String euNacBel, String euRodstvo) implements Serializable {}
