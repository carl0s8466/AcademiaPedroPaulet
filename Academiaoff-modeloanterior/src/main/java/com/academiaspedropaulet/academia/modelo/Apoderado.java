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
@Table(name = "academia_apoderado")

public class Apoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apoderado")
    private Long idApoderado;

    @Column(name = "nombrecompleto", nullable = false, length = 50)
    private String nombrecompleto;

    @Column(name = "apellidopaterno", nullable = false, length = 50)
    private String apellidopaterno;

    @Column(name = "apellidomaterno", nullable = false, length = 50)
    private String apellidomaterno;

    @Column(name = "celular", nullable = false, length = 9)
    private String celular;

    @Column(name = "celularrespaldo", nullable = false, length = 9)
    private String celularrespaldo;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

}
