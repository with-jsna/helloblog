package springkakao.helloblog.mvc.domain.external

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverSearchItem(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("link")
    val link: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("bloggername")
    val bloggername: String,
    @JsonProperty("bloggerlink")
    val bloggerlink: String,
    @JsonProperty("postdate")
    val postdate: String
)