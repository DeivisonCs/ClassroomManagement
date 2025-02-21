package com.ifba.salas_service.exceptions;

public class SalaNotFoundException extends RuntimeException {
    public SalaNotFoundException(String message) {
        super(message);
    }
}