package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListSerializer<T> implements Serializer<List<T>> {

    private final ObjectMapper objectMapper;

    private final Class<T> clazz;

    private ObjectWriter writer;

    public ListSerializer(Class<T> clazz) {
        this.clazz = clazz;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, List<T> data) {
        if (this.writer == null) {
            this.writer = objectMapper.writerFor(objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, clazz));
        }
        try {
            return this.writer.writeValueAsBytes(data);
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
