package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.LogroDTO;
import com.academiaspedropaulet.academia.modelo.Logro;

public interface LogroService extends ICrudGenericoService <Logro, Long> {
    LogroDTO saveD(LogroDTO.LogroCADto dto);

    LogroDTO updateD(LogroDTO.LogroCADto dto, Long id);
}
