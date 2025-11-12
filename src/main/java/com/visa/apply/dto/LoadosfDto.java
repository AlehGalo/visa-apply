package com.visa.apply.dto;

import com.visa.apply.entity.Basta;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Loadosf}
 */
public record LoadosfDto(Long id, MsgheaderDto dMsgheader, LcuzDto dLcuz, LcdopDto dLcdop, Basta dBasta,
                         MaikaDto dMaika, SaprugaDto dSapruga, MolbaDto dMolba, DomakinDto dDomakin, EurodaDto dEuroda,
                         VoitDto dVoit, ImagesDto dImages, FingersDto dFingers,
                         Float version) implements Serializable {
}