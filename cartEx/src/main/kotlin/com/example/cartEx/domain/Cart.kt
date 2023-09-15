package com.example.cartEx.domain

import org.springframework.data.annotation.Id

class Cart(
    @Id var id: Int = 0,
    val cartItems: MutableList<CartItem> = mutableListOf()
) {
    val totalPrice : Double get() =
        cartItems.fold(0.0) { total, item -> total + item.total }
}