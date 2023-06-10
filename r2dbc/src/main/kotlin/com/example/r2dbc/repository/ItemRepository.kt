package com.example.r2dbc.repository

import com.example.r2dbc.domain.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ItemRepository: ReactiveCrudRepository<Item, String>