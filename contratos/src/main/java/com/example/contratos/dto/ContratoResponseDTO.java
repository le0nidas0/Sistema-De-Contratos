package com.example.contratos.dto;

import java.math.BigDecimal;

public record ContratoResponseDTO (Long id, String titulo, String empresaContratante, BigDecimal valor) {
}
