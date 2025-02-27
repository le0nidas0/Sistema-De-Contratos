package com.example.contratos.controller;

import com.example.contratos.model.Revisao;
import com.example.contratos.model.StatusRevisao;
import com.example.contratos.service.RevisaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revisoes")
public class RevisaoController {

    private final RevisaoService revisaoService;

    public RevisaoController(RevisaoService revisaoService) {
        this.revisaoService = revisaoService;
    }

    @PostMapping
    public ResponseEntity<Revisao> createRevisao(@RequestParam Long contratoId, @RequestBody Revisao revisao) {
        Revisao novaRevisao = revisaoService.createRevisao(contratoId, revisao);
        return ResponseEntity.ok(novaRevisao);
    }

    @GetMapping("/{contratoId}")
    public ResponseEntity<List<Revisao>> getRevisoesByContrato(@PathVariable Long contratoId) {
        return ResponseEntity.ok(revisaoService.getRevisaoByContrato(contratoId));
    }

    @PutMapping("/{revisaoId}/status")
    public ResponseEntity<Revisao> updateStatus(@PathVariable Long revisaoId, @RequestParam String status) {
        return ResponseEntity.ok(revisaoService.alterarStatus(revisaoId, StatusRevisao.valueOf(status)));
    }

    @PutMapping("/{revisaoId}/comentario")
    public ResponseEntity<Revisao> addComentario(@PathVariable Long revisaoId, @RequestParam String comentario) {
        return ResponseEntity.ok(revisaoService.addComentario(revisaoId, comentario));
    }
}
