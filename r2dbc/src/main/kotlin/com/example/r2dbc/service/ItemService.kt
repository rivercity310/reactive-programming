package com.example.r2dbc.service

import com.example.r2dbc.domain.Item
import com.example.r2dbc.repository.ItemRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations
import org.springframework.data.mongodb.core.query.Criteria.byExample
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val fluentMongoOperations: ReactiveFluentMongoOperations
) {
    internal fun searchByExample(name: String, description: String, useAnd: Boolean): Flux<Item> {
        val item = Item(name = name, description = description, price = 0.0)

        val matcher: ExampleMatcher = if (useAnd) ExampleMatcher.matchingAll() else ExampleMatcher.matchingAny()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase()
            .withIgnorePaths("price")

        return fluentMongoOperations.query(Item::class.java)
            .matching(Query.query(byExample(Example.of(item, matcher))))
            .all()
    }

    internal fun findAll(): Flux<Item> {
        return itemRepository.findAll()
    }
}