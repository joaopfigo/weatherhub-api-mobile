package com.joaopedro.weatherhub.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
} // Usada para indicar que um recurso específico não foi encontrado. O construtor recebe uma mensagem que descreve o motivo da exceção, facilitando a identificação do problema quando a exceção for lançada.