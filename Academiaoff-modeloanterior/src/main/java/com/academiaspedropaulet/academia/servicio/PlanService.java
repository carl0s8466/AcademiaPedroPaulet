package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.PlanDTO;
import com.academiaspedropaulet.academia.modelo.Plan;

public interface PlanService extends ICrudGenericoService <Plan, Long> {
    PlanDTO saveD(PlanDTO.PlanCADto dto);

    PlanDTO updateD(PlanDTO.PlanCADto dto, Long id);
}
