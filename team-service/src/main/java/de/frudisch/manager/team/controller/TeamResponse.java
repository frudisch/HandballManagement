package de.frudisch.manager.team.controller;

import de.frudisch.manager.team.domain.Team;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

abstract class TeamResponse {

    static Resource<Team> toResource(Team team) {
        if(team == null) {
            return null;
        }

        Resource<Team> teamResource = new Resource<>(team);

        teamResource.add(linkTo(methodOn(TeamController.class).createTeam(team)).withSelfRel());
        teamResource.add(linkTo(methodOn(TeamController.class).findTeamByUUID(team.getId())).withSelfRel());
        teamResource.add(linkTo(methodOn(TeamController.class).deleteTeam(team)).withSelfRel());
        teamResource.add(linkTo(methodOn(TeamController.class).updateTeam(team)).withSelfRel());

        return teamResource;
    }

    static Resources<Resource<Team>> toResource(List<Team> teamList) {
        if (teamList == null || teamList.size() == 0) {
            return new Resources<>(Collections.emptyList());
        }
        Resources<Resource<Team>> resourceList = new Resources<>(teamList.stream()
                .map(TeamResponse::toResource)
                .collect(Collectors.toList()));

        resourceList.add(linkTo(methodOn(TeamController.class).findTeamsByName(teamList.get(0).getName())).withSelfRel());

        return resourceList;
    }
}
