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

import com.ifba.salas_service.dtos.request.TurmaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaResponseDTO;
import com.ifba.salas_service.services.TurmaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> criarTurma(@RequestBody TurmaRequestDTO dto) {
        TurmaResponseDTO response = turmaService.criarTurma(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizarTurma(
            @PathVariable Long id,
            @RequestBody TurmaRequestDTO dto
    ) {
        TurmaResponseDTO response = turmaService.atualizarTurma(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(@PathVariable Long id) {
        TurmaResponseDTO response = turmaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTodas() {
        List<TurmaResponseDTO> lista = turmaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        turmaService.deletarTurma(id);
        return ResponseEntity.noContent().build();
    }
}
