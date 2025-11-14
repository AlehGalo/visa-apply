package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.xml.Lcuz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LcuzJaxbMapper extends JaxbMapper<com.visa.apply.xml.Lcuz, com.visa.apply.entity.Lcuz> {}
