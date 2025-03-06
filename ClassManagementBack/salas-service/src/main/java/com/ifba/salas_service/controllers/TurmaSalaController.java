package com.ifba.salas_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifba.salas_service.dtos.request.TurmaSalaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaSalaResponseDTO;
import com.ifba.salas_service.services.TurmaSalaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/turmas-salas")
@RequiredArgsConstructor
public class TurmaSalaController {

    private final TurmaSalaService turmaSalaService;

    @PostMapping
    public ResponseEntity<TurmaSalaResponseDTO> criar(@RequestBody TurmaSalaRequestDTO dto) {
        TurmaSalaResponseDTO response = turmaSalaService.criarTurmaSala(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaSalaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody TurmaSalaRequestDTO dto
    ) {
        TurmaSalaResponseDTO response = turmaSalaService.atualizarTurmaSala(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaSalaResponseDTO> buscarPorId(@PathVariable Long id) {
        TurmaSalaResponseDTO response = turmaSalaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TurmaSalaResponseDTO>> listarTodos() {
        List<TurmaSalaResponseDTO> lista = turmaSalaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        turmaSalaService.deletarTurmaSala(id);
        return ResponseEntity.noContent().build();
    }
}
