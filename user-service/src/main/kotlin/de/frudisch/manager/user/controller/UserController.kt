package de.frudisch.manager.user.controller

import de.frudisch.manager.user.domain.User
import de.frudisch.manager.user.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService){

    @GetMapping("/{id}")
    fun getUser(uuid: UUID): Mono<User> {
        return userService.selectUser(uuid)
    }

    @PostMapping
    fun createUser(user: User): Mono<User> {
        return userService.createUser(user)
    }

    @DeleteMapping
    fun deleteUser(user: User): Mono<User> {
        return userService.deleteUser(user)
    }

    @PutMapping
    fun updateUser(user: User): Mono<User> {
        return userService.updateUser(user);
    }

}