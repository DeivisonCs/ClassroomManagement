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

public class DiaSemanaResponseDTO {
    private Long id;
    private String nome; // Ex: "Segunda-feira"
}