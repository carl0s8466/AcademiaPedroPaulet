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
@Table(name = "academia_institucion")
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitucion;

    @Column(name = "lugar", nullable = false, length = 100)
    private String lugar;

    @Column(name = "urlImagen")
    private String urlImagen;

    @Column(name = "mision", nullable = false, length = 500)
    private String mision;

    @Column(name = "vicion", nullable = false, length = 500)
    private String vicion;


    @Column(name = "nombreInstitucion", nullable = false, length = 45)
    private String nombreInstitucion;


    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede",
            nullable = false, foreignKey = @ForeignKey(name = "FK_SEDE_INSTITUCION"))
    private Sede sede;
}
