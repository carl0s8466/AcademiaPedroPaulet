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
@Table(name = "academia_nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Long idNota;

    @Column(name = "nota", nullable = false)
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "id_competencia", referencedColumnName = "id_competencia",
            nullable = false, foreignKey = @ForeignKey(name = "FK_COMPETENCIA_NOTA") )
    private Competencia competencia ;
}
