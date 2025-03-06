package com.ifba.salas_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_EXCHANGE = "user.exchange";
    public static final String PROFESSOR_QUEUE = "professor.queue";
    public static final String STUDENT_QUEUE = "student.queue";
    
    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }
    
    @Bean
    public Queue professorQueue() {
        return new Queue(PROFESSOR_QUEUE, true);
    }
    
    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_QUEUE, true);
    }
    
    @Bean
    public Binding professorBinding(DirectExchange userExchange, Queue professorQueue) {
        return BindingBuilder.bind(professorQueue)
                .to(userExchange)
                .with("professor.created");
    }
    
    @Bean
    public Binding studentBinding(DirectExchange userExchange, Queue studentQueue) {
        return BindingBuilder.bind(studentQueue)
                .to(userExchange)
                .with("student.created");
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, 
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}