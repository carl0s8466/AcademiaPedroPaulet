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
@Table(name = "academia_carga")

public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga")
    private Long idCarga;


    @ManyToOne
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo",
            nullable = false, foreignKey = @ForeignKey(name = "FK_PERIODO_CARGA") )
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "id_trabajador", referencedColumnName = "id_trabajador",
            nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJADOR_CARGA") )
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CURSO_CARGA") )
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion",
            nullable = false, foreignKey = @ForeignKey(name = "FK_SECCION_CARGA") )
    private Seccion seccion;

}
