package com.example.r2dbc.repository

import com.example.r2dbc.domain.Cart
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CartRepository: ReactiveCrudRepository<Cart, String>