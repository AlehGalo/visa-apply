package com.visa.apply.dto.jaxb;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Voit}
 */
public record VoitDto(Long id, String vnom, String vime, String bgime, String bgadres) implements Serializable {}
