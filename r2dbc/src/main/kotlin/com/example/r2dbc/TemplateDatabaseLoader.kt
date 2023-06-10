package com.example.r2dbc

import com.example.r2dbc.domain.Item
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component

@Component
class TemplateDatabaseLoader {
    @Bean
    internal fun initialize(mongo: ReactiveMongoOperations): CommandLineRunner {
        return CommandLineRunner {
            mongo.save(Item(name = "Alf alarm clock", price = 19.99)).subscribe()
            mongo.save(Item(name = "Smurf TV tray", price = 24.99)).subscribe()
        }
    }
}