package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.frudisch.manager.team.domain.Team;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class TeamSerializer extends GenericSerializer<Team> implements Serializer<Team> {
    public TeamSerializer() {
        super(Team.class);
    }

    public TeamSerializer(ObjectMapper objectMapper) {
        super(Team.class, objectMapper);
    }
}
