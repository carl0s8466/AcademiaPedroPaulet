package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.MatriculaDTO;
import com.academiaspedropaulet.academia.modelo.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper extends GenericMapper<MatriculaDTO, Matricula> {
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "apoderado", ignore = true)
    @Mapping(target = "medio", ignore = true)
    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "institucion", ignore = true)
    @Mapping(target = "grupo", ignore = true)

    Matricula toEntityFromCADTO(MatriculaDTO.MatriculaCADto matriculaCrearDTO);
}
