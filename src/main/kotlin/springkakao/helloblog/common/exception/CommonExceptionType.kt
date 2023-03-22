package springkakao.helloblog.common.exception

import org.springframework.http.HttpStatus

enum class CommonExceptionType(
    val httpStatus: HttpStatus,
    val message: String? = null
) {
    UNEXPECTED(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러"),
    UNEXPECTED_SEARCH_KAKAO_BLOG(HttpStatus.INTERNAL_SERVER_ERROR, "카카오 블로그 API 조회 에러"),
    UNEXPECTED_SEARCH_NAVER_BLOG(HttpStatus.INTERNAL_SERVER_ERROR, "네이버 블로그 API 조회 에러"),
    NOT_FOUND_SEARCH_BLOG(HttpStatus.FORBIDDEN)
    ;

    @Suppress("NOTHING_TO_INLINE")
    inline fun of(message: String? = null): CommonException {
        return CommonException(this, message ?: this.message ?: "", this.httpStatus)
    }
}
