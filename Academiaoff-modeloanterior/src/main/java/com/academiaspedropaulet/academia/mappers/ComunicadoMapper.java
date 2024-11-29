package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.CompetenciaDTO;
import com.academiaspedropaulet.academia.dtos.ComunicadoDTO;
import com.academiaspedropaulet.academia.modelo.Competencia;
import com.academiaspedropaulet.academia.modelo.Comunicado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComunicadoMapper extends GenericMapper<ComunicadoDTO, Comunicado> {

    @Mapping(target = "institucion", ignore = true)
    Comunicado toEntityFromCADTO(ComunicadoDTO.ComunicadoCADto comunicadoCrearDTO);
}
