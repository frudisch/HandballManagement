package de.frudisch.manager.team.service;

import de.frudisch.manager.team.connector.TeamConnector;
import de.frudisch.manager.team.domain.Team;
import de.frudisch.manager.team.exception.InvalidArgumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamConnector teamConnector;

    @InjectMocks
    private TeamService teamService;

    @Test
    public void testCreateTeam() throws InterruptedException, ExecutionException, TimeoutException {
        Team uut = createTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        when(teamConnector.createTeam(eq(uut)))
                .thenReturn(uut);

        Team result = teamService.createTeam(uut);

        assertThat(result)
                .isNotNull();
        assertThat(result.getId())
                .isOfAnyClassIn(UUID.class);
        assertThat(result.getName())
                .isEqualTo("testf6ee61c4-e2e2-4094-81c6-c1e69f5c1467");
        assertThat(result.getMembers().size())
                .isEqualTo(5);
    }

    @Test
    public void testUpdateTeam() throws InterruptedException, ExecutionException, TimeoutException {
        Team uut = Team.builder()
                .name("test")
                .members(asList(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID()
                ))
                .id(null)
                .build();

        when(teamConnector.updateTeam(eq(uut)))
                .thenReturn(Team.builder()
                        .name("test")
                        .members(asList(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID()
                        ))
                        .id(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"))
                        .build());

        Team result = teamService.updateTeam(uut);

        assertThat(result)
                .isNotNull();
        assertThat(result.getId())
                .isEqualTo(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));
        assertThat(result.getName())
                .isEqualTo("test");
        assertThat(result.getMembers().size())
                .isEqualTo(5);
    }

    @Test
    public void testDeleteTeam() throws InterruptedException, ExecutionException, TimeoutException {
        when(teamConnector.deleteTeam(eq(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"))))
                .thenReturn(true);

        boolean result = teamService.deleteTeam(createTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467")));

        assertThat(result)
                .isNotNull();
        assertThat(result)
                .isTrue();
    }

    @Test
    public void testFindTeamByUUID() {
        Team uut = createTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        when(teamConnector.findTeamByUUID(eq(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"))))
                .thenReturn(uut);

        Team result = teamService.findTeamByUUID(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        assertThat(result)
                .isNotNull();
        assertThat(result.getId())
                .isEqualTo(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));
        assertThat(result.getName())
                .isEqualTo("testf6ee61c4-e2e2-4094-81c6-c1e69f5c1467");
        assertThat(result.getMembers().size())
                .isEqualTo(5);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testUpdateTeamExceptionNoUUID() {
        Team uut = createTeam(UUID.fromString("f6ee61c4-e2e2-4094-81c6-c1e69f5c1467"));

        teamService.updateTeam(uut);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateTeamNullPass() {
        teamService.createTeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateTeamNullPass() {
        teamService.updateTeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteTeamNullPass() {
        teamService.deleteTeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindTeamByUUIDNullPass() {
        teamService.findTeamByUUID(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindTeamByNameNullPass() {
        teamService.findTeamsByName(null);
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

}