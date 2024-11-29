package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.InstitucionDTO;
import com.academiaspedropaulet.academia.modelo.Institucion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstitucionMapper extends GenericMapper<InstitucionDTO, Institucion> {
    @Mapping(target = "sede", ignore = true)
    Institucion toEntityFromCADTO(InstitucionDTO.InstitucionCADto institucionCrearDTO);
}
