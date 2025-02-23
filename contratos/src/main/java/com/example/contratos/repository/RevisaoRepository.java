package com.example.contratos.repository;

import com.example.contratos.model.Revisao;
import com.example.contratos.model.StatusRevisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
    List<Revisao> findByStatus(StatusRevisao status);
    List<Revisao> findBycontratoId(Long contratoId);
}
