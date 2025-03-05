package com.ifba.salas_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.services.SalaService;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<Sala> listarSalas() {
        return salaService.listarSalas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Sala> buscarPorCodigo(@PathVariable String codigo) {
        Optional<Sala> sala = salaService.buscarPorCodigo(codigo);
        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sala salvarSala(@RequestBody Sala sala) {
        return salaService.salvarSala(sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}