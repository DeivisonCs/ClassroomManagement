package com.ifba.salas_service.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlunoResponseDTO {
    private Long matricula;
    private String nome;
    private List<TurmaResponseDTO> turmas;
}