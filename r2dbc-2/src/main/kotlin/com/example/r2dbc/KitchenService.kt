package com.example.r2dbc

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import kotlin.random.Random

data class Dish(val name: String)

@Service
class KitchenService {
    fun getDishes() : Flux<Dish> {
        return Flux.generate<Dish?> { sink -> sink.next(randomDish()) }
            .delayElements(Duration.ofMillis(250))
    }

    private fun randomDish() : Dish {
        return menu.get(picker.nextInt(menu.size))
    }

    private val menu : List<Dish> = listOf(
        Dish("a"),
        Dish("b"),
        Dish("c")
    )

    private val picker : Random = Random.Default
}