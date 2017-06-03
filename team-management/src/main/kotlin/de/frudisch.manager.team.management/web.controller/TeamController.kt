package de.frudisch.manager.team.management.web.controller

import de.frudisch.manager.team.management.domain.TeamRepository
import de.frudisch.manager.team.management.domain.entity.Team
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(name = "/api/v1/" + "team")
class TeamController (val repository: TeamRepository) {

    @GetMapping("/")
    fun getAll(): List<Team> = repository.findAll()

    @GetMapping("")
    fun searchByName(@RequestParam search: String) = null

    @GetMapping("/{uuid}/detail")
    fun getDetailFromTeamByUUID(@PathVariable uuid: UUID) = null

    @PostMapping("/")
    fun createTeam(@RequestBody team: Team) = null

    @PutMapping("/{uuid}")
    fun updateTeam(@RequestBody team: Team, @PathVariable uuid: UUID) = null

    @DeleteMapping("/{uuid}")
    fun deleteTeam(@PathVariable uuid: UUID) = null
}