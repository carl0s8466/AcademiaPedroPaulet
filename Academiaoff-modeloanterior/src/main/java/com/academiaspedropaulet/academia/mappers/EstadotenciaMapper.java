package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.EstadotenciaDTO;
import com.academiaspedropaulet.academia.modelo.Estadotencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadotenciaMapper extends GenericMapper<EstadotenciaDTO, Estadotencia> {
}
