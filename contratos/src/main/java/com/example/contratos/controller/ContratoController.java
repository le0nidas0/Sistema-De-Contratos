package com.example.contratos.controller;

import com.example.contratos.model.Contrato;
import com.example.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contrato")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Contrato> createContrato(@RequestBody Contrato contrato) {
        Contrato createdContrato = contratoService.createContrato(contrato);
        return new ResponseEntity<>(createdContrato, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> getAllContrato() {
        List<Contrato> contrato = contratoService.getAllContrato();
        return new ResponseEntity<>(contrato, HttpStatus.OK);
    }
}
