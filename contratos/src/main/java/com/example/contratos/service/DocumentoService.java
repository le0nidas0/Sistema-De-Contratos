package com.example.contratos.service;

import com.example.contratos.model.Contrato;
import com.example.contratos.model.Documento;
import com.example.contratos.repository.ContratoRepository;
import com.example.contratos.repository.DocumentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentoService {
    private final DocumentoRepository documentoRepository;
    private final ContratoRepository contratoRepository;

    public DocumentoService(DocumentoRepository documentoRepository, ContratoRepository contratoRepository) {
        this.documentoRepository = documentoRepository;
        this.contratoRepository = contratoRepository;
    }

    public Documento addDocumento(Long contratoId, Documento documento) {
        Contrato contrato = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new EntityNotFoundException("Contrato com ID " + contratoId + " não encontrado"));

        documento.setContrato(contrato);
        return documentoRepository.save(documento);
    }

    public List<Documento> getDocumentosByContrato(Long contratoId) {
        if (!contratoRepository.existsById(contratoId)) {
            throw new EntityNotFoundException("Contrato com ID " + contratoId + " não encontrado");
        }
        return documentoRepository.findByContratoId(contratoId);
    }

    public List<Documento> searchDocumentos(String nomeOuTipo) {
        List<Documento> documentos = documentoRepository.findByNomeContainingIgnoreCaseOrTipoContainingIgnoreCase(nomeOuTipo, nomeOuTipo);
        if (documentos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum documento encontrado com nome ou tipo: " + nomeOuTipo);
        }
        return documentos;
    }

    public Documento updateVersao(Long documentoId, int novaVersao) {
        Documento documento = documentoRepository.findById(documentoId)
                .orElseThrow(() -> new EntityNotFoundException("Documento com ID " + documentoId + " não encontrado"));

        documento.setVersao(novaVersao);
        return documentoRepository.save(documento);
    }

    public void deleteDocumento(Long documentoId) {
        if (!documentoRepository.existsById(documentoId)) {
            throw new EntityNotFoundException("Documento com ID " + documentoId + " não encontrado");
        }
        documentoRepository.deleteById(documentoId);
    }

}
