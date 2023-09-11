package com.example.cartEx.repository

import com.example.cartEx.domain.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ItemRepository : ReactiveCrudRepository<Item, String>