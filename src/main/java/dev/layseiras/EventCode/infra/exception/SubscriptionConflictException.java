package dev.layseiras.EventCode.infra.exception;

public class SubscriptionConflictException extends RuntimeException {
    public SubscriptionConflictException(String message) {
        super(message);
    }
}
