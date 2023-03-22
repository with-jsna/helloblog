package springkakao.helloblog.mvc.application

import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

interface BlogSearchService {
    fun search(blogSearchGetVo: BlogSearchGetVo): BlogSearchVo
}
