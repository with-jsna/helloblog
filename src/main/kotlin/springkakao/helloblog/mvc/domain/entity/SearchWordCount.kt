package springkakao.helloblog.mvc.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "search_word_count")
class SearchWordCount(
    val word: String,
    var count: Long = 1
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(insertable = false, updatable = false)
    val createDtm: LocalDateTime? = null

    fun increaseCount() {
        this.count += 1
    }
}
