package de.frudisch.manager.team;

import de.frudisch.manager.team.domain.Team;
import de.frudisch.manager.team.io.ListDeserializer;
import de.frudisch.manager.team.io.ListSerializer;
import de.frudisch.manager.team.io.TeamDeserializer;
import de.frudisch.manager.team.io.TeamSerializer;
import de.frudisch.manager.team.io.TeamWrapper;
import de.frudisch.manager.team.io.UUIDDeserializer;
import de.frudisch.manager.team.io.UUIDSerializer;
import de.frudisch.manager.team.io.UUIDWrapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class ApplicationConfiguration {

    public static final String RAW_DATA = "team-raw";

    public static final String UUID_TEAM_DATA = "team-lc-uuid-team";

    public static final String NAME_UUIDS_DATA = "team-lc-name-uuids";

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "team-service");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());

        StreamsConfig streamsConfig = new StreamsConfig(props);

        System.err.println("streamsconfig: " + streamsConfig.defaultValueSerde());
        System.err.println("streamsconfig: " + streamsConfig.defaultKeySerde());

        return streamsConfig;
    }

    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TeamDeserializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 100);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TeamSerializer.class);
        return props;
    }

    @Bean
    public Serde<UUID> uuidSerde(UUIDSerializer uuidSerializer, UUIDDeserializer uuidDeserializer) {
        return Serdes.serdeFrom(uuidSerializer, uuidDeserializer);
    }

    @Bean
    public Serde<Team> teamSerde(TeamSerializer teamSerializer, TeamDeserializer teamDeserializer) {
        return Serdes.serdeFrom(teamSerializer, teamDeserializer);
    }

    @Bean
    public Serde<List<UUID>> uuidListSerde() {
        return Serdes.serdeFrom(
                new ListSerializer<>(UUID.class),
                new ListDeserializer<>(UUID.class)
        );
    }

}
