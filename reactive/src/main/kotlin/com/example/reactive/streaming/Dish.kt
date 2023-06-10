package com.example.reactive.streaming

class Dish(
    var description: String,
    var delivered: Boolean = false
) {
    companion object {
        internal fun deliver(dish: Dish): Dish {
            val deliveredDish = Dish(dish.description)
            deliveredDish.delivered = true
            return deliveredDish
        }
    }

    override fun toString(): String {
        return "Dish{description=${description}, delivered=${delivered}}"
    }
}