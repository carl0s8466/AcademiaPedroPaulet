package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NivelDTO {

    private Long idNivel;

    @NotNull(message = "El nombre del nivel no puede ser nulo")
    private String nombre;
}
