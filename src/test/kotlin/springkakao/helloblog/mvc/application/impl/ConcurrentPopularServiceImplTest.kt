package springkakao.helloblog.mvc.application.impl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import springkakao.helloblog.mvc.infrastructure.persistence.SearchWordCountRepository
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class ConcurrentPopularServiceImplTest(
    private val popularServiceImpl: PopularServiceImpl,
    private val searchWordCountRepository: SearchWordCountRepository
) : BehaviorSpec({

    given("100명이 동시에 요청한 상황에서") {
        val searchWord = "이뱅"
        val numberOfThreads = 100
        val latch = CountDownLatch(numberOfThreads)
        val executor = Executors.newFixedThreadPool(100)

        `when`("횟수 저장 요청하면") {
            for (i in 0 until numberOfThreads) {
                executor.submit {
                    popularServiceImpl.addSearchWord(searchWord)
                    latch.countDown()
                }
            }
            latch.await()

            val result = searchWordCountRepository.findByWord(searchWord)

            then("100 횟수 저장된다.") {
                result?.count shouldBe 100
            }
        }
    }
})
