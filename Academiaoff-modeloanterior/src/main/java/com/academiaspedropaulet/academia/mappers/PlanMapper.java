package com.academiaspedropaulet.academia.mappers;

import com.academiaspedropaulet.academia.dtos.PlanDTO;
import com.academiaspedropaulet.academia.modelo.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanMapper extends GenericMapper<PlanDTO, Plan> {

    @Mapping(target = "carga", ignore = true)

    Plan toEntityFromCADTO(PlanDTO.PlanCADto planCrearDTO);
}
