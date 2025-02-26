package com.example.contratos.controller;

import com.example.contratos.dto.UsuarioRequestDTO;
import com.example.contratos.dto.UsuarioResponseDTO;
import com.example.contratos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuarioRequestDTO));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarPerfil(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.atualizarPerfil(id, usuarioRequestDTO));
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        usuarioService.alterarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuariosPorRole(@PathVariable String role) {
        return ResponseEntity.ok(usuarioService.getUsuariosByRole(role));
    }
}
