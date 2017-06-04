package de.frudisch.manager.team.management.service

import de.frudisch.manager.team.management.domain.TeamRepository
import de.frudisch.manager.team.management.domain.entity.Team
import org.springframework.stereotype.Service
import java.util.*

@Service
class TeamService(val teamRepository: TeamRepository) {

    fun getAll(): List<Team> {
        return teamRepository.findAll()
    }

    fun search(searchTerm: String): List<Team> {
        return teamRepository.findByName(searchTerm)
    }

    fun getTeamDetail(uuid: UUID): Team {
        return teamRepository.findOne(uuid)
    }

    fun create(team: Team): Team {
        if(teamRepository.exists(team.uuid)){
            throw Error()
        }
        return teamRepository.save(team)
    }

    fun update(team: Team, uuid: UUID): Team {
        if(teamRepository.exists(uuid)){
            return teamRepository.saveAndFlush(team)
        }else{
            throw Error()
        }
    }

    fun delete(uuid: UUID): Boolean {
        teamRepository.delete(uuid)

        return true
    }

}
