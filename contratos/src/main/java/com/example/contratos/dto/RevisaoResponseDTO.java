package com.example.contratos.dto;

import com.example.contratos.model.StatusRevisao;

import java.time.LocalDate;

public record RevisaoResponseDTO (Long id, String comentario, LocalDate dataRevisao, StatusRevisao status){
}
