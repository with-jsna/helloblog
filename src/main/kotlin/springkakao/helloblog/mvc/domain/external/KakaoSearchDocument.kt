package springkakao.helloblog.mvc.domain.external

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoSearchDocument(
    @JsonProperty("blogname")
    val blogname: String,
    @JsonProperty("contents")
    val contents: String,
    @JsonProperty("datetime")
    val datetime: String,
    @JsonProperty("thumbnail")
    val thumbnail: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    val url: String,
)
