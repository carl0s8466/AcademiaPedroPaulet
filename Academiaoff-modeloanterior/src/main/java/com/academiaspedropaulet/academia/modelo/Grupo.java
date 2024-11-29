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
@Table(name = "academia_grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idGrupo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cupos", nullable = false)
    private int cupos;

    @ManyToOne
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion",
            nullable = false, foreignKey = @ForeignKey(name = "FK_SECCION_GRUPO"))
    private Seccion seccion;
}
