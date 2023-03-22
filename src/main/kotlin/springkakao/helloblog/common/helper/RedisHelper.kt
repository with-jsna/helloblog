package springkakao.helloblog.common.helper

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit.SECONDS

@Component
@DependsOn(value = ["embeddedRedis"])
class RedisHelper(
    private val redissonClient: RedissonClient
) {
    fun <T> lock(key: String, function: () -> T): T {
        val lock = redissonClient.getLock("$key$LOCK_SUFFIX")

        runCatching {
            lock.tryLock(WAIT_TIME, LEASE_TIME, SECONDS)

        }.getOrElse { ex ->
            throw ex
        }.also {
            unlock(lock)
        }

        return function()
    }

    private fun unlock(lock: RLock?) {
        if (lock != null && lock.isLocked) {
            lock.unlock()
        }
    }

    companion object {
        private const val LOCK_SUFFIX = ":LOCK"
        private const val WAIT_TIME = 5L
        private const val LEASE_TIME = 3L
    }
}
