package com.example.contratos.service;

import com.example.contratos.dto.ContratoRequestDTO;
import com.example.contratos.dto.ContratoResponseDTO;
import com.example.contratos.model.Contrato;
import com.example.contratos.model.StatusContrato;
import com.example.contratos.model.Usuario;
import com.example.contratos.repository.ContratoRepository;
import com.example.contratos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;

    public ContratoService(ContratoRepository contratoRepository, UsuarioRepository usuarioRepository) {
        this.contratoRepository = contratoRepository;
        this.usuarioRepository = usuarioRepository;
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
        Usuario usuarioPadrao = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuário padrão não encontrado"));

        Contrato contrato = new Contrato();
        contrato.setNome(dto.getNome());
        contrato.setDescricao(dto.getDescricao());
        contrato.setValor(dto.getValor() != null ? dto.getValor() : BigDecimal.ZERO);
        contrato.setTitulo("Título Padrão");
        contrato.setEmpresaContratante("Empresa Padrão");
        contrato.setDataInicio(LocalDate.now());
        contrato.setDataTermino(LocalDate.now().plusMonths(6));
        contrato.setStatus(StatusContrato.ATIVO);
        contrato.setResponsavel(usuarioPadrao);
        return contrato;
    }

    private ContratoResponseDTO convertToResponseDTO(Contrato contrato) {
        return new ContratoResponseDTO(
                contrato.getId(),
                contrato.getNome(),
                contrato.getEmpresaContratante(),
                contrato.getValor()
        );
    }
}