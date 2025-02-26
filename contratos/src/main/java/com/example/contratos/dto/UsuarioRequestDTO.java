package com.example.contratos.dto;

import com.example.contratos.model.RoleEnum;

public record UsuarioRequestDTO (String nome, String email, String senha, RoleEnum role){
}
