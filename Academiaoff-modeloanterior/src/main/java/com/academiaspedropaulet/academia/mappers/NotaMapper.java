package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.NotaDTO;
import com.academiaspedropaulet.academia.modelo.Nota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotaMapper extends GenericMapper<NotaDTO, Nota> {

    @Mapping(target = "competencia", ignore = true)

    Nota toEntityFromCADTO(NotaDTO.NotaCADto notaCrearDTO);
}
