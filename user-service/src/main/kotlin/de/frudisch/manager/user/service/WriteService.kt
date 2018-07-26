package de.frudisch.manager.user.service

import de.frudisch.manager.user.connector.Database
import de.frudisch.manager.user.connector.Kafka.Companion.USER_INPUT
import de.frudisch.manager.user.domain.User
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class WriteService(val database: Database) {

    @KafkaListener(topics = [USER_INPUT])
    fun writeUser(record: ConsumerRecord<UUID, User>) {
        if(record.value() != null) {
            database.save(record.value())
        } else {
            database.delete(database.findByUuid(record.key()))
        }
    }
}