package springkakao.helloblog.mvc.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springkakao.helloblog.common.vo.ResponseVo
import springkakao.helloblog.mvc.application.PopularService
import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

@RestController
@RequestMapping("/api/popular")
class PopularController(private val popularService: PopularService) {

    @GetMapping("/top10")
    fun getPopularSearchWords(): ResponseVo<List<PopularSearchWordVo>> {
        return ResponseVo(popularService.getPopularSearchWords())
    }
}
