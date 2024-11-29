package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogroDTO {

    private Long idLogro;

    @NotNull(message = "El título del logro no puede ser nulo")
    private String titulo;

    @NotNull(message = "La descripción del logro no puede ser nula")
    private String descripcion;

    @NotNull(message = "La fecha del logro no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "La url de la Imagen no puede ser nula")
    private String urlImagen;

    @NotNull(message = "El id de la institución no puede ser nulo")
    private InstitucionDTO institucion;

    // Record anidado para transmitir solo el ID de la institución
    public record LogroCADto(
            @NotNull(message = "El id del logro no puede ser nulo")
            Long idLogro,

            @NotNull(message = "El título del logro no puede ser nulo")
            String titulo,

            @NotNull(message = "La descripción del logro no puede ser nula")
            String descripcion,

            @NotNull(message = "La fecha del logro no puede ser nula")
            LocalDate fecha,

            @NotNull(message = "La url de la Imagen no puede ser nula")
            String urlImagen,

            @NotNull(message = "El id de la institución no puede ser nulo")
            Long institucion
    ) {
    }
}
