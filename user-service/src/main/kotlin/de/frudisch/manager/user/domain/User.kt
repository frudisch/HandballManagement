package de.frudisch.manager.user.domain

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_USER")
data class User(@Id var id: Long,
                @Column(name = "uuid") var uuid: UUID,
                @Column(name = "firstname") var firstname: String,
                @Column(name = "lastname") var lastname: String): Serializable
