package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComunicadoDTO {

    private Long idComunicado;

    @NotNull(message = "El asunto no puede ser nulo")
    private String asunto;

    @NotNull(message = "La descripción no puede ser nula")
    private String descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "La institución no puede ser nula")
    private InstitucionDTO institucion;

    // Record anidado para transmitir solo los ID de las relaciones
    public record ComunicadoCADto(
            Long idComunicado,
            @NotNull(message = "El asunto no puede ser nulo")
            String asunto,
            @NotNull(message = "La descripción no puede ser nula")
            String descripcion,
            @NotNull(message = "La fecha no puede ser nula")
            LocalDate fecha,
            @NotNull(message = "La institución no puede ser nula")
            Long institucion
    ) {
    }
}
