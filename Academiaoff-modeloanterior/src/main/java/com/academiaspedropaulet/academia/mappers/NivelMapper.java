package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.NivelDTO;
import com.academiaspedropaulet.academia.modelo.Nivel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NivelMapper extends GenericMapper<NivelDTO, Nivel> {
}
