package com.visa.apply.dto.jaxb;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Domakin}
 */
public record DomakinDto(
    Long id,
    String dmVid,
    String nomPok,
    String domGraj,
    String domFamil,
    String domIme,
    String domDarj,
    Short domNm,
    String domAdres,
    String vedDarj,
    String vedNm
)
    implements Serializable {}
