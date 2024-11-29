package com.academiaspedropaulet.academia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatriculaDTO {

    private Long idMatricula;
    @NotNull(message = "El campo 'institucion de Estudio' no puede ser nulo")
    private String ieEstudio;

    @NotNull(message = "El campo 'a que escuela postula' no puede ser nulo")
    private String escuelaPostula;

    @NotNull(message = "El campo 'ante pato psico' no puede ser nulo")
    private String antePatoPsico;

    @NotNull(message = "El campo 'ante poli judi' no puede ser nulo")
    private String antePoliJudi;

    @NotNull(message = "La declaración jurada no puede ser nula")
    private String declaracionJurada;

    @NotNull(message = "La dirección no puede ser nula")
    private String direccion;

    @NotNull(message = "El campo 'familiar militar policial' no puede ser nulo")
    private String familiarMilitarPolicial;

    @NotNull(message = "La fecha de incorporación no puede ser nula")
    private String fechaIncorporacion;

    @NotNull(message = "El lugar natural no puede ser nulo")
    private String lugarNatural;

    @NotNull(message = "El campo 'natacion' no puede ser nulo")
    private String natacion;

    @NotNull(message = "El campo 'otros' no puede ser nulo")
    private String otros;

    @NotNull(message = "El peso no puede ser nulo")
    private Double peso;

    @NotNull(message = "La talla no puede ser nula")
    private Double talla;

    @NotNull(message = "El id del estudiante no puede ser nulo")
    private EstudianteDTO estudiante;

    @NotNull(message = "El id del apoderado no puede ser nulo")
    private ApoderadoDTO apoderado;

    @NotNull(message = "El id del medio de interés no puede ser nulo")
    private MedioDTO medio;

    @NotNull(message = "El id del plan académico no puede ser nulo")
    private PlanDTO plan;

    @NotNull(message = "El id de la institución no puede ser nulo")
    private InstitucionDTO institucion;

    @NotNull(message = "El id del grupo no puede ser nulo")
    private GrupoDTO grupo;

    // Record anidado para transmitir solo el ID de los elementos relacionados
    public record MatriculaCADto(
            @NotNull(message = "El id de la matrícula no puede ser nulo")
            Long idMatricula,

            String ieEstudio,

            @NotNull(message = "El campo 'a que escuela postula' no puede ser nulo")
            String escuelaPostula,

            @NotNull(message = "El campo 'ante pato psico' no puede ser nulo")
            String antePatoPsico,

            @NotNull(message = "El campo 'ante poli judi' no puede ser nulo")
            String antePoliJudi,

            @NotNull(message = "La declaración jurada no puede ser nula")
            String declaracionJurada,

            @NotNull(message = "La dirección no puede ser nula")
            String direccion,

            @NotNull(message = "El campo 'familiar militar policial' no puede ser nulo")
            String familiarMilitarPolicial,

            @NotNull(message = "La fecha de incorporación no puede ser nula")
            String fechaIncorporacion,

            @NotNull(message = "El lugar natural no puede ser nulo")
            String lugarNatural,

            @NotNull(message = "El campo 'natacion' no puede ser nulo")
            String natacion,

            @NotNull(message = "El campo 'otros' no puede ser nulo")
            String otros,

            @NotNull(message = "El peso no puede ser nulo")
            Double peso,

            @NotNull(message = "La talla no puede ser nula")
            Double talla,

            @NotNull(message = "El id del estudiante no puede ser nulo")
            Long estudiante,

            @NotNull(message = "El id del apoderado no puede ser nulo")
            Long apoderado,

            @NotNull(message = "El id del medio de interés no puede ser nulo")
            Long medio,

            @NotNull(message = "El id del plan académico no puede ser nulo")
            Long plan,

            @NotNull(message = "El id de la institución no puede ser nulo")
            Long institucion,

            @NotNull(message = "El id del grupo no puede ser nulo")
            Long grupo
    ) {
    }
}
