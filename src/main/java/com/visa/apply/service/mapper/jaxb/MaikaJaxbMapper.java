package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Maika;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaikaJaxbMapper extends JaxbMapper<com.visa.apply.xml.Maika, Maika> {}
