package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.EstudianteDTO;
import com.academiaspedropaulet.academia.dtos.GrupoDTO;
import com.academiaspedropaulet.academia.modelo.Estudiante;
import com.academiaspedropaulet.academia.modelo.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GrupoMapper extends GenericMapper<GrupoDTO, Grupo> {

    @Mapping(target = "seccion", ignore = true)
    Grupo toEntityFromCADTO(GrupoDTO.GrupoCADto grupoCrearDTO);
}
