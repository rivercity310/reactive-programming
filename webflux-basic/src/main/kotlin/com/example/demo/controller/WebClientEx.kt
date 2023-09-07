package com.example.demo.controller

import com.example.demo.service.Book
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

@RestController
class WebClientEx {
    val url = "http://localhost:8080/books/blockingTest"
    val log = LoggerFactory.getLogger(javaClass)

    // RestTemplate -> Blocking 방식 Network IO (Deprecated After Spring 5)
    @GetMapping("/books/block")
    fun getBooksBlockingWay() : List<Book> {
        log.info("Start RestTemplate")

        val restTemplate = RestTemplate()
        val res1 = restTemplate.exchange(url, HttpMethod.GET, null, object : ParameterizedTypeReference<List<Book>>() { })

        log.info("res1 완료 : {}", res1)

        val res2 = restTemplate.exchange(url, HttpMethod.GET, null, object : ParameterizedTypeReference<List<Book>>() { })

        log.info("res2 완료 : {}", res2)

        val res3 = restTemplate.exchange(url, HttpMethod.GET, null, object : ParameterizedTypeReference<List<Book>>() { })

        log.info("res3 완료 : {}", res3)

        val result = res3.body

        log.info("result : {}", result)
        log.info("Finish RestTemplate")

        return result!!
    }

    @GetMapping("/books/nonBlock")
    fun getBooksNonBlockingWay() : Flux<Book> {
        log.info("Start WebClient")

        val parallelFlux = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Book::class.java)
            .parallel()
            .map {
                log.info("result : {}", it)
                it
            }
            .toFlux()

        val flux = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Book::class.java)
            .map {
                log.info("result : {}", it)
                it
            }

        log.info("Finish WebClient")
        return parallelFlux
    }
}