package springkakao.helloblog.mvc.infrastructure.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer

@Configuration("embeddedRedis")
class EmbeddedRedisConfig {

    lateinit var redis: RedisServer

    @PostConstruct
    fun startRedis() {
        redis = RedisServer(6379)
        redis.start()
    }

    @PreDestroy
    fun stopRedis() {
        redis.stop()
    }
}
