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
@Table(name = "Academia_comunicado")
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunicado")
    private Long idComunicado;

    @Column(name = "asunto", nullable = false, length = 120)
    private String asunto;

    @Column(name = "descripcion", nullable = false, length = 120)
    private String descripcion;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion",
            nullable = false, foreignKey = @ForeignKey(name = "FK_INSTITUCION_COMUNICADO"))
    private Institucion institucion;
}