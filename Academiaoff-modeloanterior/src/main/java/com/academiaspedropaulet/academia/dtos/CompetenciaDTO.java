package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompetenciaDTO {

    private Long idCompetencia;

    @NotNull(message = "El nombre de la competencia no puede ser nulo")
    private String nombreCompetencia;

    @NotNull(message = "El nombre del subpromedio no puede ser nulo")
    private Double subpromedio;

    @NotNull(message = "El curso no puede ser nulo")
    private CursoDTO curso;

    public record CompetenciaCADto(
            Long idCompetencia,
            @NotNull(message = "El nombre de la competencia no puede ser nulo")
            String nombreCompetencia,
            @NotNull(message = "El nombre del subpromedio no puede ser nulo")
            Double subpromedio,
            @NotNull(message = "El curso no puede ser nulo")
            Long curso
    ) {
    }
}
