package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Msgheader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MsgheaderJaxbMapper extends JaxbMapper<com.visa.apply.xml.MsgHeader, Msgheader> {}
