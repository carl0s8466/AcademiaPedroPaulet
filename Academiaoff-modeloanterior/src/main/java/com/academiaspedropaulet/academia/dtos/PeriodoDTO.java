package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodoDTO {

    private Long idPeriodo;

    @NotNull(message = "El nombre del periodo no puede ser nulo")
    private String nombrePeriodo;}