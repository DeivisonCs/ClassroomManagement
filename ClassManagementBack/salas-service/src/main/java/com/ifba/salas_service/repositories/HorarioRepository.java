package com.ifba.salas_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    }
