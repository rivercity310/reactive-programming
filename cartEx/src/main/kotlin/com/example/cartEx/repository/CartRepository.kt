package com.example.cartEx.repository

import com.example.cartEx.domain.Cart
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CartRepository : ReactiveCrudRepository<Cart, String>
