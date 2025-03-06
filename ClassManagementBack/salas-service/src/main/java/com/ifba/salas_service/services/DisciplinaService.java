package com.ifba.salas_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.DisciplinaRequestDTO;
import com.ifba.salas_service.dtos.response.DisciplinaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.DisciplinaMapper;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Professor;
import com.ifba.salas_service.repositories.DisciplinaRepository;
import com.ifba.salas_service.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    @Transactional
    public DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO dto) {
        List<Professor> professores = carregarProfessores(dto.getProfessorMatricula());
        
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        
        if (disciplina.getProfessores() == null) {
            disciplina.setProfessores(new ArrayList<>());
        }
        
        
        for (Professor professor : professores) {
            disciplina.getProfessores().add(professor);
            
            if (professor.getDisciplinas() == null) {
                professor.setDisciplinas(new ArrayList<>());
            }
            professor.getDisciplinas().add(disciplina);
        }
        
        Disciplina saved = disciplinaRepository.save(disciplina);
        
        return DisciplinaMapper.toResponseDTO(saved);
    }

    public DisciplinaResponseDTO atualizarDisciplina(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        // Carregar professores
        List<Professor> professores = carregarProfessores(dto.getProfessorMatricula());

        // Atualizar campos
        disciplina.setNome(dto.getNome());
        disciplina.setProfessores(professores);

        // Salvar
        Disciplina updated = disciplinaRepository.save(disciplina);
        return DisciplinaMapper.toResponseDTO(updated);
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));
        return DisciplinaMapper.toResponseDTO(disciplina);
    }

    public List<DisciplinaResponseDTO> listarTodas() {
        return disciplinaRepository.findAll().stream()
                .map(DisciplinaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Disciplina não encontrada");
        }
        disciplinaRepository.deleteById(id);
    }

    private List<Professor> carregarProfessores(List<Long> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            return new ArrayList<>();
        }
        return professorRepository.findAllById(matriculas);
    }
}
