package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.frudisch.manager.team.domain.Team;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

@Component
public class TeamDeserializer extends GenericDeserializer<Team> implements Deserializer<Team> {

    public TeamDeserializer() {
        super(Team.class);
    }

    public TeamDeserializer(ObjectMapper objectMapper) {
        super(Team.class, objectMapper);
    }
}
