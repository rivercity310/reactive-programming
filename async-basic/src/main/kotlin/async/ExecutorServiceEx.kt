package async

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// java.util.concurrent 패키지의 ExecutorService를 사용하면 쉽고 안전하게 스레드 풀을 사용할 수 있다.

fun main() {
    val pool: ExecutorService = Executors.newFixedThreadPool(10)

    try {
        for (i in 0..10) {
            pool.execute { println("current-thread-name : ${Thread.currentThread().name}") }
        }
    } finally {
        pool.shutdown()
    }

    println("current-thread-name : ${Thread.currentThread().name}")
}