package springkakao.helloblog.mvc.domain.external

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverSearchResultVo(
    @JsonProperty("lastBuildDate")
    val lastBuildDate: String,
    @JsonProperty("total")
    val total: Int,
    @JsonProperty("start")
    val start: Int,
    @JsonProperty("display")
    val display: Int,
    @JsonProperty("items")
    val items: List<NaverSearchItem>
) : BlogSearchResult
