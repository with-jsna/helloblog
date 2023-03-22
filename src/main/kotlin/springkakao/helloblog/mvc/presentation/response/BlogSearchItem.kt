package springkakao.helloblog.mvc.presentation.response

data class BlogSearchItem(
    val blogName: String,
    val title: String,
    val contents: String,
    val postDate: String,
    val link: String,
)
