package springkakao.helloblog.common.util

import org.junit.jupiter.api.Test
import springkakao.helloblog.mvc.domain.external.KakaoSearchResultVo

class HttpRequestTest {

    @Test
    fun get() {
        val kakaoSearchResultVo = HttpRequest.requestGet<KakaoSearchResultVo>(
            "https://dapi.kakao.com/v2/search/blog?query=jpa",
            mapOf("Authorization" to "KakaoAK 11ab87ca93bc2120ffa72a9611e75c94")
        )

        kakaoSearchResultVo?.also {
            println("${it.meta.pageableCount}")
        }
    }
}
