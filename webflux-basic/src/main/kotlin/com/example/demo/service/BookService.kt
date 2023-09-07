package com.example.demo.service

import com.example.demo.entity.Book
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicInteger

@Service
class BookService {
    private final val nextId = AtomicInteger(0)

    /*
    private val books = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "1번 책", price = 10000),
        Book(id = nextId.incrementAndGet(), name = "2번 책", price = 20000),
    )

    fun getAll() : Flux<Book> {
        // return Flux.fromIterable(books)
        // 아래 Collection 확장함수를 통해 쉽게 Flux로 변환 가능
        return books.toFlux()
    }

    fun get(id: Int): Mono<Book> {
        val book = books.find { it.id == id }
        // return Mono.justOrEmpty(book)
        return book.toMono()
    }

    fun add(req: Map<String, Any>): Mono<Book> {
        return Mono.just(req)
            .map {
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = it["name"].toString(),
                    price = it["price"] as Int
                )

                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }
    */
}
