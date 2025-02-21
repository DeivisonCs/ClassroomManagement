package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.SalaDTO;
import com.ifba.salas_service.models.Sala;

public class SalaMapper {
    public static SalaDTO toDTO(Sala sala) {
        return new SalaDTO(sala.getId(), sala.getNome());
    }

    public static Sala toEntity(SalaDTO salaDTO) {
        return new Sala(salaDTO.getId(), salaDTO.getNome());
    }
}