package de.frudisch.manager.team.controller;

import de.frudisch.manager.team.domain.Team;
import de.frudisch.manager.team.exception.InvalidArgumentException;
import de.frudisch.manager.team.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    public void testSuccessfulCreateTeam() {
        when(teamService.createTeam(eq(createTeamWithoutUUIDID())))
                .thenReturn(createTeamWithUUIDID());

        Team uut = createTeamWithoutUUIDID();

        Resource<Team> response = teamController.createTeam(uut);

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().getId())
                .isEqualTo(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));
        assertThat(response.getContent().getName())
                .isEqualTo("test");
        assertThat(response.getContent().getMembers())
                .containsExactly(UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76"));
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullCreateTeam() {
        teamController.createTeam(null);
    }

    @Test
    public void testSuccessfulUpdateTeam() {
        when(teamService.updateTeam(eq(createTeamWithUUIDID())))
                .thenReturn(createTeamWithUUIDID());

        Team uut = createTeamWithUUIDID();

        Resource<Team> response = teamController.updateTeam(uut);

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().getId())
                .isEqualTo(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));
        assertThat(response.getContent().getName())
                .isEqualTo("test");
        assertThat(response.getContent().getMembers())
                .containsExactly(UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76"));
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullUpdateTeam() {
        teamController.updateTeam(null);
    }

    @Test
    public void testSuccessfulDeleteTeam() {
        when(teamService.deleteTeam(eq(createTeamWithUUIDID())))
                .thenReturn(true);

        Team uut = createTeamWithUUIDID();

        Resource<Team> response = teamController.deleteTeam(uut);

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().getId())
                .isEqualTo(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));
        assertThat(response.getContent().getName())
                .isEqualTo("test");
        assertThat(response.getContent().getMembers())
                .containsExactly(UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76"));
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullDeleteTeam() {
        teamController.deleteTeam(null);
    }

    @Test
    public void testSuccessfulFindTeamByUUID() {
        when(teamService.findTeamByUUID(eq(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"))))
                .thenReturn(createTeamWithUUIDID());

        Resource<Team> response = teamController.findTeamByUUID(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().getId())
                .isEqualTo(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));
        assertThat(response.getContent().getName())
                .isEqualTo("test");
        assertThat(response.getContent().getMembers())
                .containsExactly(UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76"));
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullFindTeamByUUID() {
        teamController.findTeamByUUID(null);
    }

    @Test
    public void testSuccessfulFindTeamByUUIDNoResult() {
        when(teamService.findTeamByUUID(eq(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"))))
                .thenReturn(null);

        Resource<Team> response = teamController.findTeamByUUID(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"));

        assertThat(response)
                .isNull();
    }

    @Test
    public void testSuccessfulFindTeamsByName() {
        when(teamService.findTeamsByName(eq("test")))
                .thenReturn(asList(createTeamWithUUIDID(), createTeamWithUUIDID(), createTeamWithUUIDID()));

        Resources<Resource<Team>> response = teamController.findTeamsByName("test");

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().size())
                .isEqualTo(3);
    }

    @Test(expected = NullPointerException.class)
    public void testPassNullFindTeamsByName() {
        teamController.findTeamsByName(null);
    }

    @Test
    public void testSuccessfulFindTeamsByNameNoResult() {
        when(teamService.findTeamsByName("test2"))
                .thenReturn(null);

        Resources<Resource<Team>> response = teamController.findTeamsByName("test2");

        assertThat(response)
                .isNotNull();
        assertThat(response.getContent().size())
                .isEqualTo(0);
    }

    private Team createTeamWithUUIDID() {
        return Team.builder()
                .id(UUID.fromString("211a80e3-ef8a-4b48-be24-ed477a0c33e6"))
                .members(asList(
                        UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76")
                ))
                .name("test")
                .build();
    }

    private Team createTeamWithoutUUIDID() {
        return Team.builder()
                .members(asList(
                        UUID.fromString("43844c62-38a6-45fc-8642-caedd0798a53"),
                        UUID.fromString("e2c0bb27-b54d-4d5d-82ef-d4cd53f1c7cc"),
                        UUID.fromString("69f4fb49-b48e-4551-a017-f3af4bbaf5bc"),
                        UUID.fromString("0208d3ae-d303-47ac-b565-6b8da7299f76")
                ))
                .name("test")
                .build();
    }

}