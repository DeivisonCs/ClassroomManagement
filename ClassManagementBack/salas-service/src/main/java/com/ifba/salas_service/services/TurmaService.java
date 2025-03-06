package com.ifba.salas_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.TurmaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.TurmaMapper;
import com.ifba.salas_service.models.Aluno;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Turma;
import com.ifba.salas_service.repositories.AlunoRepository;
import com.ifba.salas_service.repositories.DisciplinaRepository;
import com.ifba.salas_service.repositories.TurmaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;

    public TurmaResponseDTO criarTurma(TurmaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));
    
        List<Aluno> alunos = carregarAlunos(dto.getAlunosIds());
    
        Turma turma = TurmaMapper.toEntity(dto, disciplina, alunos);
    
        for (Aluno aluno : alunos) {
            aluno.getTurmas().add(turma);
        }
    
        Turma saved = turmaRepository.save(turma);
    
        
        for (Aluno aluno : alunos) {
            alunoRepository.save(aluno);
        }
    
        return TurmaMapper.toResponseDTO(saved);
    }

    public TurmaResponseDTO atualizarTurma(Long id, TurmaRequestDTO dto) {
    Turma turma = turmaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));

    Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
            .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

    List<Aluno> alunos = carregarAlunos(dto.getAlunosIds());

    turma.setNome(dto.getNome());
    turma.setDisciplina(disciplina);
    turma.setAlunos(alunos);

    for (Aluno aluno : alunos) {
        aluno.getTurmas().add(turma);
    }

    Turma updated = turmaRepository.save(turma);

    for (Aluno aluno : alunos) {
        alunoRepository.save(aluno);
    }

    return TurmaMapper.toResponseDTO(updated);
}

    public TurmaResponseDTO buscarPorId(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));
        return TurmaMapper.toResponseDTO(turma);
    }

    public List<TurmaResponseDTO> listarTodas() {
        return turmaRepository.findAll().stream()
                .map(TurmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarTurma(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turma não encontrada");
        }
        turmaRepository.deleteById(id);
    }

    private List<Aluno> carregarAlunos(List<Long> alunosIds) {
        if (alunosIds == null || alunosIds.isEmpty()) {
            return new ArrayList<>();
        }
        return alunoRepository.findAllById(alunosIds);
    }
}
