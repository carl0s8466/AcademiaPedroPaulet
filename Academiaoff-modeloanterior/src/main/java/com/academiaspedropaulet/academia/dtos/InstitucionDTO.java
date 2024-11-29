package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstitucionDTO {

    private Long idInstitucion;

    @NotNull(message = "El lugar de la institución no puede ser nulo")
    private String lugar;
    @NotNull(message = "La url de la Imagen no puede ser nula")
    private String urlImagen;

    @NotNull(message = "La misión de la institución no puede ser nula")
    private String mision;

    @NotNull(message = "La visión de la institución no puede ser nula")
    private String vicion;

    @NotNull(message = "El nombre de la institución no puede ser nulo")
    private String nombreInstitucion;

    @NotNull(message = "La sede de la institución no puede ser nula")
    private SedeDTO sede;

    // Record anidado para transmitir solo el ID de la sede
    public record InstitucionCADto(

            Long idInstitucion,

            @NotNull(message = "El lugar de la institución no puede ser nulo")
            String lugar,
            @NotNull(message = "La url de la Imagen no puede ser nula")
            String urlImagen,

            @NotNull(message = "La misión de la institución no puede ser nula")
            String mision,
            @NotNull(message = "La visión de la institución no puede ser nula")
            String vicion,
            @NotNull(message = "El nombre de la institución no puede ser nulo")
            String nombreInstitucion,



            @NotNull(message = "El id de la sede no puede ser nulo")
            Long sede
    ) {
    }
}
