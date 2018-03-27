package de.frudisch.manager.team.io;

import de.frudisch.manager.team.domain.Team;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TeamWrapper implements Serde<Team> {

    private final TeamSerializer teamSerializer;
    private final TeamDeserializer teamDeserializer;

    public TeamWrapper() {
        teamSerializer = new TeamSerializer();
        teamDeserializer = new TeamDeserializer();
    }

    public TeamWrapper(TeamSerializer teamSerializer, TeamDeserializer teamDeserializer) {
        this.teamSerializer = teamSerializer;
        this.teamDeserializer = teamDeserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.teamSerializer.configure(configs, isKey);
        this.teamDeserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        this.teamSerializer.close();
        this.teamDeserializer.close();
    }

    @Override
    public Serializer<Team> serializer() {
        return teamSerializer;
    }

    @Override
    public Deserializer<Team> deserializer() {
        return teamDeserializer;
    }
}
