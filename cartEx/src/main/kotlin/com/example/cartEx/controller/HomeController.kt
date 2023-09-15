package com.example.cartEx.controller

import com.example.cartEx.domain.Cart
import com.example.cartEx.domain.CartItem
import com.example.cartEx.repository.CartRepository
import com.example.cartEx.repository.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Mono

@Controller
class HomeController(
    private val itemRepository: ItemRepository,
    private val cartRepository: CartRepository
) {
    companion object {
        private const val MY_CART = 1
    }

    @GetMapping
    fun home() : Mono<Rendering> {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", itemRepository.findAll())
            .modelAttribute("cart", cartRepository.findById(MY_CART)
                .defaultIfEmpty(Cart(MY_CART))
            )
            .build()
        )
    }

    @DeleteMapping("/delete/{id}")
    fun deleteItem(@PathVariable id: Int) : Mono<String> {
        return cartRepository.findById(MY_CART)
            .flatMap { cart -> cart.cartItems.stream()
                .filter { cartItem -> cartItem.item.id == id }
                .findFirst()
                .map { cartItem ->
                    cart.cartItems.remove(cartItem)
                    Mono.just(cart)
                }
                .orElseGet { Mono.just(cart) }
            }
            .flatMap { cart -> cartRepository.save(cart) }
            .thenReturn("redirect:/")
    }

    @PostMapping("/add/{id}")
    fun addToCart(@PathVariable id: Int) : Mono<String> {
        return cartRepository.findById(MY_CART)
            .defaultIfEmpty(Cart(MY_CART))
            .flatMap { cart ->
                cart.cartItems.stream()
                    .filter { cartItem -> cartItem.item.id == id }
                    .findAny()
                    .map { cartItem ->
                        cartItem.increment()
                        Mono.just(cart)
                    }
                    .orElseGet {
                        itemRepository.findById(id)
                            .map { item -> CartItem(item = item) }
                            .map { cartItem ->
                                cart.cartItems.add(cartItem)
                                cart
                            }
                    }
            }
            .flatMap { cart -> cartRepository.save(cart) }
            .thenReturn("redirect:/")
    }
}