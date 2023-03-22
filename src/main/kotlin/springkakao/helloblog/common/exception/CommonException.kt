package springkakao.helloblog.common.exception

import org.springframework.http.HttpStatus

class CommonException(
    val exceptionType: CommonExceptionType,
    message: String,
    val status: HttpStatus
) : RuntimeException(message)
