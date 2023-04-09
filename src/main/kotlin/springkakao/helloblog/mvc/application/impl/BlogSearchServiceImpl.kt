package springkakao.helloblog.mvc.application.impl

import org.springframework.stereotype.Service
import springkakao.helloblog.common.constant.Codes.BlogType.KAKAO
import springkakao.helloblog.common.constant.Codes.BlogType.NAVER
import springkakao.helloblog.mvc.application.BlogSearchComposition
import springkakao.helloblog.mvc.application.BlogSearchService
import springkakao.helloblog.mvc.application.PopularService
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@Service
class BlogSearchServiceImpl(
    private val blogSearchComposition: BlogSearchComposition,
    private val popularService: PopularService
) : BlogSearchService {

    override fun search(blogSearchGetVo: BlogSearchGetVo): BlogSearchVo {
        println("search = ${Thread.currentThread().id}")
        addSearchWord(blogSearchGetVo.keyword)

        return runCatching {
            val searchTargetBlog = blogSearchComposition.getSearchTargetBlog(KAKAO)
            searchTargetBlog.execute(blogSearchGetVo)
        }.getOrElse {
            val searchTargetBlog = blogSearchComposition.getSearchTargetBlog(NAVER)
            searchTargetBlog.execute(blogSearchGetVo)
        }
    }

    private fun addSearchWord(keyword: String) {
        popularService.addSearchWord(keyword)
    }
}
