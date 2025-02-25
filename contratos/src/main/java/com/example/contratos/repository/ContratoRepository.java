package com.example.contratos.repository;

import com.example.contratos.model.Contrato;
import com.example.contratos.model.StatusContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByEmpresaContratanteContainingIgnoreCase(String empresaContratante);
}
