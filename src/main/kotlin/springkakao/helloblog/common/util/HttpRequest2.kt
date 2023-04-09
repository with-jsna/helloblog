package springkakao.helloblog.common.util

import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class HttpRequest2 {

    var httpClient = null

    init {
        httpClient = HttpClients.custom().also {
            it.
        }
    }

    fun execute(url: String) {

        val connManager = BasicHttpClientConnectionManager().also {
            it.connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(10000, TimeUnit.MILLISECONDS)
                .build()
        }

        val a = BasicHttpClientConnectionManager() as HttpClientConnectionManager



        val pool = PoolingHttpClientConnectionManager() as HttpClientConnectionManager

        pool.connect()

        val client = HttpClients.custom().setConnectionManager(pool).build()


        client.execute(HttpGet(url), BasicHttpClientResponseHandler())


    }
}
g