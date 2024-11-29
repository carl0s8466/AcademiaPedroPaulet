package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.ApoderadoDTO;
import com.academiaspedropaulet.academia.modelo.Apoderado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApoderadoMapper extends GenericMapper<ApoderadoDTO, Apoderado> {
}
