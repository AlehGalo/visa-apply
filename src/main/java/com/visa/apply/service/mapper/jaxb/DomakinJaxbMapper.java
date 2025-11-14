package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Domakin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomakinJaxbMapper extends JaxbMapper<com.visa.apply.xml.Domakin, Domakin> {}
