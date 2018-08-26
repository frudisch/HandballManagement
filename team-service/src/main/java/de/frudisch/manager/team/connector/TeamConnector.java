package de.frudisch.manager.team.connector;

import de.frudisch.manager.team.domain.Team;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.StreamsBuilderFactoryBean;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.frudisch.manager.team.ApplicationConfiguration.RAW_DATA;
import static de.frudisch.manager.team.KafkaConfiguration.NAME_TEAM_DATA_STORE;
import static de.frudisch.manager.team.KafkaConfiguration.TEAM_DATA_STORE;

@Component
@AllArgsConstructor
public class TeamConnector {

    private final KafkaTemplate<UUID, Team> producer;

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    public Team createTeam(Team team) throws InterruptedException, ExecutionException, TimeoutException {
        return sendTeamToRawTopic(team.getId(), team);
    }

    public Team findTeamByUUID(UUID uuid) {
        return getUUIDTeamStore().get(uuid);
    }

    public Boolean deleteTeam(UUID uuid) throws InterruptedException, ExecutionException, TimeoutException {
        return sendTeamToRawTopic(uuid, null) != null;
    }

    public Team updateTeam(Team team) throws InterruptedException, ExecutionException, TimeoutException {
        return sendTeamToRawTopic(team.getId(), team);
    }

    public List<Team> findTeamByName(String name) {
        return Stream.of(getNameUUIDStore().get(name))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(getUUIDTeamStore()::get)
                .collect(Collectors.toList());
    }

    private Team sendTeamToRawTopic(UUID key, Team team) throws InterruptedException, ExecutionException, TimeoutException {
        ListenableFuture<SendResult<UUID, Team>> sendResultListenableFuture = producer.send(RAW_DATA, key, team);

        sendResultListenableFuture.get(1000, TimeUnit.MILLISECONDS);

        return team;
    }

    ReadOnlyKeyValueStore<UUID, Team> getUUIDTeamStore() {
        return streamsBuilderFactoryBean.getKafkaStreams().store(TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
        // return kafkaStreams.store(TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
    }

    ReadOnlyKeyValueStore<String, List<UUID>> getNameUUIDStore() {
        return streamsBuilderFactoryBean.getKafkaStreams().store(NAME_TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
        // return kafkaStreams.store(NAME_TEAM_DATA_STORE, QueryableStoreTypes.keyValueStore());
    }
}
