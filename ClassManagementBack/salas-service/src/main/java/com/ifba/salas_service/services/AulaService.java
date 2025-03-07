package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifba.salas_service.dtos.request.AulaRequestDTO;
import com.ifba.salas_service.dtos.response.AulaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.AulaMapper;
import com.ifba.salas_service.models.Aula;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Turma;
import com.ifba.salas_service.repositories.AulaRepository;
import com.ifba.salas_service.repositories.DisciplinaRepository;
import com.ifba.salas_service.repositories.TurmaRepository;
import com.ifba.salas_service.repositories.TurmaSalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final TurmaSalaRepository turmaSalaRepository; // Injetar o repositório TurmaSala

    public AulaResponseDTO criarAula(AulaRequestDTO dto) {
        
        // Carregar Disciplina
        Disciplina disc = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        // Carregar Turma
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));

        // Converter para Entidade
        Aula aula = AulaMapper.toEntity(dto, disc, turma);

        // Salvar
        Aula saved = aulaRepository.save(aula);

        // Converter para Response
        return AulaMapper.toResponseDTO(saved);
    }

    public AulaResponseDTO atualizarAula(Long id, AulaRequestDTO dto) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada"));

        Disciplina disc = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));

        aula.setDisciplina(disc);
        aula.setTurma(turma);

        Aula updated = aulaRepository.save(aula);
        return AulaMapper.toResponseDTO(updated);
    }

    public AulaResponseDTO buscarPorId(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada"));
        return AulaMapper.toResponseDTO(aula);
    }

    public List<AulaResponseDTO> listarTodas() {
        return aulaRepository.findAll().stream()
                .map(AulaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional // Garantir que a operação seja atômica
    public void deletarAula(Long id) {
        // Obter a aula antes de excluir para ter acesso à turma_id
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada"));
        
        Long turmaId = aula.getTurma().getId();
        
        // Primeiro deletar os registros relacionados em turma_sala
        turmaSalaRepository.deleteByTurmaId(turmaId);
        
        // Depois deletar a aula
        aulaRepository.delete(aula);
    }
}