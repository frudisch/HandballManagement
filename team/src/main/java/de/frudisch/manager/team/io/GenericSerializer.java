package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public abstract class GenericSerializer<T> implements Serializer<T> {

    private Class<T> targetType;

    private ObjectMapper objectMapper;

    private ObjectWriter writer;

    public GenericSerializer(Class<T> targetType) {
        this.targetType = targetType;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public GenericSerializer(Class<T> targetType, ObjectMapper objectMapper) {
        this.targetType = targetType;
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        if(writer == null){
            writer = objectMapper.writerFor(targetType);
        }
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if(data == null) return null;
        if(writer == null){
            writer = objectMapper.writerFor(targetType);
        }
        try {
            System.out.println("Serialization: " + data);
            return writer.writeValueAsBytes(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        writer = null;
    }
}
