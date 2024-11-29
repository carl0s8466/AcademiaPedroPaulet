package com.academiaspedropaulet.academia.mappers;


import com.academiaspedropaulet.academia.dtos.InsidenciaDTO;
import com.academiaspedropaulet.academia.modelo.Insidencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsidenciaMapper extends GenericMapper<InsidenciaDTO, Insidencia> {
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    Insidencia toEntityFromCADTO(InsidenciaDTO.InsidenciaCADto insidenciaCrearDTO);

}
