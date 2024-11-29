package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.ComunicadoDTO;
import com.academiaspedropaulet.academia.modelo.Comunicado;

public interface ComunicadoService extends ICrudGenericoService <Comunicado, Long>{
    ComunicadoDTO saveD(ComunicadoDTO.ComunicadoCADto dto);

    ComunicadoDTO updateD(ComunicadoDTO.ComunicadoCADto dto, Long id);
}
