package de.frudisch.manager.team;

import de.frudisch.manager.team.domain.Team;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaBootstrapConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.frudisch.manager.team.ApplicationConfiguration.RAW_DATA;

@Configuration
public class KafkaConfiguration {

    public static final String TEAM_DATA_STORE = "team-data-store";

    public static final String NAME_TEAM_DATA_STORE = "name-uuid-data-store";

    @Bean
    public KStream<UUID, Team> streamTopology(StreamsBuilder builder,
                                        Serde<UUID> uuidSerde,
                                        Serde<Team> teamSerde,
                                        Serde<List<UUID>> uuidListSerde
    ) {
        KStream<UUID, Team> stream = builder.stream(RAW_DATA, Consumed.with(uuidSerde, teamSerde));

        stream.groupByKey(Serialized.with(uuidSerde, teamSerde))
                .reduce(((oldOne, newOne) -> newOne), Materialized.<UUID, Team, KeyValueStore<Bytes, byte[]>>as(TEAM_DATA_STORE)
                        .withKeySerde(uuidSerde)
                        .withValueSerde(teamSerde));

        stream.map((key, value) -> value == null ? new KeyValue<>("", key) : new KeyValue<>(value.getName(), key))
                .groupByKey(Serialized.with(Serdes.String(), uuidSerde))
                .aggregate(ArrayList::new, (key, value, aggregate) -> {
                    aggregate.add(value);
                    return aggregate;
                }, Materialized.<String, List<UUID>, KeyValueStore<Bytes, byte[]>>as(NAME_TEAM_DATA_STORE)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(uuidListSerde));

        return stream;
    }


    /*
    @Bean
    public Topology topology(Serde<UUID> uuidSerde,
                             Serde<Team> teamSerde,
                             Serde<List<UUID>> uuidListSerde
    ) {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<UUID, Team> stream = builder.stream(RAW_DATA, Consumed.with(uuidSerde, teamSerde));

        stream.groupByKey(Serialized.with(uuidSerde, teamSerde))
                .reduce(((oldOne, newOne) -> newOne), Materialized.<UUID, Team, KeyValueStore<Bytes, byte[]>>as(TEAM_DATA_STORE)
                        .withKeySerde(uuidSerde)
                        .withValueSerde(teamSerde));

        stream.map((key, value) -> {
                    return value == null ? new KeyValue<>("", key), new KeyValue<>(value.getName(), key);
                })
                .groupByKey(Serialized.with(Serdes.String(), uuidSerde))
                .aggregate(ArrayList::new, (key, value, aggregate) -> {
                    aggregate.add(value);
                    return aggregate;
                }, Materialized.<String, List<UUID>, KeyValueStore<Bytes, byte[]>>as(NAME_TEAM_DATA_STORE)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(uuidListSerde));

        return builder.build();
    }


    @Bean
    public KafkaStreams kafkaStreams(Topology topology, StreamsConfig streamsConfig) {
        return new KafkaStreams(topology, streamsConfig, new DefaultKafkaClientSupplier());
    }
    */

    /*
    @Bean
    public ReadOnlyKeyValueStore<UUID, Team> teamDataStore(KafkaStreams kafkaStreams) throws InterruptedException {
        return kafkaStreams.store(TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
    }

    @Bean
    public ReadOnlyKeyValueStore<String, List<UUID>> nameUUIDDataStore(KafkaStreams kafkaStreams) throws InterruptedException {
        return kafkaStreams.store(NAME_TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
    }
    */

    /*
    @Bean
    public KStream<UUID, Team> teamRawStream(StreamsBuilder streamsBuilder, Serde<UUID> uuidSerde, Serde<Team> teamSerde) {
        return streamsBuilder.stream(RAW_DATA, Consumed.with(uuidSerde, teamSerde));
    }

    @Bean
    public ReadOnlyKeyValueStore<UUID, Team> uuidTeamKTable(
            KStream<UUID, Team> userKStream, Serde<UUID> uuidSerde, Serde<Team> teamSerde, StreamsBuilder streamsBuilder
    ) {
        userKStream.groupByKey(Serialized.with(uuidSerde, teamSerde))
                .reduce(((value1, value2) -> {
                    System.out.print("reducing! " + value1 + " => " + value2);
                    return value2;
                }))
                .toStream()
                .to(UUID_TEAM_DATA, Produced.with(uuidSerde, teamSerde));

        try {
            KTable<UUID, Team> table = streamsBuilder.table(UUID_TEAM_DATA,
                    Materialized.<UUID, Team, KeyValueStore<Bytes, byte[]>>as(TEAM_DATA_STORE)
                            .withKeySerde(uuidSerde)
                            .withValueSerde(teamSerde));
        }catch (Exception e){
            e.printStackTrace();
        }

        //return kafkaStreams.store(table.queryableStoreName(), QueryableStoreTypes.keyValueStore());
        return null;
    }

    @Bean
    public ReadOnlyKeyValueStore<String, List<UUID>> nameUUIDKTable(KStream<UUID, Team> userKStream, Serde<UUID> uuidSerde, Serde<Team> teamSerde, Serde<List<UUID>> uuidListSerde, KafkaStreams kafkaStreams) {
        KTable<String, List<UUID>> aggregate1 = userKStream
                .map((key, value) -> new KeyValue<>(value.getName(), key))
                .groupByKey()
                .aggregate(ArrayList::new, ((key, value, aggregate) -> {
                    aggregate.add(value);
                    return aggregate;
                }), Materialized.<String, List<UUID>, KeyValueStore<Bytes, byte[]>>as(NAME_TEAM_DATA_STORE)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(uuidListSerde));

        System.out.println("store name: " + aggregate1.queryableStoreName());


        if (!kafkaStreams.state().isRunning()) {
            kafkaStreams.start();
        }

        return null;
    }

    @Bean
    public KafkaStreams kafkaStreams(StreamsBuilderFactoryBean streamsBuilderFactoryBean) {
        streamsBuilderFactoryBean.start();
        KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();

        if (!kafkaStreams.state().isRunning()) {
            kafkaStreams.start();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

        return kafkaStreams;
    }
    */

    /*
    @Bean
    public ReadOnlyKeyValueStore<UUID, Team> teamDataStore(KafkaStreams kafkaStreams) {
        return kafkaStreams.store(TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
        //return new KeyValueStoreBuilder<>(
        //        new RocksDbKeyValueBytesStoreSupplier(TEAM_DATA_STORE), uuidSerde, teamSerde, Time.SYSTEM)
        //        .build();
    }

    @Bean
    public ReadOnlyKeyValueStore<String, List<UUID>> nameUUIDDataStore(KafkaStreams kafkaStreams, KTable<String, List<UUID>> nameUUIDKTable) {
        return kafkaStreams.store(nameUUIDKTable.queryableStoreName(), QueryableStoreTypes.keyValueStore());
        //return new KeyValueStoreBuilder<>(
        //        new RocksDbKeyValueBytesStoreSupplier(NAME_TEAM_DATA_STORE), Serdes.String(), uuidListSerde, Time.SYSTEM)
        //        .build();
    }

    */
}
