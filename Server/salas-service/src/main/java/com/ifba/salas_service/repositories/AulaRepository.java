package com.ifba.salas_service.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.salas_service.models.Aula;
import com.ifba.salas_service.models.Disciplina;
import com.ifba.salas_service.models.Turma;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    boolean existsByDisciplinaAndTurma(Disciplina disciplina, Turma turma);
    
}
