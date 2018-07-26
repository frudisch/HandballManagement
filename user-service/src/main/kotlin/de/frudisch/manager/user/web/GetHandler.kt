package de.frudisch.manager.user.web

import de.frudisch.manager.user.domain.User
import de.frudisch.manager.user.service.UserService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.UUID

@Component
class GetHandler(val userService: UserService) {

    fun all(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(generateUserResponse(userService.selectAllUser()).toMono(), UserResponse::class.java)
    }

    fun getByUUID(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(generateUserResponse(userService.selectUser(UUID.fromString(req.pathVariable("uuid")))), UserResponse::class.java)
    }

    fun generateUserResponse(user: Mono<User>): Mono<UserResponse> {
        return user.map(::UserResponse)
    }

    fun generateUserResponse(user: Flux<User>): Flux<UserResponse> {
        return user.map(::UserResponse)
    }

}