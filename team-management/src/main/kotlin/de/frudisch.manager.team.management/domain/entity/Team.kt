package de.frudisch.manager.team.management.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_TEAM")
data class Team(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val uuid: UUID? = null,

        val name: String? = null,

        val organisationUUID : UUID? = null,

        val league: League? = null)