package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Loadosf;
import com.visa.apply.xml.DLoadosf;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {
        BastaJaxbMapper.class,
        DomakinJaxbMapper.class,
        EurodaJaxbMapper.class,
        FingersJaxbMapper.class,
        ImagesJaxbMapper.class,
        LcdopJaxbMapper.class,
        LcuzJaxbMapper.class,
        MaikaJaxbMapper.class,
        MsgheaderJaxbMapper.class,
        SaprugaJaxbMapper.class,
        VoitJaxbMapper.class,
        MolbaJaxbMapper.class,
    }
)
public interface LoadosfJaxbMapper extends JaxbMapper<DLoadosf, Loadosf> {}
