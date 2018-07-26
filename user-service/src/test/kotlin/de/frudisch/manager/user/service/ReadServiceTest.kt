package de.frudisch.manager.user.service

import de.frudisch.manager.user.connector.Database
import de.frudisch.manager.user.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import reactor.test.StepVerifier
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ReadServiceTest {

    @Mock
    private lateinit var database: Database

    private lateinit var readService: ReadService

    @Before
    fun setUp() {
        this.readService = ReadService(database);
    }

    @Test
    fun `Test reading single user by uuid`() {
        val uuid = UUID.randomUUID()

        given(database.findByUuid(uuid)).willReturn(generateUser(uuid))

        val uut = readService.selectUser(uuid)

        assertThat(uut).isNotNull
        StepVerifier.create(uut)
                .expectNext(generateUser(uuid))
                .expectComplete()
                .verify()
    }

    private fun generateUser(uuid: UUID): User {
        return User(0, uuid, "max", "test")
    }
}