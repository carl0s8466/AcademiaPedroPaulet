package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.LogroDTO;
import com.academiaspedropaulet.academia.modelo.Logro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogroMapper extends GenericMapper<LogroDTO, Logro> {
    @Mapping(target = "institucion", ignore = true)

    Logro toEntityFromCADTO(LogroDTO.LogroCADto logroCrearDTO);
}
