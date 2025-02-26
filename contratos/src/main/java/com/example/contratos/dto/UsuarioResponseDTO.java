package com.example.contratos.dto;

import com.example.contratos.model.RoleEnum;

public record UsuarioResponseDTO (Long id, String nome, String email, RoleEnum role){
}
