package de.frudisch.manager.team.io;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class UUIDWrapper implements Serde<UUID>{

    private final UUIDSerializer uuidSerializer;
    private final UUIDDeserializer uuidDeserializer;

    public UUIDWrapper() {
        uuidSerializer = new UUIDSerializer();
        uuidDeserializer = new UUIDDeserializer();
    }

    public UUIDWrapper(UUIDSerializer uuidSerializer, UUIDDeserializer uuidDeserializer) {
        this.uuidSerializer = uuidSerializer;
        this.uuidDeserializer = uuidDeserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.uuidSerializer.configure(configs, isKey);
        this.uuidDeserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        this.uuidSerializer.close();
        this.uuidDeserializer.close();
    }

    @Override
    public Serializer<UUID> serializer() {
        return uuidSerializer;
    }

    @Override
    public Deserializer<UUID> deserializer() {
        return uuidDeserializer;
    }
}
