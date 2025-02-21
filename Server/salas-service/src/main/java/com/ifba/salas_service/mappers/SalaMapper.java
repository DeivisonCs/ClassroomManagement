package com.ifba.salas_service.mappers;


import com.ifba.salas_service.dtos.request.SalaRequestDTO;
import com.ifba.salas_service.dtos.response.SalaResponseDTO;
import com.ifba.salas_service.models.Sala;

public class SalaMapper {

    public static Sala toEntity(SalaRequestDTO dto) {
        if (dto == null) return null;
        Sala sala = new Sala();
        sala.setNome(dto.getNome());
        return sala;
    }

    public static SalaResponseDTO toResponseDTO(Sala sala) {
        if (sala == null) return null;
        SalaResponseDTO dto = new SalaResponseDTO();
        dto.setId(sala.getId());
        dto.setNome(sala.getNome());
        return dto;
    }
}