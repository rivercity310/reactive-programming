package com.example.cartEx.repository

import com.example.cartEx.domain.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : ReactiveCrudRepository<Item, String>