package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.SedeDTO;
import com.academiaspedropaulet.academia.modelo.Sede;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SedeMapper extends GenericMapper<SedeDTO, Sede> {
}
