package com.ifba.salas_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    boolean existsByMatricula(Long matricula);
}
