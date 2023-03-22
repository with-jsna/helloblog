package springkakao.helloblog.common.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun commonExceptionHandler(e: CommonException): CommonExceptionResponse {
        return CommonExceptionResponse(e.exceptionType, e.message ?: "UNKNOWN", e.status)
    }
}
