package com.example.r2dbc.domain

import org.springframework.data.annotation.Id

class Cart(
    @Id var id: String? = null,
    val cartItems: MutableList<CartItem> = arrayListOf()
)