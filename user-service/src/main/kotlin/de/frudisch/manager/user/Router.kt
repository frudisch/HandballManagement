package de.frudisch.manager.user

import de.frudisch.manager.user.web.DeleteHandler
import de.frudisch.manager.user.web.GetHandler
import de.frudisch.manager.user.web.PostHandler
import de.frudisch.manager.user.web.UpdateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val deleteHandler: DeleteHandler,
             private val postHandler: PostHandler,
             private val updateHandler: UpdateHandler,
             private val getHandler: GetHandler) {

    @Bean
    fun apiRouter() = router {
        (accept(MediaType.APPLICATION_JSON) and "/api").nest {
            // users
            "/user".nest {
                GET("/", getHandler::all)
                GET("/{uuid}", getHandler::getByUUID)
                POST("/", postHandler::create)
                DELETE("/{uuid}", deleteHandler::delete)
                PUT("/{uuid}", updateHandler::update)
            }
        }
    }

}