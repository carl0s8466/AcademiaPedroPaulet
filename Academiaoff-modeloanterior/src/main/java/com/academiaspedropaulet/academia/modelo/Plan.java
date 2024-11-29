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
@Table(name = "academia_plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "nombrePlan", nullable = false, length = 20)
    private String nombrePlan;

    @ManyToOne
    @JoinColumn(name = "id_carga", referencedColumnName = "id_carga",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CARGA_PLAN") )
    private Carga carga;
}
