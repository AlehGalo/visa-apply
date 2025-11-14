package com.visa.apply.dto.jaxb;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Lcdop}
 */
public record LcdopDto(
    Long id,
    String ldMrjdarj,
    String ldMrjnm,
    String ldMrjgraj,
    String ldZenen,
    String ldJitDarj,
    String ldJitNm,
    String ldJitUl,
    Long ldTel,
    String ldRabota,
    String ldProfkod,
    String ldProfesia,
    String ldSlDarj,
    String ldSlNm,
    String ldSlUl
)
    implements Serializable {}
