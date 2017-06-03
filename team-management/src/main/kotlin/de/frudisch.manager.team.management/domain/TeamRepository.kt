package de.frudisch.manager.team.management.domain

import de.frudisch.manager.team.management.domain.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface TeamRepository : JpaRepository<Team, UUID>