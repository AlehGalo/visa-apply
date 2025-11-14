package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Basta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BastaJaxbMapper extends JaxbMapper<com.visa.apply.xml.Basta, Basta> {}
