package com.ifba.salas_service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.ifba.salas_service.dtos.request.DisciplinaRequestDTO;
import com.ifba.salas_service.dtos.response.DisciplinaResponseDTO;
import com.ifba.salas_service.dtos.response.ProfessorResponseDTO;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Professor;

public class DisciplinaMapper {

    public static Disciplina toEntity(DisciplinaRequestDTO dto, List<Professor> professores) {
        if (dto == null) return null;
        Disciplina disc = new Disciplina();
        disc.setNome(dto.getNome());
        disc.setProfessores(professores);
        return disc;
    }

    public static DisciplinaResponseDTO toResponseDTO(Disciplina disc) {
        if (disc == null) return null;
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
        dto.setId(disc.getId());
        dto.setNome(disc.getNome());

        if (disc.getProfessores() != null) {
            dto.setProfessores(
                disc.getProfessores().stream()
                    .map(prof -> {
                        ProfessorResponseDTO pDto = new ProfessorResponseDTO();
                        pDto.setMatricula(prof.getMatricula());
                        pDto.setNome(prof.getNome());
                        return pDto;
                    })
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }
}
