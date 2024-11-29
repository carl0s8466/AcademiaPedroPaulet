package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.InsidenciaDTO;
import com.academiaspedropaulet.academia.modelo.Insidencia;

public interface InsidenciaService extends ICrudGenericoService <Insidencia, Long>{
    InsidenciaDTO saveD(InsidenciaDTO.InsidenciaCADto dto);

    InsidenciaDTO updateD(InsidenciaDTO.InsidenciaCADto dto, Long id);
}
