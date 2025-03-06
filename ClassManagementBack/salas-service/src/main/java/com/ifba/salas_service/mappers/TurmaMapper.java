package com.ifba.salas_service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.ifba.salas_service.dtos.request.TurmaRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.dtos.response.DisciplinaResponseDTO;
import com.ifba.salas_service.dtos.response.ProfessorResponseDTO;
import com.ifba.salas_service.dtos.response.TurmaResponseDTO;
import com.ifba.salas_service.models.Aluno;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Turma;

public class TurmaMapper {

    public static Turma toEntity(TurmaRequestDTO dto, Disciplina disciplina, List<Aluno> alunos) {
        if (dto == null) return null;
        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setDisciplina(disciplina);
        turma.setAlunos(alunos);
        return turma;
    }

    public static TurmaResponseDTO toResponseDTO(Turma turma) {
        if (turma == null) return null;
        TurmaResponseDTO dto = new TurmaResponseDTO();
        dto.setId(turma.getId());
        dto.setNome(turma.getNome());

        if (turma.getDisciplina() != null) {
            DisciplinaResponseDTO discDTO = new DisciplinaResponseDTO();
            discDTO.setId(turma.getDisciplina().getId());
            discDTO.setNome(turma.getDisciplina().getNome());
            discDTO.setProfessores(
                turma.getDisciplina().getProfessores().stream()
                    .map(prof -> {
                        ProfessorResponseDTO pDto = new ProfessorResponseDTO();
                        pDto.setMatricula(prof.getMatricula());
                        pDto.setNome(prof.getNome());
                        return pDto;
                    })
                    .collect(Collectors.toList())
            );
            dto.setDisciplina(discDTO);
        }

        if (turma.getAlunos() != null) {
            dto.setAlunos(
                turma.getAlunos().stream()
                    .map(aluno -> {
                        AlunoResponseDTO aDto = new AlunoResponseDTO();
                        aDto.setMatricula(aluno.getMatricula());
                        aDto.setNome(aluno.getNome());
                        return aDto;
                    })
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }
}
