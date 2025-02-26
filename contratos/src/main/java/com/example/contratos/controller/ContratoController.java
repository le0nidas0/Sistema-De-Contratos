package com.example.contratos.controller;

import com.example.contratos.dto.ContratoRequestDTO;
import com.example.contratos.dto.ContratoResponseDTO;
import com.example.contratos.service.ContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @PostMapping
    public ResponseEntity<ContratoResponseDTO> createContrato(@RequestBody ContratoRequestDTO contratoRequestDTO) {
        return ResponseEntity.ok(contratoService.criarContrato(contratoRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> buscarContratoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contratoService.buscarContratoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponseDTO>> listarContratos() {
        return ResponseEntity.ok(contratoService.listarContratos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> atualizarContrato(@PathVariable Long id, @RequestBody ContratoRequestDTO contratoRequestDTO) {
        return ResponseEntity.ok(contratoService.atualizarContrato(id, contratoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        contratoService.deletarContrato(id);
        return ResponseEntity.noContent().build();
    }
}
