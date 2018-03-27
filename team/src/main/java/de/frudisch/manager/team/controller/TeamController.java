package de.frudisch.manager.team.controller;

import de.frudisch.manager.team.domain.Team;
import de.frudisch.manager.team.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping(
            value = "/{uuid}",
            produces = {
                    "application/json",
                    "application/v1+json"
            }
    )
    public Resource<Team> findTeamByUUID(@PathVariable(value = "uuid")
                                         @NonNull
                                                 UUID uuid) {
        return TeamResponse.toResource(teamService.findTeamByUUID(uuid));
    }

    @GetMapping(
            produces = {
                    "application/json",
                    "application/v1+json"
            }
    )
    public Resources<Resource<Team>> findTeamsByName(@RequestParam(value = "name")
                                                     @NonNull
                                                             String name) {
        return TeamResponse.toResource(teamService.findTeamsByName(name));
    }

    @PostMapping(
            produces = {
                    "application/json",
                    "application/v1+json"
            }
    )
    public Resource<Team> createTeam(
            @RequestBody
            @NonNull
                    Team team) {
        return TeamResponse.toResource(teamService.createTeam(team));
    }

    @PutMapping(
            produces = {
                    "application/json",
                    "application/v1+json"
            }
    )
    public Resource<Team> updateTeam(
            @RequestBody
            @NonNull
                    Team team) {
        return TeamResponse.toResource(teamService.updateTeam(team));
    }

    @DeleteMapping(
            produces = {
                    "application/json",
                    "application/v1+json"
            }
    )
    public Resource<Team> deleteTeam(
            @RequestBody
            @NonNull
                    Team team) {
        teamService.deleteTeam(team);
        return TeamResponse.toResource(team);
    }
}
