package springkakao.helloblog.common.exception

import org.springframework.http.HttpStatus

class CommonExceptionResponse(
    val exceptionType: CommonExceptionType,
    val message: String,
    val status: HttpStatus
)
