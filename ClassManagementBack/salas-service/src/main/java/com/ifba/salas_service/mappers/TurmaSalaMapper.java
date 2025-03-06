package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.TurmaSalaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaSalaResponseDTO;
import com.ifba.salas_service.models.DiaSemana;
import com.ifba.salas_service.models.Horario;
import com.ifba.salas_service.models.Professor;
import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.models.Turma;
import com.ifba.salas_service.models.TurmaSala;

public class TurmaSalaMapper {

    public static TurmaSala toEntity(
            TurmaSalaRequestDTO dto,
            Turma turma,
            Sala sala,
            Horario horario,
            DiaSemana diaSemana,
            Professor professor
    ) {
        if (dto == null) return null;
        TurmaSala ts = new TurmaSala();
        ts.setTurma(turma);
        ts.setSala(sala);
        ts.setHorario(horario);
        ts.setDiaSemana(diaSemana); 
        ts.setProfessor(professor);
        return ts;
    }

    public static TurmaSalaResponseDTO toResponseDTO(TurmaSala ts) {
        if (ts == null) return null;
        TurmaSalaResponseDTO dto = new TurmaSalaResponseDTO();
        dto.setId(ts.getId());
        dto.setSala(SalaMapper.toResponseDTO(ts.getSala()));
        dto.setTurma(TurmaMapper.toResponseDTO(ts.getTurma()));
        dto.setHorario(HorarioMapper.toResponseDTO(ts.getHorario()));
        dto.setDiaSemana(DiaSemanaMapper.toResponseDTO(ts.getDiaSemana()));
        dto.setProfessor(ProfessorMapper.toResponseDTO(ts.getProfessor()));
        return dto;
    }
}
