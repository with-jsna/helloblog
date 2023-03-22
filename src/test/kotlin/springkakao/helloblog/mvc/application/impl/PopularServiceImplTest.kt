package springkakao.helloblog.mvc.application.impl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import io.mockk.verify
import org.mockito.InjectMocks
import springkakao.helloblog.common.helper.RedisHelper
import springkakao.helloblog.mvc.domain.entity.SearchWordCount
import springkakao.helloblog.mvc.infrastructure.persistence.DslSearchWordCountRepository
import springkakao.helloblog.mvc.infrastructure.persistence.SearchWordCountRepository
import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

val searchWordCountRepository: SearchWordCountRepository = mockk()
val dslSearchWordCountRepository: DslSearchWordCountRepository = mockk()
val redisHelper: RedisHelper = mockk()

@InjectMocks
val popularServiceImpl = PopularServiceImpl(
    searchWordCountRepository,
    dslSearchWordCountRepository,
    redisHelper
)

class PopularServiceImplTest : BehaviorSpec({

    given("인기 검색어 조회가 요청된 상황에서") {
        val mockSearchWordCounts = listOf(
            SearchWordCount(word = "카뱅", count = 999),
            SearchWordCount(word = "판교", count = 888)
        )

        val mockPopularSearchWords = listOf(
            PopularSearchWordVo(word = "카뱅", count = 999),
            PopularSearchWordVo(word = "판교", count = 888)
        )

        every { dslSearchWordCountRepository.findPopularSearchWords() } answers { mockSearchWordCounts }

        `when`("조회하면") {
            val result = popularServiceImpl.getPopularSearchWords()

            then("조회 된다.") {
                verify(exactly = 1) { dslSearchWordCountRepository.findPopularSearchWords() }
                result shouldBe mockPopularSearchWords
            }
        }
    }

    given("검색어 횟수 증가가 요청이된 상황에서") {
        val searchWordCount = SearchWordCount("카뱅", 1)

        every { searchWordCountRepository.findByWord(any()) } answers { searchWordCount }
        every { redisHelper.lock(any(), captureLambda<() -> Unit>()) } answers { lambda<() -> Unit>().invoke() }

        `when`("처리를 하면") {
            popularServiceImpl.addSearchWord("카뱅")

            then("처리된다.") {
                verify(exactly = 1) { searchWordCountRepository.findByWord("카뱅") }
            }
        }
    }
})
