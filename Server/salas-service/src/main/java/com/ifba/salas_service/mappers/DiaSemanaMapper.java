package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.DiaSemanaRequestDTO;
import com.ifba.salas_service.dtos.response.DiaSemanaResponseDTO;
import com.ifba.salas_service.models.DiaSemana;

public class DiaSemanaMapper {

    public static DiaSemana toEntity(DiaSemanaRequestDTO dto) {
        if (dto == null) return null;
        DiaSemana dia = new DiaSemana();
        dia.setNome(dto.getNome());
        return dia;
    }

    public static DiaSemanaResponseDTO toResponseDTO(DiaSemana dia) {
        if (dia == null) return null;
        DiaSemanaResponseDTO dto = new DiaSemanaResponseDTO();
        dto.setId(dia.getId());
        dto.setNome(dia.getNome());
        return dto;
    }
}
