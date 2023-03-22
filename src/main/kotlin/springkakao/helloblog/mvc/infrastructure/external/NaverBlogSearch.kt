package springkakao.helloblog.mvc.infrastructure.external

import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Component
import springkakao.helloblog.common.constant.Codes.BlogType.NAVER
import springkakao.helloblog.common.exception.CommonExceptionType.UNEXPECTED_SEARCH_NAVER_BLOG
import springkakao.helloblog.mvc.domain.mapper.BlogSearchConverter
import springkakao.helloblog.common.properties.BlogProperties
import springkakao.helloblog.common.util.HttpRequest
import springkakao.helloblog.common.util.toEncodeValue
import springkakao.helloblog.mvc.application.BlogSearchStrategy
import springkakao.helloblog.mvc.domain.external.NaverSearchResultVo
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@Component
class NaverBlogSearch(private val blogProperties: BlogProperties) : BlogSearchStrategy {
    override fun getBlogType() = NAVER

    override fun execute(blogSearchGetVo: BlogSearchGetVo): BlogSearchVo {
        val naverBlog = blogProperties.naver
        val url = "${naverBlog.url}${naverBlog.blogPath}"
        val path = blogSearchGetVo.let {
            "query=${it.keyword.toEncodeValue()}&sort=${it.sort.naverCode}&start=${it.pageNumber}&display=${it.pageSize}"
        }

        val naverSearchResultVo = runCatching {
            HttpRequest.requestGet<NaverSearchResultVo>(
                url = "${url}?${path}",
                headers = mapOf(HEADER_ID to naverBlog.id, HEADER_SECRET to naverBlog.secret)
            ) ?: throw UNEXPECTED_SEARCH_NAVER_BLOG.of()
        }.getOrElse { throw UNEXPECTED_SEARCH_NAVER_BLOG.of() }

        return Mappers.getMapper(BlogSearchConverter::class.java)
            .naverResultVoToSearchVo(naverSearchResultVo, blogSearchGetVo, getBlogType())
    }

    companion object {
        const val HEADER_ID = "X-Naver-Client-Id"
        const val HEADER_SECRET = "X-Naver-Client-Secret"
    }
}
