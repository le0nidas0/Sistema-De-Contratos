package com.example.contratos.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
