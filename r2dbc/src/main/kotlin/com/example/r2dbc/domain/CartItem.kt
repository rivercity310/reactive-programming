package com.example.r2dbc.domain

class CartItem(
    var item: Item,
    var quantity: Int = 1
) {
    internal fun increment(): Unit {
        this.quantity++
    }

    internal fun decrement(): Unit {
        this.quantity--
    }
}