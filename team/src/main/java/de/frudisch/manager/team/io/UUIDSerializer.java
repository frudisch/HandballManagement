package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDSerializer extends GenericSerializer<UUID> {
    public UUIDSerializer() {
        super(UUID.class);
    }

    public UUIDSerializer(ObjectMapper objectMapper) {
        super(UUID.class, objectMapper);
    }
}
