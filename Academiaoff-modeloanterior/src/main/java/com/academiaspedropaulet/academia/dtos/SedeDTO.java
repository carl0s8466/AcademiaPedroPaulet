package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SedeDTO {

    private Long idSede;

    @NotNull(message = "El nombre de la sede no puede ser nulo")
    private String nombreSede;
}