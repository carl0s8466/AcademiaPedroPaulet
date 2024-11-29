package com.academiaspedropaulet.academia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccesoDTO {
    private Long idAcceso;
    private String nombre;
    private String url;
    private String icono;
}