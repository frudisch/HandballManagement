package de.frudisch.manager.team.management.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_REGION")
data class Region(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val uuid: UUID? = null,

        val name: String? = null,

        val identifier: String? = null,

        val landIdentifier: String? = null)
