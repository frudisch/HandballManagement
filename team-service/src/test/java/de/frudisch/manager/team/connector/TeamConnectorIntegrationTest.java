package de.frudisch.manager.team.connector;

import de.frudisch.manager.team.domain.Team;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static de.frudisch.manager.team.ApplicationConfiguration.NAME_UUIDS_DATA;
import static de.frudisch.manager.team.ApplicationConfiguration.RAW_DATA;
import static de.frudisch.manager.team.ApplicationConfiguration.UUID_TEAM_DATA;
import static de.frudisch.manager.team.KafkaConfiguration.NAME_TEAM_DATA_STORE;
import static de.frudisch.manager.team.KafkaConfiguration.TEAM_DATA_STORE;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TeamConnectorIntegrationTest {

    private static List<UUID> uuidList = asList(
            UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"),
            UUID.fromString("bad91a1d-e856-4e5b-b929-5f92d68a2e56"),
            UUID.fromString("d67820b9-408e-4892-b3fd-1b7d86de0c25"),
            UUID.fromString("84c94100-953b-4e91-a659-2d17a00c5671"),
            UUID.fromString("c701ec97-76fe-4556-a951-b30e715f8d46")
    );

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RAW_DATA, UUID_TEAM_DATA, NAME_UUIDS_DATA, TEAM_DATA_STORE, NAME_TEAM_DATA_STORE);

    @Autowired
    private KafkaTemplate<UUID, Team> kafkaTemplate;

    @Autowired
    private TeamConnector teamConnector;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    @Before
    public void setup() {
        createTraffic();
    }

    @Test
    public void testCreateTeam() throws InterruptedException, ExecutionException, TimeoutException {
        Team uut = createTeam(UUID.fromString("fd056787-3a25-4dbc-b17f-87baa2d3a183"));
        Team result = teamConnector.createTeam(uut);

        assertThat(uut).isEqualTo(result);
    }

    @Test
    public void testFindTeamByUUID() {
        Team uut = teamConnector.findTeamByUUID(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        assertThat(uut)
                .isNotNull();
        assertThat(uut.getId())
                .isEqualTo(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));
        assertThat(uut.getMembers().size())
                .isEqualTo(5);
        assertThat(uut.getName())
                .isEqualTo("testf6ee61c4-e2e2-4094-81c6-c1e69f5c1467");
    }

    @Test
    public void testFindTeamByName() {
        List<Team> uut = teamConnector.findTeamByName("testf6ee61c4-e2e2-4094-81c6-c1e69f5c1467");

        assertThat(uut)
                .isNotNull();
        assertThat(uut.size())
                .isEqualTo(1);
        assertThat(uut.get(0).getId())
                .isEqualTo(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));
        assertThat(uut.get(0).getMembers().size())
                .isEqualTo(5);
        assertThat(uut.get(0).getName())
                .isEqualTo("testf6ee61c4-e2e2-4094-81c6-c1e69f5c1467");
    }

    @Test
    public void testDeleteTeam() throws InterruptedException, ExecutionException, TimeoutException {
        Boolean result = teamConnector.deleteTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        assertThat(result)
                .isTrue();

        Team select = teamConnector.findTeamByUUID(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        assertThat(select)
                .isNull();
    }

    @Test
    public void testUpdateTeam() throws InterruptedException, ExecutionException, TimeoutException {
        Team uut = createTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));
        uut.setName("updatedTest!");
        uut.setMembers(null);

        Team result = teamConnector.updateTeam(uut);

        assertThat(result)
                .isNotNull();

        Team select = teamConnector.findTeamByUUID(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        assertThat(select)
                .isNotNull();
        assertThat(select.getName())
                .isEqualTo("updatedTest!");
        assertThat(select.getMembers())
                .isEqualTo(null);
        assertThat(select.getId())
                .isEqualTo(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

    }

    private void createTraffic() {
        uuidList.forEach(uuid -> kafkaTemplate.send(new ProducerRecord<>(RAW_DATA, UUID.randomUUID(), createTeam(uuid))));
    }

    private Team createTeam(UUID uuid) {
        return Team.builder()
                .name("test" + uuid.toString())
                .members(asList(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ))
                .id(uuid)
                .build();
    }

    @AfterClass
    public static void shutdownClass() throws Exception {
        embeddedKafka.destroy();
    }

}