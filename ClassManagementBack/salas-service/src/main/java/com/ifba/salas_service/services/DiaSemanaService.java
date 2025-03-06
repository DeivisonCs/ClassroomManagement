package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.DiaSemanaRequestDTO;
import com.ifba.salas_service.dtos.response.DiaSemanaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.DiaSemanaMapper;
import com.ifba.salas_service.models.DiaSemana;
import com.ifba.salas_service.repositories.DiaSemanaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaSemanaService {

    private final DiaSemanaRepository diaSemanaRepository;

    public DiaSemanaResponseDTO criarDiaSemana(DiaSemanaRequestDTO dto) {
        DiaSemana dia = DiaSemanaMapper.toEntity(dto);
        DiaSemana saved = diaSemanaRepository.save(dia);
        return DiaSemanaMapper.toResponseDTO(saved);
    }

    public DiaSemanaResponseDTO atualizarDiaSemana(Long id, DiaSemanaRequestDTO dto) {
        DiaSemana dia = diaSemanaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dia da semana não encontrado"));
        dia.setNome(dto.getNome());
        return DiaSemanaMapper.toResponseDTO(diaSemanaRepository.save(dia));
    }

    public DiaSemanaResponseDTO buscarPorId(Long id) {
        DiaSemana dia = diaSemanaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dia da semana não encontrado"));
        return DiaSemanaMapper.toResponseDTO(dia);
    }

    public List<DiaSemanaResponseDTO> listarTodos() {
        return diaSemanaRepository.findAll().stream()
                .map(DiaSemanaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarDiaSemana(Long id) {
        if (!diaSemanaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dia da semana não encontrado");
        }
        diaSemanaRepository.deleteById(id);
    }
}
