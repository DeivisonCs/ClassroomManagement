package com.ifba.salas_service.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaResponseDTO {
    private Long id;
    private String nome;
    private Integer capacidade;
}