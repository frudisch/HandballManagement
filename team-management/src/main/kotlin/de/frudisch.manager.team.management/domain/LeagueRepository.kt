package de.frudisch.manager.team.management.domain

import de.frudisch.manager.team.management.domain.entity.League
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface LeagueRepository : JpaRepository<League, UUID> {
    fun findByName(searchTerm: String): List<League>
}