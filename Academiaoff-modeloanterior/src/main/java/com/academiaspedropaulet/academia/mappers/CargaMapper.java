package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.AsistenciaDTO;
import com.academiaspedropaulet.academia.dtos.CargaDTO;
import com.academiaspedropaulet.academia.modelo.Asistencia;
import com.academiaspedropaulet.academia.modelo.Carga;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargaMapper extends GenericMapper<CargaDTO, Carga> {

    @Mapping(target = "periodo", ignore = true)
    @Mapping(target = "trabajador", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "seccion", ignore = true)

    Carga toEntityFromCADTO(CargaDTO.CargaCADto cargaCrearDTO);
}
