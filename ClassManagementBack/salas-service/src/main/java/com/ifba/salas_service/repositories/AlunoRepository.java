package com.ifba.salas_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
   boolean existsByMatricula(Long matricula);
   }
