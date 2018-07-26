package de.frudisch.manager.user.web

import de.frudisch.manager.user.domain.User
import org.springframework.hateoas.ResourceSupport

data class UserResponse(val user: User): ResourceSupport()
