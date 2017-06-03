package de.frudisch.manager.team.management.service

import de.frudisch.manager.team.management.domain.TeamRepository
import org.springframework.stereotype.Service

@Service
class TeamService(val teamRepository: TeamRepository) {

    fun hello() = "Hello!"

    fun getByName(name: String) = null

}
