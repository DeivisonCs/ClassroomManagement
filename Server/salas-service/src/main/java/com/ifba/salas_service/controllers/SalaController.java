package com.ifba.salas_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifba.salas_service.dtos.SalaDTO;
import com.ifba.salas_service.services.SalaService;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<SalaDTO> listarSalas() {
        return salaService.listarSalas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> buscarPorId(@PathVariable Long id) {
        SalaDTO salaDTO = salaService.buscarPorId(id);
        return ResponseEntity.ok(salaDTO);
    }

    @PostMapping
    public ResponseEntity<SalaDTO> salvarSala(@RequestBody SalaDTO salaDTO) {
        SalaDTO savedSalaDTO = salaService.salvarSala(salaDTO);
        return ResponseEntity.ok(savedSalaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}