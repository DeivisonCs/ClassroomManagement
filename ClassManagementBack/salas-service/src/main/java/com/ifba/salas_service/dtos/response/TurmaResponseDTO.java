package com.ifba.salas_service.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaResponseDTO {
    private Long id;
    private String nome;
    // Disciplina associada à turma
    private DisciplinaResponseDTO disciplina;
    // Alunos que pertencem à turma
    private List<AlunoResponseDTO> alunos;
}
