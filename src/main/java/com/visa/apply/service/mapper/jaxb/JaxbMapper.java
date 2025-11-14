package com.visa.apply.service.mapper.jaxb;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface JaxbMapper<D, E> {
    E toEntity(D source);

    D toJaxbDto(E source);

    List<E> toEntity(List<D> dtoList);

    List<D> toJaxbDto(List<E> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D source);
}
