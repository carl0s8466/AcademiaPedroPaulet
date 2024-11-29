package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.CargaDTO;
import com.academiaspedropaulet.academia.modelo.Carga;

public interface CargaService extends ICrudGenericoService <Carga, Long> {
    CargaDTO saveD(CargaDTO.CargaCADto dto);

    CargaDTO updateD(CargaDTO.CargaCADto dto, Long id);
}
