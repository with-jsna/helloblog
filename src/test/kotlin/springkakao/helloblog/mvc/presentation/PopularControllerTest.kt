package springkakao.helloblog.mvc.presentation

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import springkakao.helloblog.mvc.application.PopularService
import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

@WebMvcTest(PopularController::class)
class PopularControllerTest : AbstractControllerTest() {

    @MockBean
    private lateinit var popularService: PopularService

    @Test
    fun getPopularSearchWords() {

        val popularSearchWordVos = listOf(
            PopularSearchWordVo(
                word = "카뱅",
                count = 100000
            )
        )

        // given
        given(popularService.getPopularSearchWords()).willReturn(popularSearchWordVos)

        // when
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/popular/top10"))

        // then
        result.andExpect(status().isOk)
    }
}
