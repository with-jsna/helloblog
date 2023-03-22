package springkakao.helloblog.mvc.application.impl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.mockito.InjectMocks
import springkakao.helloblog.common.constant.Codes.BlogType.KAKAO
import springkakao.helloblog.mvc.application.BlogSearchComposition
import springkakao.helloblog.mvc.application.PopularService
import springkakao.helloblog.mvc.infrastructure.external.KakaoBlogSearch
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchItem
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

val blogSearchComposition: BlogSearchComposition = mockk()
val kakaoBlogSearch: KakaoBlogSearch = mockk()
val popularService: PopularService = mockk()

@InjectMocks
val blogSearchServiceImpl: BlogSearchServiceImpl =
    BlogSearchServiceImpl(blogSearchComposition, popularService)

class BlogSearchServiceImplTest : BehaviorSpec({

    given("검색어만 입력하고 요청된 상황에서") {

        val blogSearchGetVo = BlogSearchGetVo(keyword = "카뱅")
        val mockBlogSearchVo = BlogSearchVo(
            blogType = KAKAO,
            result = listOf(
                BlogSearchItem(
                    blogName = "블로그 in kotlin",
                    title = "카뱅으로 가는길",
                    contents = "카뱅으로 가는길",
                    postDate = "2023-03-15T20:00:18.000+09:00",
                    link = "https://peter.tistory.com/92"
                )
            ),
            pageNumber = 1,
            pageSize = 50,
            totalCount = 100
        )

        every { blogSearchComposition.getSearchTargetBlog(KAKAO) } answers { kakaoBlogSearch }
        every { kakaoBlogSearch.execute(blogSearchGetVo) } answers { mockBlogSearchVo }
        every { popularService.addSearchWord(any()) } just Runs

        `when`("조회하면") {
            val result = blogSearchServiceImpl.search(blogSearchGetVo)

            then("조회 된다.") {
                result shouldBe mockBlogSearchVo
            }
        }
    }
})
