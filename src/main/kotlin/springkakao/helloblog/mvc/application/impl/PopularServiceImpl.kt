package springkakao.helloblog.mvc.application.impl

import org.mapstruct.factory.Mappers
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import springkakao.helloblog.common.helper.RedisHelper
import springkakao.helloblog.mvc.application.PopularService
import springkakao.helloblog.mvc.domain.entity.SearchWordCount
import springkakao.helloblog.mvc.domain.mapper.PopularSearchWordConverter
import springkakao.helloblog.mvc.infrastructure.persistence.DslSearchWordCountRepository
import springkakao.helloblog.mvc.infrastructure.persistence.SearchWordCountRepository
import springkakao.helloblog.mvc.presentation.response.PopularSearchWordVo

@Transactional
@Service
class PopularServiceImpl(
    private val searchWordCountRepository: SearchWordCountRepository,
    private val dslSearchWordCountRepository: DslSearchWordCountRepository,
    private val redisHelper: RedisHelper
) : PopularService {

    @Transactional(readOnly = true)
    override fun getPopularSearchWords(): List<PopularSearchWordVo> {
        val mapper = Mappers.getMapper(PopularSearchWordConverter::class.java)

        return dslSearchWordCountRepository.findPopularSearchWords().let {
            mapper.entityToVo(it)
        }
    }

    @Async
    override fun addSearchWord(word: String) {
        println("addSearchWord = ${Thread.currentThread().id}")
        val lockKeyName = "${LOCK_PREFIX}:${word}"

        redisHelper.lock(lockKeyName) {
            searchWordCountRepository.findByWord(word)?.also {
                it.increaseCount()
            } ?: run {
                searchWordCountRepository.save(SearchWordCount(word = word))
            }
            searchWordCountRepository.flush()
        }
    }

    companion object {
        const val LOCK_PREFIX = "SEARCH_WORD"
    }
}
