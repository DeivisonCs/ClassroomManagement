package com.ifba.salas_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifba.salas_service.dtos.SalaDTO;
import com.ifba.salas_service.exceptions.SalaNotFoundException;
import com.ifba.salas_service.mappers.SalaMapper;
import com.ifba.salas_service.repositories.SalaRepository;
import com.ifba.salas_service.models.Sala;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    public List<SalaDTO> listarSalas() {
        return salaRepository.findAll().stream()
                .map(SalaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SalaDTO buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new SalaNotFoundException("Sala não encontrada com id: " + id));
        return SalaMapper.toDTO(sala);
    }

    public SalaDTO salvarSala(SalaDTO salaDTO) {
        Sala sala = SalaMapper.toEntity(salaDTO);
        Sala savedSala = salaRepository.save(sala);
        return SalaMapper.toDTO(savedSala);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new SalaNotFoundException("Sala não encontrada com id: " + id);
        }
        salaRepository.deleteById(id);
    }
}