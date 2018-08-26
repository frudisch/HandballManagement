package de.frudisch.manager.team.exception;

public class InvalidArgumentException extends RuntimeException {

    private ErrorMessage errorMessage;

    public InvalidArgumentException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
