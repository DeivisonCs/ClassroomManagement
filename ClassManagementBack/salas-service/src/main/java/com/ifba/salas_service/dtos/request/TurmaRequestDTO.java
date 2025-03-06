package com.ifba.salas_service.dtos.request;


import java.util.List;

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
public class TurmaRequestDTO {
    private String nome;
    private Long disciplinaId;    
    private List<Long> alunosIds;  
}