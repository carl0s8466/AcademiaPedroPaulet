package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotaDTO {

    private Long idNota;

    @NotNull(message = "La nota no puede ser nula")
    private Double nota;

    @NotNull(message = "El id de la competencia no puede ser nulo")
    private CompetenciaDTO competencia;

    public record NotaCADto(
            Long idNota,

            @NotNull(message = "La nota no puede ser nula")
            Double nota,

            @NotNull(message = "El id de la competencia no puede ser nulo")
            Long competencia
    ) {
    }
}
