package springkakao.helloblog.common.util

import org.springframework.web.reactive.function.client.WebClient

object HttpRequest {

    inline fun <reified T> requestGet(url: String, headers: Map<String, String>): T? {
        return WebClient.builder().baseUrl(url)
            .defaultHeaders { header ->
                headers.entries.map {
                    header.set(it.key, it.value)
                }
            }
            .build()
            .get()
            .retrieve()
            .bodyToMono(T::class.java)
            .block()
    }
}
