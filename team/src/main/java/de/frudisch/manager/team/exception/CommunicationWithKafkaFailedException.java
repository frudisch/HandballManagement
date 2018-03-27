package de.frudisch.manager.team.exception;

public class CommunicationWithKafkaFailedException extends RuntimeException{

    public CommunicationWithKafkaFailedException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
