package com.example.r2dbc

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    asyncEx()
}

// runBlocking : Coroutine을 생성해주는 Builder, runBlocking 내부 코드의 실행이 끝날때까지 해당 스레드가 Blocking 됨
fun runBlockingEx() {
    runBlocking {
        println("Hello")
        println(Thread.currentThread().name)
    }

    println("World")
    println(Thread.currentThread().name)
}

// launch : Thread Blocking 없이 Coroutine을 시작하고, 결과로 Job을 반환
fun launchEx() {
    runBlocking<Unit> {

        launch {
            delay(1000)
            println("World! ${Thread.currentThread().name}")
        }

        println("Hello ${Thread.currentThread().name}")
    }

    println("outside ${Thread.currentThread().name}")
}

// launch 여러개를 이용한 병렬처리
fun launchEx2() = runBlocking {
    // Job : launch로부터 생성된 Coroutine의 상태를 확인하거나 실행, 취소 가능

    val job1 : Job = launch {
        val elapsedTime = measureTimeMillis { delay(150) }
        println("async task-1 $elapsedTime ${Thread.currentThread().name}")
    }

    job1.cancel()

    // CoroutineStart.LAZY -> job.start() 함수 사용시 launch 시작됨
    val job2 : Job = launch(start = CoroutineStart.LAZY) {
        val elapsedTime = measureTimeMillis { delay(100) }
        println("async task-2 $elapsedTime ${Thread.currentThread().name}")
    }

    println("start task-2")
    job2.start()
}

fun sum(a: Int, b: Int) = a + b

// 비동기 작업이 결과를 가지는 경우 async 사용
fun asyncEx() = runBlocking<Unit> {

    // await() 함수를 통해 Deffered로부터 결과를 받아올 수 있다.
    val result1 : Deferred<Int> = async {
        delay(1000)
        sum(1, 3)
    }

    println("result1 : ${result1.await()}")

    val result2 : Deferred<Int> = async {
        delay(500)
        sum(2, 5)
    }

    println("result2 : ${result2.await()}")
}