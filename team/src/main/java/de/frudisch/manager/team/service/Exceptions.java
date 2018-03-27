package de.frudisch.manager.team.service;

import de.frudisch.manager.team.exception.ErrorMessage;
import lombok.Getter;

@Getter
public enum Exceptions implements ErrorMessage {
    TeamUpdateUUIDNotSet("Please set the UUID when calling the Update Endpoint!"),
    DataSaveFailed("The changes to the data could be saved!");

    private String message;

    Exceptions(String message) {
        this.message = message;
    }
}
