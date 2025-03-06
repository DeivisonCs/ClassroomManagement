package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.ProfessorRequestDTO;
import com.ifba.salas_service.dtos.response.ProfessorResponseDTO;
import com.ifba.salas_service.models.Professor;

public class ProfessorMapper {

    public static Professor toEntity(ProfessorRequestDTO dto) {
        if (dto == null) return null;
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        return professor;
    }

    public static ProfessorResponseDTO toResponseDTO(Professor professor) {
        if (professor == null) return null;
        ProfessorResponseDTO dto = new ProfessorResponseDTO();
        dto.setMatricula(professor.getMatricula());
        dto.setNome(professor.getNome());
        return dto;
    }
}
