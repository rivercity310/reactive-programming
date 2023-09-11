package com.example.cartEx.domain

class CartItem(
    val item: Item,
    var quantity: Int = 0,
) {
    val total : Double get() =
        item.price * quantity

    fun increment() {
        this.quantity++
    }
}