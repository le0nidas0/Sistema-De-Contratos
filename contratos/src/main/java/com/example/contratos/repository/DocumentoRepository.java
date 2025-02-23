package com.example.contratos.repository;

import com.example.contratos.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByTipo(String tipo);
    List<Documento> findByNome(String nome);

}
