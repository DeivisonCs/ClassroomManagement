package com.ifba.ms_user.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ifba.ms_user.config.RabbitMQConfig;
import com.ifba.ms_user.dto.UserEvent;
import com.ifba.ms_user.models.Account;

@Service
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    
    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void publishProfessorCreatedEvent(Account account) {
        UserEvent event = new UserEvent(
            account.getId(), 
            account.getRegistration(), 
            account.getPerson().getName(), 
            "PROFESSOR_CREATED"
        );
        
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.USER_EXCHANGE,
            "professor.created",
            event
        );
    }
    
    public void publishStudentCreatedEvent(Account account) {
        UserEvent event = new UserEvent(
            account.getId(), 
            account.getRegistration(), 
            account.getPerson().getName(), 
            "STUDENT_CREATED"
        );
        
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.USER_EXCHANGE,
            "student.created",
            event
        );
    }
}