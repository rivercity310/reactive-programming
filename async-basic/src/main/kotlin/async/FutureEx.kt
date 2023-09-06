package async

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// Future는 비동기 작업에 대한 결과를 얻고 싶은 경우에 사용 -> 수행시간이 오래 걸리는 작업에 대한 결과를 기다리면서 다른 작업을 병행할 때 유용
// Thread는 Runnable Interface를 사용하여 비동기 처리를 하지만, Future는 Callable Interface를 사용한다.

// Future의 get() 함수는 비동기 작업이 완료될 떄까지 다음 코드로 넘어가지 않고 무한정 대기하거나 타임아웃 시간까지 블로킹된다는 단점이 있다.
// ---> 단점을 보완하기 위해 CompletableFuture 사용

fun waitingSum(a: Int, b: Int) : Int {
    Thread.sleep(1500)
    return a + b
}

fun main() {
    val pool: ExecutorService = Executors.newSingleThreadExecutor()
    val future = pool.submit(Callable { waitingSum(100, 200) })

    println("계산 시작")
    val futureResult = future.get()  // 비동기 작업의 결과를 기다린다
    println(futureResult)
    println("계산 종료")
}