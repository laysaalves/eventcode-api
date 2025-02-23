package dev.layseiras.EventCode.infra.exception;

public class UserIndicadorNotFoundException extends RuntimeException {
    public UserIndicadorNotFoundException(String message) {
        super(message);
    }
}
