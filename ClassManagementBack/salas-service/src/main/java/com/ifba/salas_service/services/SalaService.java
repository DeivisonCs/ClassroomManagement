package com.ifba.salas_service.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.request.SalaRequestDTO;
import com.ifba.salas_service.dtos.response.SalaResponseDTO;
import com.ifba.salas_service.exceptions.ResourceNotFoundException;
import com.ifba.salas_service.mappers.SalaMapper;
import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.repositories.SalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaResponseDTO criarSala(SalaRequestDTO dto) {
        Sala sala = SalaMapper.toEntity(dto);
        Sala saved = salaRepository.save(sala);
        return SalaMapper.toResponseDTO(saved);
    }

    public SalaResponseDTO atualizarSala(Long id, SalaRequestDTO dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

        sala.setNome(dto.getNome());

        Sala updated = salaRepository.save(sala);
        return SalaMapper.toResponseDTO(updated);
    }

    public SalaResponseDTO buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));
        return SalaMapper.toResponseDTO(sala);
    }

    public List<SalaResponseDTO> listarTodas() {
        List<Sala> salas = salaRepository.findAll();
        return salas.stream()
                .map(SalaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala não encontrada");
        }
        salaRepository.deleteById(id);
    }
}
