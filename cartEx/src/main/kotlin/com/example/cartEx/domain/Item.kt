package com.example.cartEx.domain

import org.springframework.data.annotation.Id

class Item(
    @Id var id: Int? = 0,
    val name: String,
    val price: Double
)