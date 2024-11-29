package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.AsistenciaDTO;
import com.academiaspedropaulet.academia.modelo.Asistencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper extends GenericMapper<AsistenciaDTO, Asistencia> {

    @Mapping(target = "estadotencia", ignore = true)
    @Mapping(target = "periodo", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "curso", ignore = true)


    Asistencia toEntityFromCADTO(AsistenciaDTO.AsistenciaCADto asistenciaCrearDTO);
}
