package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.TrabajadorDTO;
import com.academiaspedropaulet.academia.modelo.Trabajador;

public interface TrabajadorService extends ICrudGenericoService <Trabajador, Long> {
    TrabajadorDTO saveD(TrabajadorDTO.TrabajadorCADto dto);

    TrabajadorDTO updateD(TrabajadorDTO.TrabajadorCADto dto, Long id);
}
