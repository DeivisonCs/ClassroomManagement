package com.ifba.salas_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
   @Query("SELECT t FROM Turma t LEFT JOIN FETCH t.alunos WHERE t.id = :id")
   Optional<Turma> findByIdWithAlunos(@Param("id") Long id);
   }
