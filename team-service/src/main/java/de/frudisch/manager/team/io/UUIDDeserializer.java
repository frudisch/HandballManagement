package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDDeserializer extends GenericDeserializer<UUID>{
    public UUIDDeserializer() {
        super(UUID.class);
    }

    public UUIDDeserializer(ObjectMapper objectMapper) {
        super(UUID.class, objectMapper);
    }
}
