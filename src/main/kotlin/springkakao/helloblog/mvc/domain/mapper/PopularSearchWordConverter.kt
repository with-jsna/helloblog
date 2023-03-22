package springkakao.helloblog.mvc.domain.mapper

import org.mapstruct.Mapper
import springkakao.helloblog.mvc.domain.entity.SearchWordCount
import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

@Mapper
interface PopularSearchWordConverter {
    fun entityToVo(searchWords: List<SearchWordCount>): List<PopularSearchWordVo>
}