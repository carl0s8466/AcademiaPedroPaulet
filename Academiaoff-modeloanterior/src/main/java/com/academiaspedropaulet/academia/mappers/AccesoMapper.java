package com.academiaspedropaulet.academia.mappers;

import org.mapstruct.Mapper;
import com.academiaspedropaulet.academia.dtos.AccesoDTO;
import com.academiaspedropaulet.academia.modelo.Acceso;

@Mapper(componentModel = "spring")
public interface AccesoMapper extends GenericMapper<AccesoDTO, Acceso> {
}