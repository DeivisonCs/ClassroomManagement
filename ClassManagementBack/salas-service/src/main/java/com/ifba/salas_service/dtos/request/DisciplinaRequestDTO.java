package com.ifba.salas_service.dtos.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DisciplinaRequestDTO {
    private String nome;
    private List<Long> professorMatricula;
    private List<Long> turmasIds;
}