package springkakao.helloblog.common.util

import springkakao.helloblog.common.exception.CommonExceptionType.UNEXPECTED
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.toEncodeValue(): String {
    return runCatching {
        URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
    }.getOrElse { throw UNEXPECTED.of("Can not encode $this ") }
}