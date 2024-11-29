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
@Table(name = "academia_trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
    private Long idTrabajador;

    @Column(name = "nombreCompleto", nullable = false, length = 50)
    private String nombreCompleto;

    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "celular", nullable = false, length = 9)
    private String celular;

    @Column(name = "correo", nullable = false, length = 120)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion",
            nullable = false, foreignKey = @ForeignKey(name = "FK_INSTITUCION_TRABAJADOR") )
    private Institucion institucion;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;


}
