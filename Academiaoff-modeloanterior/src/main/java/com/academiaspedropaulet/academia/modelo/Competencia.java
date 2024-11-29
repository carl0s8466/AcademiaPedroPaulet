package com.academiaspedropaulet.academia.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "academia_competencia")

public class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competencia")
    private Long idCompetencia;

    @Column(name = "nombreCompetencia", nullable = false, length = 50)
    private String nombreCompetencia;

    @Column(name = "subpromedio", nullable = false)
    private Double subpromedio;


    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CURSO_COMPETENCIA") )
    private Curso curso;

}
