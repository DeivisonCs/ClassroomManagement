package com.ifba.salas_service.services.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ifba.salas_service.config.RabbitMQConfig;
import com.ifba.salas_service.dtos.UserEvent;
import com.ifba.salas_service.models.Aluno;
import com.ifba.salas_service.models.Professor;
import com.ifba.salas_service.repositories.AlunoRepository;
import com.ifba.salas_service.repositories.ProfessorRepository;

@Component
public class UserEventListener {
    
    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);
    
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    
    public UserEventListener(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }
    
    @RabbitListener(queues = RabbitMQConfig.PROFESSOR_QUEUE)
public void handleProfessorEvent(UserEvent event) {
    logger.info("Recebido evento de professor: {}", event.getEventType());
    
    if ("PROFESSOR_CREATED".equals(event.getEventType())) {
        try {
            Long matricula = Long.parseLong(event.getRegistration());
            if (!professorRepository.existsById(matricula)) {
                Professor professor = new Professor();
                professor.setMatricula(matricula);
                professor.setNome(event.getName());
                
                professorRepository.save(professor);
                logger.info("Professor salvo com sucesso: {}", professor.getNome());
            } else {
                logger.info("Professor já existe com matrícula: {}", matricula);
            }
        } catch (Exception e) {
            logger.error("Erro ao processar evento de professor: {}", e.getMessage(), e);
        }
    }
}
    
    @RabbitListener(queues = RabbitMQConfig.STUDENT_QUEUE)
public void handleStudentEvent(UserEvent event) {
    logger.info("Recebido evento de aluno: {}", event.getEventType());
    
    if ("STUDENT_CREATED".equals(event.getEventType())) {
        try {
            Long matricula = Long.parseLong(event.getRegistration());
            if (!alunoRepository.existsById(matricula)) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(matricula); 
                aluno.setNome(event.getName());
                
                alunoRepository.save(aluno);
                logger.info("Aluno salvo com sucesso: {}", aluno.getNome());
            } else {
                logger.info("Aluno já existe com matrícula: {}", matricula);
            }
        } catch (Exception e) {
            logger.error("Erro ao processar evento de aluno", e);
        }
    }
}
    }