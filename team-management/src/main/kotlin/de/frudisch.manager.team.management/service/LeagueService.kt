package de.frudisch.manager.team.management.service

import de.frudisch.manager.team.management.domain.LeagueRepository
import de.frudisch.manager.team.management.domain.entity.League
import org.springframework.stereotype.Service
import java.util.*

@Service
class LeagueService(val leagueRepository: LeagueRepository) {

    fun getAll(): List<League> {
        return leagueRepository.findAll()
    }

    fun search(searchTerm: String): List<League> {
        return leagueRepository.findByName(searchTerm)
    }

    fun getTeamDetail(uuid: UUID): League {
        return leagueRepository.findOne(uuid)
    }

    fun create(league: League): League {
        if(leagueRepository.exists(league.uuid)){
            throw Error()
        }
        return leagueRepository.save(league)
    }

    fun update(league: League, uuid: UUID): League {
        if(leagueRepository.exists(uuid)){
            return leagueRepository.saveAndFlush(league)
        }else{
            throw Error()
        }
    }

    fun delete(uuid: UUID): Boolean {
        leagueRepository.delete(uuid)

        return true
    }

}
