package com.example.r2dbc.controller

import com.example.r2dbc.domain.Cart
import com.example.r2dbc.repository.CartRepository
import com.example.r2dbc.repository.ItemRepository
import com.example.r2dbc.service.CartService
import com.example.r2dbc.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Mono

@Controller
class HomeController(
    private val itemService: ItemService,
    private val cartService: CartService,
    private val cartRepository: CartRepository
) {
    @GetMapping(value = ["/", "/home"])
    internal fun home(): Mono<Rendering> {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", this.itemService.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(Cart("My Cart")))
                .build()
        )
    }

    @PostMapping(value = ["/add/{id}"])
    internal fun addToCart(@PathVariable id: String): Mono<String> {
        return this.cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/")
    }

    @DeleteMapping(value = ["/delete/{id}"])
    internal fun deleteFromCart(@PathVariable id: String): Mono<String> {
        return this.cartService.deleteFromCart("My Cart", id)
            .thenReturn("redirect:/")
    }

    @GetMapping(value = ["/search"])
    internal fun search(
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam useAnd: Boolean
    ): Mono<Rendering> {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", itemService.searchByExample(name, description, useAnd))
            .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(Cart("My Cart")))
            .build()
        )
    }
}