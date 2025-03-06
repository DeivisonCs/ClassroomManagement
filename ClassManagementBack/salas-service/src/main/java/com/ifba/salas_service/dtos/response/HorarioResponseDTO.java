package com.ifba.salas_service.dtos.response;

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
public class HorarioResponseDTO {
    private Long id;
    private String inicio; // "HH:mm"
    private String fim;    // "HH:mm"
}