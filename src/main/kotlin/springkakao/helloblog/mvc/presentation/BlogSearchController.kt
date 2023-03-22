package springkakao.helloblog.mvc.presentation

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springkakao.helloblog.common.vo.ResponseVo
import springkakao.helloblog.mvc.application.BlogSearchService
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@RestController
@RequestMapping("/api/blog")
class BlogSearchController(private val blogSearchService: BlogSearchService) {

    @GetMapping
    fun searchBlog(@Valid blogSearchGetVo: BlogSearchGetVo): ResponseVo<BlogSearchVo> {
        return ResponseVo(blogSearchService.search(blogSearchGetVo))
    }
}
