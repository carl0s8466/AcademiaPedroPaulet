package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsidenciaDTO {

    private Long idInsidencia;

    @NotNull(message = "El castigo no puede ser nulo")
    private String castigo;

    @NotNull(message = "La descripción no puede ser nula")
    private String descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha; // Se puede usar LocalDate si el proyecto lo admite.

    @NotNull(message = "El id del curso no puede ser nulo")
    private CursoDTO curso;

    @NotNull(message = "El id del estudiante no puede ser nulo")
    private EstudianteDTO estudiante;

    // Record anidado para transmitir solo datos básicos en ciertos contextos
    public record InsidenciaCADto(

            Long idInsidencia,

            @NotNull(message = "El castigo no puede ser nulo")
            String castigo,

            @NotNull(message = "La descripción no puede ser nula")
            String descripcion,

            @NotNull(message = "La fecha no puede ser nula")
            String fecha,

            @NotNull(message = "El id del curso no puede ser nulo")
            Long curso,

            @NotNull(message = "El id del estudiante no puede ser nulo")
            Long estudiante
    ) {
    }
}
