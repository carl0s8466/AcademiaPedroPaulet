package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.CompetenciaDTO;
import com.academiaspedropaulet.academia.modelo.Competencia;

public interface CompetenciaService extends ICrudGenericoService <Competencia, Long>{
    CompetenciaDTO saveD(CompetenciaDTO.CompetenciaCADto dto);

    CompetenciaDTO updateD(CompetenciaDTO.CompetenciaCADto dto, Long id);
}
