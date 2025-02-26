package com.example.contratos.dto;

import java.time.LocalDate;

public record ContratoResponseDTO (Long id, String titulo, String empresaContratante, LocalDate dataTermino, String status) {
}
