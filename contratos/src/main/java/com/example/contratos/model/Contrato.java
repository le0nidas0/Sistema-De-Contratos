package com.example.contratos.model;


import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Table(name = "contratos")
@Entity(name = "contratos")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contrato {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String titulo;
    private String empresaContratante;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    @Enumerated(EnumType.STRING)
    private StatusContrato status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario responsavel;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;


    public Contrato(Object o, String nome, String descricao, BigDecimal valor) {
    }
}
