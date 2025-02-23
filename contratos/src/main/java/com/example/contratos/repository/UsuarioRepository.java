package com.example.contratos.repository;

import com.example.contratos.model.RoleEnum;
import com.example.contratos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEmail(String email);
    List<Usuario> findByRole(RoleEnum role);
}
