package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrabajadorDTO {

    private Long idTrabajador;

    @NotNull(message = "El nombre completo no puede ser nulo")
    private String nombreCompleto;

    @NotNull(message = "El apellido paterno no puede ser nulo")
    private String apellidoPaterno;

    @NotNull(message = "El apellido materno no puede ser nulo")
    private String apellidoMaterno;

    @NotNull(message = "El celular no puede ser nulo")
    private String celular;

    @NotNull(message = "El correo no puede ser nulo")
    private String correo;



    @NotNull(message = "El id de la institución no puede ser nulo")
    private InstitucionDTO institucion;

    @NotNull(message = "El usuario no puede ser nula")
    private String usuario;

    // Record anidado para transmitir solo el ID de la institución y persona
    public record TrabajadorCADto(
            Long idtrabajador,

            @NotNull(message = "El nombre completo no puede ser nulo")
            String nombreCompleto,

            @NotNull(message = "El apellido paterno no puede ser nulo")
            String apellidoPaterno,

            @NotNull(message = "El apellido materno no puede ser nulo")
            String apellidoMaterno,

            @NotNull(message = "El celular no puede ser nulo")
            String celular,

            @NotNull(message = "El correo no puede ser nulo")
            String correo,


            @NotNull(message = "El id de la institución no puede ser nulo")
            Long institucion,

            @NotNull(message = "El id de la persona no puede ser nulo")
            String usuario
    ) {
    }
}
