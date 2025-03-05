package com.ifba.salas_service.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.repositories.SalaRepository;


@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> buscarPorCodigo(String codigo) {
        return salaRepository.findByCodigo(codigo);
    }

    public Sala salvarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public void deletarSala(Long id) {
        salaRepository.deleteById(id);
    }
}
