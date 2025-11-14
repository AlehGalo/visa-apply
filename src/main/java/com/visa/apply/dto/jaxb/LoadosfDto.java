package com.visa.apply.dto.jaxb;

import com.visa.apply.dto.DomakinDto;
import com.visa.apply.dto.EurodaDto;
import com.visa.apply.dto.FingersDto;
import com.visa.apply.dto.ImagesDto;
import com.visa.apply.dto.LcdopDto;
import com.visa.apply.dto.LcuzDto;
import com.visa.apply.dto.MaikaDto;
import com.visa.apply.dto.MolbaDto;
import com.visa.apply.dto.MsgheaderDto;
import com.visa.apply.dto.SaprugaDto;
import com.visa.apply.dto.VoitDto;
import com.visa.apply.entity.Basta;
import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Loadosf}
 */
public record LoadosfDto(
    Long id,
    MsgheaderDto dMsgheader,
    LcuzDto dLcuz,
    LcdopDto dLcdop,
    Basta dBasta,
    MaikaDto dMaika,
    SaprugaDto dSapruga,
    MolbaDto dMolba,
    DomakinDto dDomakin,
    EurodaDto dEuroda,
    VoitDto dVoit,
    ImagesDto dImages,
    FingersDto dFingers,
    Float version
)
    implements Serializable {}
