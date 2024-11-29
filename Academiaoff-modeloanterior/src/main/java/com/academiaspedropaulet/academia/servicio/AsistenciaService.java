package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.AsistenciaDTO;
import com.academiaspedropaulet.academia.modelo.Asistencia;

public interface AsistenciaService extends ICrudGenericoService <Asistencia, Long>{
    AsistenciaDTO saveD(AsistenciaDTO.AsistenciaCADto dto);

    AsistenciaDTO updateD(AsistenciaDTO.AsistenciaCADto dto, Long id);
}
