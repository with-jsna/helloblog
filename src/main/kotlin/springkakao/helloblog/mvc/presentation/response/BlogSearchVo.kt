package springkakao.helloblog.mvc.presentation.response

import springkakao.helloblog.common.constant.Codes.BlogType

data class BlogSearchVo(
    val blogType: BlogType,
    val result: List<BlogSearchItem>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Long
)
