package com.ifba.salas_service.dtos;

import java.io.Serializable;

public class UserEvent implements Serializable {
    private Long id;
    private String registration;
    private String name;
    private String eventType;
    
    public UserEvent() {}
    
    public UserEvent(Long id, String registration, String name, String eventType) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.eventType = eventType;
    }
    
    // Getters e setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRegistration() {
        return registration;
    }
    
    public void setRegistration(String registration) {
        this.registration = registration;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}