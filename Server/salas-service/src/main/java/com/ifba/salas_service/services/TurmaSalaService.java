package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.TurmaSalaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaSalaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.TurmaSalaMapper;
import com.ifba.salas_service.models.DiaSemana;
import com.ifba.salas_service.models.Horario;
import com.ifba.salas_service.models.Professor;
import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.models.Turma;
import com.ifba.salas_service.models.TurmaSala;
import com.ifba.salas_service.repositories.DiaSemanaRepository;
import com.ifba.salas_service.repositories.HorarioRepository;
import com.ifba.salas_service.repositories.ProfessorRepository;
import com.ifba.salas_service.repositories.SalaRepository;
import com.ifba.salas_service.repositories.TurmaRepository;
import com.ifba.salas_service.repositories.TurmaSalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurmaSalaService {

    private final TurmaSalaRepository turmaSalaRepository;
    private final TurmaRepository turmaRepository;
    private final SalaRepository salaRepository;
    private final HorarioRepository horarioRepository;
    private final DiaSemanaRepository diaSemanaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaSalaResponseDTO criarTurmaSala(TurmaSalaRequestDTO dto) {
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));
        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));
        Horario horario = horarioRepository.findById(dto.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        DiaSemana diaSemana = diaSemanaRepository.findById(dto.getDiaSemanaId())
                .orElseThrow(() -> new ResourceNotFoundException("Dia da semana não encontrado"));
        Professor professor = professorRepository.findById(dto.getProfessorMatricula())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        TurmaSala ts = TurmaSalaMapper.toEntity(dto, turma, sala, horario, diaSemana, professor);

        TurmaSala saved = turmaSalaRepository.save(ts);
        return TurmaSalaMapper.toResponseDTO(saved);
    }

    public TurmaSalaResponseDTO atualizarTurmaSala(Long id, TurmaSalaRequestDTO dto) {
        TurmaSala ts = turmaSalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro TurmaSala não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));
        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));
        Horario horario = horarioRepository.findById(dto.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        DiaSemana diaSemana = diaSemanaRepository.findById(dto.getDiaSemanaId())
                .orElseThrow(() -> new ResourceNotFoundException("Dia da semana não encontrado"));
        Professor professor = professorRepository.findById(dto.getProfessorMatricula())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));

        ts.setTurma(turma);
        ts.setSala(sala);
        ts.setHorario(horario);
        ts.setDiaSemana(diaSemana);
        ts.setProfessor(professor);

        TurmaSala updated = turmaSalaRepository.save(ts);
        return TurmaSalaMapper.toResponseDTO(updated);
    }

    public TurmaSalaResponseDTO buscarPorId(Long id) {
        TurmaSala ts = turmaSalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro TurmaSala não encontrado"));
        return TurmaSalaMapper.toResponseDTO(ts);
    }

    public List<TurmaSalaResponseDTO> listarTodos() {
        return turmaSalaRepository.findAll().stream()
                .map(TurmaSalaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarTurmaSala(Long id) {
        if (!turmaSalaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro TurmaSala não encontrado");
        }
        turmaSalaRepository.deleteById(id);
    }
}