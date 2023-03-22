package springkakao.helloblog.mvc.application

import org.springframework.stereotype.Component
import springkakao.helloblog.common.constant.Codes.BlogType
import springkakao.helloblog.common.exception.CommonExceptionType.NOT_FOUND_SEARCH_BLOG

@Component
class BlogSearchComposition(blogSearchStrategies: List<BlogSearchStrategy>) {

    private val blogs: MutableMap<BlogType, BlogSearchStrategy> = mutableMapOf()

    init {
        blogSearchStrategies.forEach { blogs[it.getBlogType()] = it }
    }

    fun getSearchTargetBlog(searchBlog: BlogType): BlogSearchStrategy {
        return blogs[searchBlog]
            ?: throw NOT_FOUND_SEARCH_BLOG.of("${searchBlog.codeName} 블로그를 조회할 수 없습니다.")
    }
}
