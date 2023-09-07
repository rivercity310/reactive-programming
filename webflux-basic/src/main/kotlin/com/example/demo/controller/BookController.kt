package com.example.demo.controller

import com.example.demo.entity.Book
import com.example.demo.repository.BookRepository
import com.example.demo.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController("/books")
class BookController(
    private val bookService: BookService,
    private val bookRepository: BookRepository
) {
    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String) : Mono<Book> {
        return bookRepository.findByName(name)
    }

    @PostMapping
    fun create(@RequestBody map: Map<String, Any>) : Mono<Book> {
        val book = Book(
            name = map["name"].toString(),
            price = map["price"] as Int
        )

        return bookRepository.save(book)
    }

    /*
    @GetMapping
    fun getAll() : Flux<Book> {
        // WebFlux 프레임워크에서 자동으로 subscribe() 메서드를 호출해준다.
        return bookService.getAll()
    }

    @GetMapping("/blockingTest")
    fun getAllWithBlockingTest() : Flux<Book> {
        Thread.sleep(3500)
        return bookService.getAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int) : Mono<Book> {
        return bookService.get(id)
    }

    @PostMapping
    fun add(@RequestBody req: Map<String, Any>) : Mono<Book> {
        return bookService.add(req)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) : Mono<Void> {
        return bookService.delete(id)
    }

    @DeleteMapping("/blockingTest/{id}")
    fun deleteBlockingTest(@PathVariable id: Int) : Mono<Void> {
        Thread.sleep(3000)
        return bookService.delete(id)
    }
    */
}