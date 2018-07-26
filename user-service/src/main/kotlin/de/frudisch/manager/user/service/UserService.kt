package de.frudisch.manager.user.service

import de.frudisch.manager.user.connector.Kafka
import de.frudisch.manager.user.domain.User
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class UserService(val readService: ReadService, val kafka: Kafka) {

    fun selectUser(uuid: UUID): Mono<User> {
        return readService.selectUser(uuid)
    }

    fun createUser(user: User): Mono<User> {
        user.uuid = UUID.randomUUID()
        kafka.sendUser(user)
        return Mono.just(user)
    }

    fun deleteUser(user: User): Mono<Boolean> {
        kafka.deleteUser(user.uuid)
        return Mono.just(true)
    }

    fun updateUser(user: User): Mono<User> {
        kafka.sendUser(user)
        return Mono.just(user)
    }

    fun selectAllUser(): Flux<User> {
        return readService.getAllUser()
    }

    fun selectByFirstnameLastname(firstname: String, lastname: String): Flux<User> {
        return readService.selectByFirstname(firstname).mergeWith(readService.selectByLastname(lastname))
    }

}