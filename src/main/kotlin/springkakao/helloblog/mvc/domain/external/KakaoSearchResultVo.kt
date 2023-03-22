package springkakao.helloblog.mvc.domain.external

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoSearchResultVo(
    @JsonProperty("documents")
    val documents: List<KakaoSearchDocument>,
    @JsonProperty("meta")
    val meta: KakaoSearchResultMeta
) : BlogSearchResult
