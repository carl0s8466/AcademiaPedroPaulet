package com.academiaspedropaulet.academia.mappers;


import com.academiaspedropaulet.academia.dtos.CompetenciaDTO;
import com.academiaspedropaulet.academia.modelo.Competencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetenciaMapper extends GenericMapper<CompetenciaDTO, Competencia> {

    @Mapping(target = "curso", ignore = true)
    Competencia toEntityFromCADTO(CompetenciaDTO.CompetenciaCADto competenciaCrearDTO);
}
