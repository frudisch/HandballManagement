package de.frudisch.manager.team.service;

import de.frudisch.manager.team.connector.TeamConnector;
import de.frudisch.manager.team.domain.Team;
import de.frudisch.manager.team.exception.CommunicationWithKafkaFailedException;
import de.frudisch.manager.team.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
@AllArgsConstructor
public class TeamService {

    private final TeamConnector teamConnector;

    public Team findTeamByUUID(@NonNull UUID uuid) {
        return teamConnector.findTeamByUUID(uuid);
    }

    public Team createTeam(@NonNull Team team) {
        team.setId(UUID.randomUUID());
        try {
            return teamConnector.createTeam(team);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            throw new CommunicationWithKafkaFailedException(Exceptions.DataSaveFailed);
        }
    }

    public boolean deleteTeam(@NonNull Team team) {
        try {
            return teamConnector.deleteTeam(team.getId());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            throw new CommunicationWithKafkaFailedException(Exceptions.DataSaveFailed);
        }
    }

    public Team updateTeam(@NonNull Team team) {
        if (team.getId() != null) {
            throw new InvalidArgumentException(Exceptions.TeamUpdateUUIDNotSet);
        }

        try {
            return teamConnector.updateTeam(team);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            throw new CommunicationWithKafkaFailedException(Exceptions.DataSaveFailed);
        }
    }

    public List<Team> findTeamsByName(@NonNull String name) {
        return teamConnector.findTeamByName(name);
    }
}
