package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.GrupoDTO;
import com.academiaspedropaulet.academia.modelo.Grupo;

public interface GrupoService extends ICrudGenericoService <Grupo, Long> {
    GrupoDTO saveD(GrupoDTO.GrupoCADto dto);

    GrupoDTO updateD(GrupoDTO.GrupoCADto dto, Long id);
}
