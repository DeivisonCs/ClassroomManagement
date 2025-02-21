package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.AlunoRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.models.Aluno;

public class AlunoMapper {

    public static Aluno toEntity(AlunoRequestDTO dto) {
        if (dto == null) return null;
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        return aluno;
    }

    public static AlunoResponseDTO toResponseDTO(Aluno aluno) {
        if (aluno == null) return null;
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setMatricula(aluno.getMatricula());
        dto.setNome(aluno.getNome());
        return dto;
    }
}
