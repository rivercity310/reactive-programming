package com.example.demo.controller

import com.example.demo.service.Book
import com.example.demo.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(private val bookService: BookService) {
    @GetMapping("/books")
    fun getAll() : Flux<Book> {
        // WebFlux 프레임워크에서 자동으로 subscribe() 메서드를 호출해준다.
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Int) : Mono<Book> {
        return bookService.get(id)
    }

    @PostMapping("/books")
    fun add(@RequestBody req: Map<String, Any>) : Mono<Book> {
        return bookService.add(req)
    }

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Int) : Mono<Void> {
        return bookService.delete(id)
    }
}