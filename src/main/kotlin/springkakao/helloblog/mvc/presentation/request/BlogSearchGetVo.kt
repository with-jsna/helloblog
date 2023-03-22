package springkakao.helloblog.mvc.presentation.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import springkakao.helloblog.common.constant.Codes.SearchSortType

data class BlogSearchGetVo(
    @field:NotBlank
    val keyword: String,
    val sort: SearchSortType = SearchSortType.ACCURACY,
    @field:Min(value = 1)
    @field:Max(value = 50)
    val pageNumber: Int = 1,
    @field:Min(value = 1)
    @field:Max(value = 50)
    val pageSize: Int = 10
)
