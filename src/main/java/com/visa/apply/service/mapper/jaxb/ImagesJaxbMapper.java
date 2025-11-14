package com.visa.apply.service.mapper.jaxb;

import com.visa.apply.entity.Images;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagesJaxbMapper extends JaxbMapper<com.visa.apply.xml.Images, Images> {}
