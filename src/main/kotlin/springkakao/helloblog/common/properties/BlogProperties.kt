package springkakao.helloblog.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "blog")
data class BlogProperties(
    val kakao: KakaoProperties,
    val naver: NaverProperties
) {
    data class KakaoProperties(
        val url: String,
        val blogPath: String,
        val authorization: String
    )

    data class NaverProperties(
        val url: String,
        val blogPath: String,
        val id: String,
        val secret: String
    )
}
