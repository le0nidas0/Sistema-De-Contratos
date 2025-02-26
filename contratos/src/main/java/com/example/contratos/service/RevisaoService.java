package com.example.contratos.service;

import com.example.contratos.model.Contrato;
import com.example.contratos.model.Revisao;
import com.example.contratos.model.StatusRevisao;
import com.example.contratos.repository.ContratoRepository;
import com.example.contratos.repository.RevisaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevisaoService {
    private final RevisaoRepository  revisaoRepository;
    private final ContratoRepository contratoRepository;

    public RevisaoService(RevisaoRepository revisaoRepository, ContratoRepository contratoRepository) {
        this.revisaoRepository = revisaoRepository;
        this.contratoRepository = contratoRepository;
    }

    public Revisao createRevisao(Long contratoId, Revisao revisao) {
        Contrato contrato = contratoRepository.findById(contratoId).orElseThrow(() -> new RuntimeException("Contrato nao encontrado"));

        revisao.setContrato(contrato);
        revisao.setDataRevisao(LocalDate.now());
        revisao.setStatus(StatusRevisao.PENDENTE);

        return revisaoRepository.save(revisao);
    }

    public List<Revisao> getrevisaoByContrato(Long contratoId) {
        return revisaoRepository.findBycontratoId(contratoId);
    }

    public List<Revisao> getRevisaoBystatus(StatusRevisao status){
        return revisaoRepository.findByStatus(status);
    }

    public Revisao addComentario(Long revisaoId, String comentario) {
        Revisao revisao = revisaoRepository.findById(revisaoId).orElseThrow(() -> new RuntimeException("Revisao nao encontrada"));
        revisao.setComentarios(comentario);
        return revisaoRepository.save(revisao);
    }

    public Revisao alterarStatus(Long revisaoId, StatusRevisao novoStatus) {
        Revisao revisao = revisaoRepository.findById(revisaoId).orElseThrow(() -> new RuntimeException("Revisao nao encontrada"));

        revisao.setStatus(novoStatus);
        return revisaoRepository.save(revisao);
    }
}
