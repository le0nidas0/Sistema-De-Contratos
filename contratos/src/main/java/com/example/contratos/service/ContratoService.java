package com.example.contratos.service;

import com.example.contratos.model.Contrato;
import com.example.contratos.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public Contrato createContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public List<Contrato> getAllContrato() {
        return contratoRepository.findAll();
    }

    public Contrato getContratoById(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public Contrato updateContrato (Long id, Contrato contratoAtualizado) {

        Contrato contratoExistente = getContratoById(id);
        contratoExistente.setDataTermino(contratoAtualizado.getDataTermino());
        contratoExistente.setStatus(contratoAtualizado.getStatus());

        return contratoRepository.save(contratoExistente);
    }

    public boolean deleteContrato(Long id) {
        if (contratoRepository.existsById(id)) {
            contratoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
