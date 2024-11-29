package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApoderadoDTO {

    private Long idApoderado;
    @NotNull(message = "El id de la nombreCompleto no puede ser nulo")
    private String nombrecompleto;
    @NotNull(message = "El id de la apellidoPaterno no puede ser nulo")
    private String apellidopaterno;
    @NotNull(message = "El id de la apellidoMaterno no puede ser nulo")
    private String apellidomaterno;
    @NotNull(message = "El id de la celular no puede ser nulo")
    private String celular;
    @NotNull(message = "El id de la celularRespaldo no puede ser nulo")
    private String celularrespaldo;
    @NotNull(message = "El id de la dni no puede ser nulo")
    private String dni;
    @NotNull(message = "El id de la correo no puede ser nulo")
    private String correo;
}
