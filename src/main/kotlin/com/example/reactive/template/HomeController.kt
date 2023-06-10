package com.example.reactive.template

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@Controller
class HomeController {
    @GetMapping(value = ["/", "/home"])
    internal fun home(): Mono<String> {
        return Mono.just("home")
    }
}