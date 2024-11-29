package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.SeccionDTO;
import com.academiaspedropaulet.academia.modelo.Seccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeccionMapper extends GenericMapper<SeccionDTO, Seccion> {

    @Mapping(target = "nivel", ignore = true)

    Seccion toEntityFromCADTO(SeccionDTO.SeccionCADto seccionCrearDTO);
}
