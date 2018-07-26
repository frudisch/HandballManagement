package de.frudisch.manager.user.service

import de.frudisch.manager.user.connector.Database
import de.frudisch.manager.user.domain.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class ReadService(val database: Database) {

    fun selectUser(uuid: UUID): Mono<User> {
        return Mono.just(database.findByUuid(uuid))
    }

    fun selectByFirstname(firstname: String): Flux<User> {
        return Flux.fromStream(database.findByFirstname(firstname))
    }

    fun selectByLastname(lastname: String): Flux<User> {
        return Flux.fromStream(database.findByLastname(lastname))
    }

    fun getAllUser(): Flux<User> {
        return Flux.fromIterable(database.findAll())
    }

}
