package de.frudisch.manager.team.management.web.controller

import de.frudisch.manager.team.management.domain.entity.League
import de.frudisch.manager.team.management.service.LeagueService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(name = "/api/v1/" + "league")
class LeagueController(val leagueService: LeagueService){

    @GetMapping("/")
    fun getAll() = leagueService.getAll()

    @GetMapping("")
    fun searchByName(@RequestParam search: String) = leagueService.search(search)

    @GetMapping("/{uuid}/detail")
    fun getDetailFromTeamByUUID(@PathVariable uuid: UUID) = leagueService.getTeamDetail(uuid)

    @PostMapping("/")
    fun createTeam(@RequestBody league: League) = leagueService.create(league)

    @PutMapping("/{uuid}")
    fun updateTeam(@RequestBody league: League, @PathVariable uuid: UUID) = leagueService.update(league, uuid)

    @DeleteMapping("/{uuid}")
    fun deleteTeam(@PathVariable uuid: UUID) = leagueService.delete(uuid)
}