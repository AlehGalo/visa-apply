package com.visa.apply.dto.jaxb;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.visa.apply.entity.Fingers}
 */
public record FingersDto(
    Long id,
    LocalDate fnDatreg,
    LocalDateTime fnDatvav,
    String fnUsera,
    Integer fnSid,
    Byte fnPnr,
    String fnVidmol,
    String fnDrugi,
    Short fnDeviceid,
    Short fnScanres,
    Short fnWidth,
    Short fnHeight,
    Byte fnPixeldepth,
    Byte fnCompressalgo,
    String fnFingerposition,
    Byte fnImagequality,
    String fnImage,
    Integer fnImglen,
    String fnNottakenreason
)
    implements Serializable {}
