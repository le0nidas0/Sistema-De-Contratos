package com.example.contratos.service;

import com.example.contratos.model.Contrato;
import com.example.contratos.repository.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("Contrato com ID " + id + " não encontrado."));
    }

    public List<Contrato> getContratosByEmpresa(String empresaContratante) {
        return contratoRepository.findByEmpresaContratanteContainingIgnoreCase(empresaContratante);
    }





    public Contrato updateContrato (Long id, Contrato contratoAtualizado) {

        Contrato contratoExistente = getContratoById(id);

        if (contratoAtualizado.getTitulo() != null) {
            contratoExistente.setTitulo(contratoAtualizado.getTitulo());
        }
        if (contratoAtualizado.getDataTermino() != null) {
            contratoExistente.setDataTermino(contratoAtualizado.getDataTermino());
        }
        if (contratoAtualizado.getStatus() != null) {
            contratoExistente.setStatus(contratoAtualizado.getStatus());
        }
        if (contratoAtualizado.getEmpresaContratante() != null) {
            contratoExistente.setEmpresaContratante(contratoAtualizado.getEmpresaContratante());
        }

        return contratoRepository.save(contratoExistente);
    }



    public void deleteContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato com ID " + id + " não encontrado para exclusão."));
        contratoRepository.delete(contrato);
    }



}
