package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.SeccionDTO;
import com.academiaspedropaulet.academia.modelo.Seccion;

public interface SeccionService extends ICrudGenericoService <Seccion, Long>{
    SeccionDTO saveD(SeccionDTO.SeccionCADto dto);

    SeccionDTO updateD(SeccionDTO.SeccionCADto dto, Long id);
}
