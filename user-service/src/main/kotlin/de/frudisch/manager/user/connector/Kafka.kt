package de.frudisch.manager.user.connector

import de.frudisch.manager.user.domain.User
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class Kafka(val template: KafkaTemplate<UUID, User>) {

    companion object {
        const val USER_INPUT: String = "user_input"
    }

    fun sendUser(user: User) {
        val listenableFuture = template.send(USER_INPUT, user.uuid, user)
        listenableFuture.get(500L, TimeUnit.MILLISECONDS)
    }

    fun deleteUser(uuid: UUID) {
        val listenableFuture = template.send(USER_INPUT, uuid, null)
        listenableFuture.get(500L, TimeUnit.MILLISECONDS)
    }
}