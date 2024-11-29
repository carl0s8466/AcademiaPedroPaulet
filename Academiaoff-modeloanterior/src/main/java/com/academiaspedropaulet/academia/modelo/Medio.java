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
@Table(name = "academia_medio")
public class Medio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio")
    private Long idMedio;

    @Column(name = "nombreMedio", nullable = false, length = 100)
    private String nombreMedio;
}