package de.frudisch.manager.team.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ListDeserializer<T> implements Deserializer<List<T>> {

    private final ObjectMapper objectMapper;

    private final Class<T> clazz;

    private ObjectReader reader;

    public ListDeserializer(Class<T> clazz) {
        this.clazz = clazz;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public List<T> deserialize(String topic, byte[] data) {
        if(data == null) {
            System.out.println("list data null!!!");
            return null;
        }
        if(this.reader == null){
            this.reader = objectMapper.readerFor(objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, clazz));
        }
        try {
            return this.reader.readValue(data);
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
