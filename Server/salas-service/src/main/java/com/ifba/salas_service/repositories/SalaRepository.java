package com.ifba.salas_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}