package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.HorarioRequestDTO;
import com.ifba.salas_service.dtos.response.HorarioResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.HorarioMapper;
import com.ifba.salas_service.models.Horario;
import com.ifba.salas_service.repositories.HorarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioResponseDTO criarHorario(HorarioRequestDTO dto) {
        Horario horario = HorarioMapper.toEntity(dto);
        Horario saved = horarioRepository.save(horario);
        return HorarioMapper.toResponseDTO(saved);
    }

    public HorarioResponseDTO atualizarHorario(Long id, HorarioRequestDTO dto) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        horario.setInicio(dto.getInicio());
        horario.setFim(dto.getFim());
        return HorarioMapper.toResponseDTO(horarioRepository.save(horario));
    }

    public HorarioResponseDTO buscarPorId(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        return HorarioMapper.toResponseDTO(horario);
    }

    public List<HorarioResponseDTO> listarTodos() {
        return horarioRepository.findAll().stream()
                .map(HorarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Horário não encontrado");
        }
        horarioRepository.deleteById(id);
    }
}
