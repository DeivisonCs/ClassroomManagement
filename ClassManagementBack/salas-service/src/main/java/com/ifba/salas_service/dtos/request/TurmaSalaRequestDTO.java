package com.ifba.salas_service.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TurmaSalaRequestDTO {
    private Long salaId;
    private Long turmaId;
    private Long horarioId;
    private Long diaSemanaId;  
    private Long professorMatricula;
}