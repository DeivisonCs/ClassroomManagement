package com.ifba.salas_service.dtos.request;

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
public class HorarioRequestDTO {
    private String inicio; // Formato "HH:mm"
    private String fim;    // Formato "HH:mm"
}