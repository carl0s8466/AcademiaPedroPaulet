package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.MatriculaDTO;
import com.academiaspedropaulet.academia.modelo.Matricula;

public interface MatriculaService extends ICrudGenericoService <Matricula, Long> {
    MatriculaDTO saveD(MatriculaDTO.MatriculaCADto dto);

    MatriculaDTO updateD(MatriculaDTO.MatriculaCADto dto, Long id);
}
