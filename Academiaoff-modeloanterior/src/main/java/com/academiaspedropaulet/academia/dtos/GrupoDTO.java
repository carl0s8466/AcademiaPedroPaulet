package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupoDTO {

    private Long idGrupo;

    @NotNull(message = "El nombre del grupo no puede ser nulo")
    private String nombre;

    @NotNull(message = "Los cupos no pueden ser nulos")
    private Integer cupos;

    @NotNull(message = "La sección asociada no puede ser nula")
    private SeccionDTO seccion;

    // Record anidado para transmitir solo el ID de la sección
    public record GrupoCADto(
            Long idGrupos,

            @NotNull(message = "El nombre del grupo no puede ser nulo")
            String nombre,

            @NotNull(message = "Los cupos no pueden ser nulos")
            Integer cupos,

            @NotNull(message = "El id de la sección no puede ser nulo")
            Long seccion
    ) {
    }
}
