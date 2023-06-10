package com.example.reactive.streaming

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import kotlin.random.Random

@Service
class KitchenService {
    private val menu: List<Dish> = listOf(
        Dish("Sesame chicken"),
        Dish("Lo mein noodles, plain"),
        Dish("Sweet & sour beef")
    )

    private val picker: Random = Random.Default

    /* 요리 스트림 생성 */
    internal fun getDishes(): Flux<Dish> {
        return Flux.generate { it.next(randomDish()) }
            .delayElements(Duration.ofMillis(250))
    }

    /* 요리 무작위 선택 */
    private fun randomDish(): Dish {
        return menu[picker.nextInt(menu.size)]
    }
}