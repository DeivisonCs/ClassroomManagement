package com.ifba.salas_service.dtos.response;

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
public class TurmaSalaResponseDTO {
    private Long id;
    private SalaResponseDTO sala;
    private TurmaResponseDTO turma;
    private HorarioResponseDTO horario;
    private DiaSemanaResponseDTO diaSemana; 
    private ProfessorResponseDTO professor;
}