package com.ifba.salas_service.mappers;

import com.ifba.salas_service.dtos.request.HorarioRequestDTO;
import com.ifba.salas_service.dtos.response.HorarioResponseDTO;
import com.ifba.salas_service.models.Horario;

public class HorarioMapper {

    public static Horario toEntity(HorarioRequestDTO dto) {
        if (dto == null) return null;
        Horario horario = new Horario();
        horario.setInicio(dto.getInicio());
        horario.setFim(dto.getFim());
        return horario;
    }

    public static HorarioResponseDTO toResponseDTO(Horario horario) {
        if (horario == null) return null;
        HorarioResponseDTO dto = new HorarioResponseDTO();
        dto.setId(horario.getId());
        dto.setInicio(horario.getInicio());
        dto.setFim(horario.getFim());
        return dto;
    }
}
