package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.ProfessorRequestDTO;
import com.ifba.salas_service.dtos.response.ProfessorResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.ProfessorMapper;
import com.ifba.salas_service.models.Professor;
import com.ifba.salas_service.repositories.ProfessorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto) {
        Professor professor = ProfessorMapper.toEntity(dto);
        Professor saved = professorRepository.save(professor);
        return ProfessorMapper.toResponseDTO(saved);
    }

    public ProfessorResponseDTO atualizarProfessor(Long id, ProfessorRequestDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        professor.setNome(dto.getNome());
        return ProfessorMapper.toResponseDTO(professorRepository.save(professor));
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        return ProfessorMapper.toResponseDTO(professor);
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll().stream()
                .map(ProfessorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor não encontrado");
        }
        professorRepository.deleteById(id);
    }
}
