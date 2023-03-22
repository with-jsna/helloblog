package springkakao.helloblog.mvc.infrastructure.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import springkakao.helloblog.mvc.domain.entity.QSearchWordCount.searchWordCount
import springkakao.helloblog.mvc.domain.entity.SearchWordCount

@Repository
class DslSearchWordCountRepository(private val queryFactory: JPAQueryFactory) {

    fun findPopularSearchWords(): List<SearchWordCount> {
        return queryFactory.selectFrom(searchWordCount)
            .orderBy(searchWordCount.count.desc())
            .limit(10)
            .fetch()
    }
}
