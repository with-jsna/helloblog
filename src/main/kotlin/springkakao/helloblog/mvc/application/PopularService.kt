package springkakao.helloblog.mvc.application

import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

interface PopularService {
    fun getPopularSearchWords(): List<PopularSearchWordVo>
    fun addSearchWord(word: String)
}
