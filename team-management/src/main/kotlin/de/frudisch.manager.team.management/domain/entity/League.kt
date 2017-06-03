package de.frudisch.manager.team.management.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_LEAGUE")
data class League(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val uuid: UUID? = null,

        val name: String? = null)