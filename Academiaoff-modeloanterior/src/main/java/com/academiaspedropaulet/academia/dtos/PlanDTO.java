package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanDTO {

    private Long idPlan;

    @NotNull(message = "El nombre de la carga académica no puede ser nulo")
    private String nombrePlan;

    @NotNull(message = "El id de la carga académica no puede ser nulo")
    private CargaDTO carga;

    public record PlanCADto(
            Long idPlan,

            @NotNull(message = "El nombre de la carga académica no puede ser nulo")
            String nombrePlan,

            @NotNull(message = "El id de la carga académica no puede ser nulo")
            Long carga){

    }

}
