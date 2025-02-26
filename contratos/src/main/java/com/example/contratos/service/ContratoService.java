package com.example.contratos.service;

import com.example.contratos.dto.ContratoRequestDTO;
import com.example.contratos.dto.ContratoResponseDTO;
import com.example.contratos.model.Contrato;
import com.example.contratos.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public ContratoResponseDTO criarContrato(ContratoRequestDTO dto) {
        Contrato contrato = convertToEntity(dto);
        Contrato contratoSalvo = contratoRepository.save(contrato);
        return convertToResponseDTO(contratoSalvo);
    }

    public ContratoResponseDTO buscarContratoPorId(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
        return convertToResponseDTO(contrato);
    }

    public List<ContratoResponseDTO> listarContratos() {
        return contratoRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ContratoResponseDTO atualizarContrato(Long id, ContratoRequestDTO dto) {
        Contrato contratoExistente = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        contratoExistente.setNome(dto.getNome());
        contratoExistente.setDescricao(dto.getDescricao());
        contratoExistente.setValor(dto.getValor());

        Contrato contratoAtualizado = contratoRepository.save(contratoExistente);
        return convertToResponseDTO(contratoAtualizado);
    }

    public void deletarContrato(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado");
        }
        contratoRepository.deleteById(id);
    }

    private Contrato convertToEntity(ContratoRequestDTO dto) {
        return new Contrato(null, dto.getNome(), dto.getDescricao(), dto.getValor());
    }

    private ContratoResponseDTO convertToResponseDTO(Contrato contrato) {
        return new ContratoResponseDTO(contrato.getId(), contrato.getNome(), contrato.getDescricao(), contrato.getValor());
    }
}
