package com.academiaspedropaulet.academia.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "academia_asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long idAsistencia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecharegistro", nullable = false)
    private LocalDate fecharegistro;


    @ManyToOne
    @JoinColumn(name = "id_estadotencia", referencedColumnName = "id_estadotencia",
            nullable = false, foreignKey = @ForeignKey(name = "FK_ESTADOTENCIA_ASISTENCIA") )
    private Estadotencia estadotencia;

    @ManyToOne
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo",
            nullable = false, foreignKey = @ForeignKey(name = "FK_PERIODO_ASISTENCIA") )
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante",
            nullable = false, foreignKey = @ForeignKey(name = "FK_ESTUDIANTE_ASISTENCIA") )
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CURSO_ASISTENCIA") )
    private Curso curso;

}
