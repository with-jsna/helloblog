package springkakao.helloblog.mvc.domain.external

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoSearchResultMeta(
    @JsonProperty("is_end")
    val isEnd: Boolean,
    @JsonProperty("pageable_count")
    val pageableCount: Long,
    @JsonProperty("total_count")
    val totalCount: Long
)
