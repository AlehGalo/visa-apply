package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Molba;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MolbaJaxbMapper extends JaxbMapper<com.visa.apply.xml.Molba, Molba> {}
