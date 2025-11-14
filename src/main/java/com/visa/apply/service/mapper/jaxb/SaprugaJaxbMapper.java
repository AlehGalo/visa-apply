package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Sapruga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaprugaJaxbMapper extends JaxbMapper<com.visa.apply.xml.Sapruga, Sapruga> {}
