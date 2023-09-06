package async

import java.util.concurrent.CompletableFuture

// CompletableFuture를 사용하면 논블로킹으로 동작하는 비동기 처리를 할 수 있다.

fun main() {
    val completableFuture = CompletableFuture.supplyAsync { waitingSum(300, 400) }

    println("계산 시작")
    completableFuture.thenApplyAsync(::println)   // 논블로킹으로 동작

    /*
    val result = completableFuture.get()   // 블로킹으로 동작
    println(result)
    */

    while (!completableFuture.isDone) {
        Thread.sleep(500)
        println("계산 결과를 집계중입니다.")
    }

    println("계산 종료")
}