package springkakao.helloblog.mvc.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import springkakao.helloblog.mvc.domain.entity.SearchWordCount

@Repository
interface SearchWordCountRepository : JpaRepository<SearchWordCount, Long> {

    @Query("""
        from SearchWordCount 
        where word=:word
        order by word
        limit 1
    """)
    fun findByWord(word: String): SearchWordCount?
}
