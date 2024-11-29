package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.ComunicadoDTO;
import com.academiaspedropaulet.academia.dtos.EstudianteDTO;
import com.academiaspedropaulet.academia.modelo.Comunicado;
import com.academiaspedropaulet.academia.modelo.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudianteMapper extends GenericMapper<EstudianteDTO, Estudiante> {

}
