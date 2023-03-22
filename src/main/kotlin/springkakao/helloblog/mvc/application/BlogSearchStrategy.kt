package springkakao.helloblog.mvc.application

import springkakao.helloblog.common.constant.Codes.BlogType
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

interface BlogSearchStrategy {
    fun getBlogType(): BlogType
    fun execute(blogSearchGetVo: BlogSearchGetVo): BlogSearchVo
}
