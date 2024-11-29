package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AsistenciaDTO {

    private Long idAsistencia;

    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fecharegistro;

    @NotNull(message = "El estado de asistencia no puede ser nulo")
    private EstadotenciaDTO estadotencia;

    @NotNull(message = "El periodo académico no puede ser nulo")
    private PeriodoDTO periodo;

    @NotNull(message = "El estudiante no puede ser nulo")
    private EstudianteDTO estudiante;

    @NotNull(message = "El curso de academia no puede ser nulo")
    private CursoDTO curso;

    public record AsistenciaCADto(
            Long idAsistencia,
            @NotNull(message = "La fecha de registro no puede ser nula")
            LocalDate fecharegistro,
            @NotNull(message = "El estado de asistencia no puede ser nulo")
            Long estadotencia,
            @NotNull(message = "El periodo académico no puede ser nulo")
            Long periodo,
            @NotNull(message = "El estudiante no puede ser nulo")
            Long estudiante,
            @NotNull(message = "El curso de academia no puede ser nulo")
            Long curso
    ) {
    }
}
