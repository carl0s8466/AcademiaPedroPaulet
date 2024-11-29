package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.TrabajadorDTO;
import com.academiaspedropaulet.academia.modelo.Trabajador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrabajadorMapper extends GenericMapper<TrabajadorDTO, Trabajador> {
    @Mapping(target = "institucion", ignore = true)

    Trabajador toEntityFromCADTO(TrabajadorDTO.TrabajadorCADto trabajadorCrearDTO);
}
