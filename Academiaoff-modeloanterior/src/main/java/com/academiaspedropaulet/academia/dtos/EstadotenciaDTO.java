package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstadotenciaDTO {

    private Long idEstadoAsistencia;

    @NotNull(message = "El nombre del estado no puede ser nulo")
    private String nombre;
}