package de.frudisch.manager.team.management.web.controller

import de.frudisch.manager.team.management.domain.entity.Team
import de.frudisch.manager.team.management.service.TeamService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(name = "/api/v1/" + "team")
class TeamController (val service: TeamService) {

    @GetMapping("/")
    fun getAll(): List<Team> = service.getAll()

    @GetMapping("")
    fun searchByName(@RequestParam searchTerm: String): List<Team> = service.search(searchTerm)

    @GetMapping("/{uuid}/detail")
    fun getDetailFromTeamByUUID(@PathVariable uuid: UUID): Team = service.getTeamDetail(uuid)

    @PostMapping("/")
    fun createTeam(@RequestBody team: Team): Team = service.create(team)

    @PutMapping("/{uuid}")
    fun updateTeam(@RequestBody team: Team, @PathVariable uuid: UUID): Team = service.update(team, uuid)

    @DeleteMapping("/{uuid}")
    fun deleteTeam(@PathVariable uuid: UUID): Boolean = service.delete(uuid)
}