package de.frudisch.manager.team.management.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface LeagueRepository : JpaRepository<LeagueRepository, UUID>