package com.example.contratos.service;

import com.example.contratos.dto.UsuarioRequestDTO;
import com.example.contratos.dto.UsuarioResponseDTO;
import com.example.contratos.model.RoleEnum;
import com.example.contratos.model.Usuario;
import com.example.contratos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(RoleEnum.valueOf(dto.getRole().toUpperCase()));

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(savedUsuario);
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return convertToResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizarPerfil(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setRole(RoleEnum.valueOf(usuarioDTO.getRole().toUpperCase()));

        Usuario atualizado = usuarioRepository.save(usuarioExistente);
        return convertToResponseDTO(atualizado);
    }

    public void alterarSenha(Long id, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> getUsuariosByRole(String role) {
        return usuarioRepository.findByRole(RoleEnum.valueOf(role))
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}
