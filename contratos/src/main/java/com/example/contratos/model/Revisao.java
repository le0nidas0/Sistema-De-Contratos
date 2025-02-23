package com.example.contratos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "revisoes")
@Entity(name = "revisoes")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Revisao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataRevisao;

    @Enumerated(EnumType.STRING)
    private StatusRevisao status;

    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @ManyToOne
    private Usuario revisor;

}
