package com.example.r2dbc

import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ServerController(private val kitchenService: KitchenService) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/server"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun serveDishes() : Flux<Dish> {
        return kitchenService.getDishes()
            .doOnNext { log.info("Thank you for ${it.name}") }
            .doOnComplete { log.info("It's Done") }
    }

    @GetMapping(value = ["/server/{id}"])
    suspend fun getDish(@PathVariable id: Int) : Dish {
        return kitchenService.getDish(id)
    }
}