package de.frudisch.manager.user.web

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DeleteHandler {

    fun delete(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().build()
    }

}
