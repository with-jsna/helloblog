package springkakao.helloblog.mvc.presentation

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import springkakao.helloblog.common.constant.Codes
import springkakao.helloblog.mvc.application.BlogSearchService
import springkakao.helloblog.mvc.presentation.response.BlogSearchItem
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@WebMvcTest(BlogSearchController::class)
class BlogSearchControllerTest : AbstractControllerTest() {

    @MockBean
    private lateinit var blogSearchService: BlogSearchService

    @Test
    fun searchBlog() {
        val mockBlogSearchVo = BlogSearchVo(
            blogType = Codes.BlogType.KAKAO,
            result = listOf(
                BlogSearchItem(
                    blogName = "Blog in kotlin",
                    title = "카뱅으로 가는길",
                    contents = "카뱅으로 가는길",
                    postDate = "2023-03-15T20:00:18.000+09:00",
                    link = "https://flame.tistory.com/1"
                )
            ),
            pageNumber = 1,
            pageSize = 10,
            totalCount = 100
        )

        // given
        given(blogSearchService.search(any())).willReturn(mockBlogSearchVo)

        // when
        val result = mockMvc.perform(
            get("/api/blog")
                .param("keyword", "카뱅")
        )

        // then
        result.andExpect(status().isOk)
    }
}
