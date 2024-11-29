package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.NotaDTO;
import com.academiaspedropaulet.academia.modelo.Nota;

public interface NotaService extends ICrudGenericoService <Nota, Long> {
    NotaDTO saveD(NotaDTO.NotaCADto dto);

    NotaDTO updateD(NotaDTO.NotaCADto dto, Long id);
}
