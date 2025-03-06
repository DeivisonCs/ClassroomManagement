package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.AulaRequestDTO;
import com.ifba.salas_service.dtos.response.AulaResponseDTO;
import com.ifba.salas_service.models.Aula;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Turma;

public class AulaMapper {

    public static Aula toEntity(AulaRequestDTO dto, Disciplina disciplina, Turma turma) {
        if (dto == null) return null;

        Aula aula = new Aula();
        aula.setDisciplina(disciplina);
        aula.setTurma(turma);

        return aula;
    }

    public static AulaResponseDTO toResponseDTO(Aula aula) {
        if (aula == null) return null;

        AulaResponseDTO dto = new AulaResponseDTO();
        dto.setId(aula.getId());

        if (aula.getDisciplina() != null) {
            dto.setDisciplina(
                DisciplinaMapper.toResponseDTO(aula.getDisciplina())
            );
        }

        if (aula.getTurma() != null) {
            dto.setTurma(
                TurmaMapper.toResponseDTO(aula.getTurma())
            );
        }

        return dto;
    }
}
