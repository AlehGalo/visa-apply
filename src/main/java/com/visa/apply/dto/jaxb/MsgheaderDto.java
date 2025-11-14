package com.visa.apply.dto.jaxb;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Msgheader}
 */
public record MsgheaderDto(Long id, String mhKscreated, int mhRegnom, String mhVfsrefno, String mhUsera, String mhDatvav)
    implements Serializable {}
