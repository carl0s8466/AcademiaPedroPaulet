package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.InstitucionDTO;
import com.academiaspedropaulet.academia.modelo.Institucion;

public interface InstitucionService extends ICrudGenericoService <Institucion, Long>{
    InstitucionDTO saveD(InstitucionDTO.InstitucionCADto dto);

    InstitucionDTO updateD(InstitucionDTO.InstitucionCADto dto, Long id);
}
