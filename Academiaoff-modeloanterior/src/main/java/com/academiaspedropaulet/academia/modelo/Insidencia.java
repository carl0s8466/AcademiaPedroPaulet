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
@Table(name = "academia_insidencias")
public class Insidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insidencia")
    private Long idInsidencia;

    @Column(name = "castigo", nullable = false, length = 50)
    private String castigo;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CURSO_INSIDENCIA"))
    private Curso curso;


    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante",
            nullable = false, foreignKey = @ForeignKey(name = "fk_ESTUDIANTE_INSIDENCIA"))
    private Estudiante estudiante;
}
