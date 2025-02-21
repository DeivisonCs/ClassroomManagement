package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.AlunoRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.AlunoMapper;
import com.ifba.salas_service.models.Aluno;
import com.ifba.salas_service.repositories.AlunoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoResponseDTO criarAluno(AlunoRequestDTO dto) {
        Aluno aluno = AlunoMapper.toEntity(dto);
        Aluno saved = alunoRepository.save(aluno);
        return AlunoMapper.toResponseDTO(saved);
    }

    public AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        aluno.setNome(dto.getNome());
        return AlunoMapper.toResponseDTO(alunoRepository.save(aluno));
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        return AlunoMapper.toResponseDTO(aluno);
    }

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll().stream()
                .map(AlunoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}
