package com.example.r2dbc.repository

import com.example.r2dbc.domain.Item
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ItemRepository: ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {
    fun findByNameContaining(partialName: String): Flux<Item>

    @Query("{ 'name' :  ?0, 'age' :  ?1 }")
    fun findItemsForCustomerMonthlyReport(name: String, age: Int): Flux<Item>

    @Query("{ 'age' :  -1 }")
    fun findSortedStuffForWeeklyReport(): Flux<Item>
}