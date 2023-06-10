package com.example.r2dbc.domain

import org.springframework.data.annotation.Id

class Item(
    @Id var id: String? = null,
    var name: String,
    var price: Double
)