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
@Table(name = "academia_matricula")

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    @Column(name = "ieEstudio", nullable = false, length = 100)
    private String ieEstudio;

    @Column(name = "escuelaPostula", nullable = false, length = 100)
    private String escuelaPostula;

    @Column(name = "antePatoPsico", nullable = false, length = 100)
    private String antePatoPsico;

    @Column(name = "antePoliJudi", nullable = false, length = 100)
    private String antePoliJudi;

    @Column(name = "declaracionJurada", nullable = false, length = 20)
    private String declaracionJurada;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "familiarMilitarPolicial", nullable = false, length = 50)
    private String familiarMilitarPolicial;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaIncorporacion", nullable = false, length = 100)
    private LocalDate fechaIncorporacion;

    @Column(name = "lugarNatural", nullable = false, length = 100)
    private String lugarNatural;

    @Column(name = "natacion", nullable = false, length = 2)
    private String natacion;

    @Column(name = "otros", nullable = false, length = 50)
    private String otros;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "talla", nullable = false)
    private Double talla;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante",
            nullable = false, foreignKey = @ForeignKey(name = "FK_ESTUDIANTE_MATRICULA") )
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_apoderado", referencedColumnName = "id_apoderado",
            nullable = false, foreignKey = @ForeignKey(name = "FK_APODERADO_MATRICULA") )
    private Apoderado apoderado;

    @ManyToOne
    @JoinColumn(name = "id_medio", referencedColumnName = "id_medio",
            nullable = false, foreignKey = @ForeignKey(name = "FK_MEDIO_MATRICULA") )
    private Medio medio;

    @ManyToOne
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan",
            nullable = false, foreignKey = @ForeignKey(name = "FK_PLANACADEMICO_MATRICULA") )
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion",
            nullable = false, foreignKey = @ForeignKey(name = "FK_INSTITUCION_MATRICULA") )
    private Institucion institucion;

    @ManyToOne
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo",
            nullable = false, foreignKey = @ForeignKey(name = "FK_GRUPo_MATRICULA") )
    private Grupo grupo;
}
