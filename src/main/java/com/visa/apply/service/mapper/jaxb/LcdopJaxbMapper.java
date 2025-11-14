package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Lcdop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LcdopJaxbMapper extends JaxbMapper<com.visa.apply.xml.Lcdop, Lcdop> {}
