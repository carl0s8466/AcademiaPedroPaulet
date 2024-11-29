package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CargaDTO {

    private Long idCarga;

    @NotNull(message = "El periodo académico no puede ser nulo")
    private PeriodoDTO periodo;

    @NotNull(message = "El trabajador académico no puede ser nulo")
    private TrabajadorDTO trabajador;

    @NotNull(message = "El curso de academia no puede ser nulo")
    private CursoDTO curso;

    @NotNull(message = "La sección académica no puede ser nula")
    private SeccionDTO seccion;

    public record CargaCADto(
            Long idCarga,
            @NotNull(message = "El periodo académico no puede ser nulo")
            Long periodo,
            @NotNull(message = "El trabajador académico no puede ser nulo")
            Long trabajador,
            @NotNull(message = "El curso de academia no puede ser nulo")
            Long curso,
            @NotNull(message = "La sección académica no puede ser nula")
            Long seccion
    ) {
    }
}
