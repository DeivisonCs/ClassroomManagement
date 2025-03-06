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
public class DisciplinaResponseDTO {
    private Long id;
    private String nome;
    // Lista de professores vinculados a esta disciplina
    private List<ProfessorResponseDTO> professores;
}