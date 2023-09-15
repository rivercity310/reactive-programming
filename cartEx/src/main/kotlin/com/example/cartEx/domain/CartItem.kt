package com.example.cartEx.domain

import org.springframework.data.annotation.Id

class CartItem(
    @Id var id: Int = 0,
    val item: Item,
    var quantity: Int = 0,
) {
    val total : Double get() =
        item.price * quantity

    fun increment() {
        this.quantity++
    }
}