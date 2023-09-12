package com.example.cartEx.repository

import com.example.cartEx.domain.Cart
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : ReactiveCrudRepository<Cart, String>
