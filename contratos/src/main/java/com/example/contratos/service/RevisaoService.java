package com.example.contratos.service;

import com.example.contratos.repository.RevisaoRepository;
import org.springframework.stereotype.Service;

@Service
public class RevisaoService {
    private final RevisaoRepository  revisaoRepository;

    public RevisaoService(RevisaoRepository revisaoRepository) {
        this.revisaoRepository = revisaoRepository;
    }
}
