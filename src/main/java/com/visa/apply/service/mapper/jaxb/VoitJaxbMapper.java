package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Voit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoitJaxbMapper extends JaxbMapper<com.visa.apply.xml.Voit, Voit> {}
