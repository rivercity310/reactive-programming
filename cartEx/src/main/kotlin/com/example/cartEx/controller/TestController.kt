package com.example.cartEx.controller

import com.example.cartEx.domain.Item
import com.example.cartEx.repository.ItemRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(private val itemRepository: ItemRepository) {
    @PostMapping
    fun add(@RequestBody item : Item) : Mono<Item> {
        return itemRepository.save(item)
    }
}