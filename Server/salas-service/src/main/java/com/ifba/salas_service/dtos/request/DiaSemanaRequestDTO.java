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
public class DiaSemanaRequestDTO {
    private String nome; // Ex: "Segunda-feira", "Terça-feira", etc.
}
