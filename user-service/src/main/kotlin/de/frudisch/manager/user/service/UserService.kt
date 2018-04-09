package de.frudisch.manager.user.service

import de.frudisch.manager.user.connector.UserConnector
import de.frudisch.manager.user.domain.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.UUID

@Component
class UserService(val userConnector: UserConnector) {

    fun selectUser(uuid: UUID): Mono<User> {
        return userConnector.selectUser(uuid).toMono()
    }

    fun createUser(user: User): Mono<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteUser(user: User): Mono<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateUser(user: User): Mono<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}