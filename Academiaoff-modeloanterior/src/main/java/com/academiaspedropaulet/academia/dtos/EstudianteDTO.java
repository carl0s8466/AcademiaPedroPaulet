package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstudianteDTO {

    private Long idEstudiante;
    @NotNull(message = "Los nombres no pueden ser nulos")
    private String nombre;
    @NotNull(message = "El apellido paterno no puede ser nulo")
    private String apellidoPaterno;
    @NotNull(message = "El apellido materno no puede ser nulo")
    private String apellidoMaterno;
    @NotNull(message = "El correo no puede ser nulo")
    private String correo;
    @NotNull(message = "El celular no puede ser nulo")
    private String celular;
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate fechanacimiento;

    @NotNull(message = "El usuario no puede ser nula")
    private String usuario;

}
