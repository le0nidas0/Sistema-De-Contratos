package com.example.contratos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "documentos")
@Entity(name = "documentos")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String caminhoArquivo;
    private LocalDate dataUpload;
    private int versao;

    @ManyToOne
    private Contrato contrato;
}
