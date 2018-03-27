package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public abstract class GenericDeserializer<T> implements Deserializer<T> {

    private Class<T> targetType;

    private ObjectMapper objectMapper;

    private ObjectReader reader;

    public GenericDeserializer(Class<T> targetType) {
        this.targetType = targetType;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public GenericDeserializer(Class<T> targetType, ObjectMapper objectMapper) {
        this.targetType = targetType;
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        if(reader == null){
            reader = objectMapper.readerFor(targetType);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if(data == null) return null;
        if(reader == null){
            reader = objectMapper.readerFor(targetType);
        }
        try {
            System.out.println("Deserializing: " + new String(data));
            return reader.readValue(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        reader = null;
    }
}
