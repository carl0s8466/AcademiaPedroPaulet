package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeccionDTO {

    private Long idSeccion;

    @NotNull(message = "El nombre de la sección no puede ser nulo")
    private String nombre;

    @NotNull(message = "El id del nivel no puede ser nulo")
    private NivelDTO nivel;

    public record SeccionCADto(
            @NotNull(message = "El id de la sección no puede ser nulo")
            Long idSeccion,

            @NotNull(message = "El nombre de la sección no puede ser nulo")
            String nombre,

            @NotNull(message = "El id del nivel no puede ser nulo")
            Long nivel
    ) {
    }
}
