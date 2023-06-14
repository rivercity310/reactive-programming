package com.example.r2dbc.service

import com.example.r2dbc.domain.Cart
import com.example.r2dbc.domain.CartItem
import com.example.r2dbc.repository.CartRepository
import com.example.r2dbc.repository.ItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CartService(
    private val itemRepository: ItemRepository,
    private val cartRepository: CartRepository
) {
    internal fun addToCart(cartId: String, id: String): Mono<Cart> {
        return this.cartRepository.findById(cartId)
                .defaultIfEmpty(Cart(cartId))        // Mono<Cart>
                .flatMap { cart -> cart.cartItems.stream()
                        .filter { it.item.id.equals(id) }
                        .findAny()
                        .map { cartItem ->
                            cartItem.increment()
                            return@map Mono.just(cart)
                        }
                        .orElseGet {
                            return@orElseGet this.itemRepository.findById(id)
                                    .map { item -> CartItem(item) }
                                    .doOnNext { cartItem -> cart.cartItems.add(cartItem) }
                                    .map { cart }
                        }
                }
                .flatMap { cart -> this.cartRepository.save(cart) }
    }

    internal fun deleteFromCart(cartId: String, id: String): Mono<Cart> {
        return this.cartRepository.findById(cartId)
                .defaultIfEmpty(Cart(cartId))
                .flatMap { cart -> cart.cartItems.stream()
                        .filter { it.item.id.equals(id) }
                        .findAny()
                        .map { cartItem ->
                            cartItem.decrement()
                            return@map Mono.just(cart)
                        }
                        .orElseGet {
                            return@orElseGet this.itemRepository.findById(id)
                                    .map { item -> CartItem(item) }
                                    .doOnNext { cartItem -> cart.cartItems.remove(cartItem) }
                                    .map { cart }
                        }
                }
                .flatMap {
                    cart -> this.cartRepository.delete(cart).subscribe()
                    return@flatMap Mono.just(cart)
                }
    }
}