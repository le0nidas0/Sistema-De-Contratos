package com.example.contratos.controller;

import com.example.contratos.dto.DocumentoRequestDTO;
import com.example.contratos.dto.DocumentoResponseDTO;
import com.example.contratos.model.Documento;
import com.example.contratos.service.DocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> addDocumento(@RequestBody DocumentoRequestDTO request) {
        Documento documento = new Documento();
        documento.setNome(request.nome());
        documento.setTipo(request.tipo());

        Documento savedDocumento = documentoService.addDocumento(request.contratoId(), documento);

        DocumentoResponseDTO response = new DocumentoResponseDTO(savedDocumento.getId(), savedDocumento.getNome(),
                savedDocumento.getTipo(), savedDocumento.getVersao());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{contratoId}")
    public ResponseEntity<List<DocumentoResponseDTO>> getDocumentosByContrato(@PathVariable Long contratoId) {
        List<DocumentoResponseDTO> documentos = documentoService.getDocumentosByContrato(contratoId)
                .stream()
                .map(doc -> new DocumentoResponseDTO(doc.getId(), doc.getNome(), doc.getTipo(), doc.getVersao()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DocumentoResponseDTO>> searchDocumentos(@RequestParam String query) {
        List<DocumentoResponseDTO> documentos = documentoService.searchDocumentos(query)
                .stream()
                .map(doc -> new DocumentoResponseDTO(doc.getId(), doc.getNome(), doc.getTipo(), doc.getVersao()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(documentos);
    }

    @PutMapping("/{documentoId}/versao")
    public ResponseEntity<DocumentoResponseDTO> updateVersao(@PathVariable Long documentoId, @RequestParam int novaVersao) {
        Documento documento = documentoService.updateVersao(documentoId, novaVersao);
        DocumentoResponseDTO response = new DocumentoResponseDTO(documento.getId(), documento.getNome(), documento.getTipo(), documento.getVersao());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{documentoId}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long documentoId) {
        documentoService.deleteDocumento(documentoId);
        return ResponseEntity.noContent().build();
    }
}

