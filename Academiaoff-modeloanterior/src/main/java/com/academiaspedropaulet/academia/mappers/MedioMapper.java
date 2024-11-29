package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.MedioDTO;
import com.academiaspedropaulet.academia.modelo.Medio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedioMapper extends GenericMapper<MedioDTO, Medio> {
}
