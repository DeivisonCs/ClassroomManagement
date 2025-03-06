package com.ifba.salas_service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.ifba.salas_service.dtos.request.AlunoRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.dtos.response.TurmaResponseDTO;
import com.ifba.salas_service.models.Aluno;

public class AlunoMapper {

    public static Aluno toEntity(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        return aluno;
    }

    public static AlunoResponseDTO toResponseDTO(Aluno aluno) {
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setMatricula(aluno.getMatricula());
        dto.setNome(aluno.getNome());

        // Converter a lista de turmas do aluno para a resposta
        List<TurmaResponseDTO> turmasDTO = aluno.getTurmas().stream()
                .map(TurmaMapper::toResponseDTO) 
                .collect(Collectors.toList());

        dto.setTurmas(turmasDTO);
        return dto;
    }
}

