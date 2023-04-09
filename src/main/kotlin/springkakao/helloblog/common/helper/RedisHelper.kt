package springkakao.helloblog.common.helper

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit.SECONDS

@Component
@DependsOn(value = ["embeddedRedis"])
class RedisHelper(private val redissonClient: RedissonClient) {
    fun <T> lock(key: String, function: () -> T) {
        val lock = redissonClient.getLock("$key$LOCK_SUFFIX")

        runCatching {
            val result = lock.tryLock(WAIT_TIME, LEASE_TIME, SECONDS)

            if (!result) {
                throw RuntimeException()
            }

            function()

        }.getOrElse { ex ->
            throw ex
        }.also {
            unlock(lock)
        }

    }

    private fun unlock(lock: RLock?) {
        if (lock != null && lock.isLocked) {
            lock.unlock()
        }
    }

    companion object {
        private const val LOCK_SUFFIX = ":LOCK"
        private const val WAIT_TIME = 100L
        private const val LEASE_TIME = 10L
    }
}
