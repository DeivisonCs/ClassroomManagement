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

import com.ifba.salas_service.dtos.request.DiaSemanaRequestDTO;
import com.ifba.salas_service.dtos.response.DiaSemanaResponseDTO;
import com.ifba.salas_service.services.DiaSemanaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dias-semana")
@RequiredArgsConstructor
public class DiaSemanaController {

    private final DiaSemanaService diaSemanaService;

    @PostMapping
    public ResponseEntity<DiaSemanaResponseDTO> criarDiaSemana(@RequestBody DiaSemanaRequestDTO dto) {
        DiaSemanaResponseDTO response = diaSemanaService.criarDiaSemana(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaSemanaResponseDTO> atualizarDiaSemana(
            @PathVariable Long id,
            @RequestBody DiaSemanaRequestDTO dto
    ) {
        DiaSemanaResponseDTO response = diaSemanaService.atualizarDiaSemana(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaSemanaResponseDTO> buscarPorId(@PathVariable Long id) {
        DiaSemanaResponseDTO response = diaSemanaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DiaSemanaResponseDTO>> listarTodos() {
        List<DiaSemanaResponseDTO> lista = diaSemanaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiaSemana(@PathVariable Long id) {
        diaSemanaService.deletarDiaSemana(id);
        return ResponseEntity.noContent().build();
    }
}
