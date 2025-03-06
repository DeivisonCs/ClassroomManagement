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

import com.ifba.salas_service.dtos.request.HorarioRequestDTO;
import com.ifba.salas_service.dtos.response.HorarioResponseDTO;
import com.ifba.salas_service.services.HorarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioResponseDTO> criarHorario(@RequestBody HorarioRequestDTO dto) {
        HorarioResponseDTO response = horarioService.criarHorario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> atualizarHorario(
            @PathVariable Long id,
            @RequestBody HorarioRequestDTO dto
    ) {
        HorarioResponseDTO response = horarioService.atualizarHorario(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> buscarPorId(@PathVariable Long id) {
        HorarioResponseDTO response = horarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<HorarioResponseDTO>> listarTodos() {
        List<HorarioResponseDTO> lista = horarioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHorario(@PathVariable Long id) {
        horarioService.deletarHorario(id);
        return ResponseEntity.noContent().build();
    }
}
