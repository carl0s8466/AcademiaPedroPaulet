package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.PeriodoDTO;
import com.academiaspedropaulet.academia.modelo.Periodo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeriodoMapper extends GenericMapper<PeriodoDTO, Periodo> {

}
