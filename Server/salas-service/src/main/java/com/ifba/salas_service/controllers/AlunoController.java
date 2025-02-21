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

import com.ifba.salas_service.dtos.request.AlunoRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.services.AlunoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO response = alunoService.criarAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @RequestBody AlunoRequestDTO dto
    ) {
        AlunoResponseDTO response = alunoService.atualizarAluno(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        AlunoResponseDTO response = alunoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
        List<AlunoResponseDTO> lista = alunoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
