package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Fingers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FingersJaxbMapper extends JaxbMapper<com.visa.apply.xml.Fingers, Fingers> {}
