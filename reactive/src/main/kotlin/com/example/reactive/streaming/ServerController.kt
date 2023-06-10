package com.example.reactive.streaming

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ServerController(private val kitchen: KitchenService) {
    @GetMapping(value = ["/server"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun serveDishes(): Flux<Dish> {
        return this.kitchen.getDishes()
    }

    @GetMapping(value = ["/server-dishes"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun deliverDishes(): Flux<Dish> {
        return this.kitchen.getDishes()
            .map { Dish.deliver(it) }
    }
}