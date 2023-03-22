package springkakao.helloblog.mvc.infrastructure.external

import org.mapstruct.factory.Mappers
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import springkakao.helloblog.common.constant.Codes.BlogType.KAKAO
import springkakao.helloblog.common.exception.CommonExceptionType.UNEXPECTED_SEARCH_KAKAO_BLOG
import springkakao.helloblog.common.properties.BlogProperties
import springkakao.helloblog.common.util.HttpRequest
import springkakao.helloblog.common.util.toEncodeValue
import springkakao.helloblog.mvc.application.BlogSearchStrategy
import springkakao.helloblog.mvc.domain.external.KakaoSearchResultVo
import springkakao.helloblog.mvc.domain.mapper.BlogSearchConverter
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@Component
class KakaoBlogSearch(private val blogProperties: BlogProperties) : BlogSearchStrategy {
    override fun getBlogType() = KAKAO

    override fun execute(blogSearchGetVo: BlogSearchGetVo): BlogSearchVo {
        val kakaoBlog = blogProperties.kakao
        val url = "${kakaoBlog.url}${kakaoBlog.blogPath}"
        val path = blogSearchGetVo.let {
            "query=${it.keyword.toEncodeValue()}&sort=${it.sort.kakaoCode}&page=${it.pageNumber}&size=${it.pageSize}"
        }

        val kakaoSearchResultVo = runCatching {
            HttpRequest.requestGet<KakaoSearchResultVo>(
                url = "${url}?${path}",
                headers = mapOf(HttpHeaders.AUTHORIZATION to kakaoBlog.authorization)
            ) ?: throw UNEXPECTED_SEARCH_KAKAO_BLOG.of()
        }.getOrElse { throw UNEXPECTED_SEARCH_KAKAO_BLOG.of() }

        return Mappers.getMapper(BlogSearchConverter::class.java)
            .kakaoResultVoToSearchVo(kakaoSearchResultVo, blogSearchGetVo, getBlogType())
    }
}
