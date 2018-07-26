package de.frudisch.manager.user.connector

import de.frudisch.manager.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Stream

@Repository
interface Database : CrudRepository<User, Long>{

    fun findByFirstname(firstname: String): Stream<User>

    fun findByLastname(lastname: String): Stream<User>

    fun findByUuid(uuid: UUID): User

}
