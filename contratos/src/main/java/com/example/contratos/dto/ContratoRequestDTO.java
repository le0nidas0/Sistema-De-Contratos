package com.example.contratos.dto;

import java.time.LocalDate;

public record ContratoRequestDTO (String titulo, String empresaContratante, LocalDate dataTermino){
}
