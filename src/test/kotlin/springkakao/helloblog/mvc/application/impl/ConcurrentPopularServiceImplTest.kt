package springkakao.helloblog.mvc.application.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import springkakao.helloblog.mvc.infrastructure.persistence.SearchWordCountRepository
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class ConcurrentPopularServiceImplTest @Autowired constructor(
    val popularServiceImpl: PopularServiceImpl,
    val searchWordCountRepository: SearchWordCountRepository
) {
    @Test
    fun `검색어_횟수_증가_동시성_테스트`() {
        val searchWord = "애플페이"
        val numberOfThreads = 100
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(numberOfThreads)

        for (i in 0 until numberOfThreads) {
            executorService.submit {
                try {
                    popularServiceImpl.addSearchWord(searchWord)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        println("-------------------------")

        val searchWordCount = searchWordCountRepository.findByWord(searchWord)

        assertThat(searchWordCount?.count).isEqualTo(numberOfThreads)
    }
}
