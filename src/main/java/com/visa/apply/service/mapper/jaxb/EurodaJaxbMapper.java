package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Euroda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EurodaJaxbMapper extends JaxbMapper<com.visa.apply.xml.Euroda, Euroda> {}
