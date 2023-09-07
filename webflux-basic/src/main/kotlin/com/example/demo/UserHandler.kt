package com.example.demo

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class User(
    val id: Long,
    val email: String
)

@Component
class UserHandler {
    private val users = listOf(
        User(id = 1, email = "h970126@gmail.com"),
        User(id = 2, email = "user2@gmail.com"),
        User(id = 3, email = "user3@gmail.com"),
        User(id = 4, email = "user4@gmail.com"),
    )

    fun getUser(req: ServerRequest) : Mono<ServerResponse> {
        return users.find { req.pathVariable("id").toLong() == it.id }
            ?.let { ServerResponse.ok().bodyValue(it) }
            ?: ServerResponse.notFound().build()
    }

    fun getAll(req: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue(users)
    }
}