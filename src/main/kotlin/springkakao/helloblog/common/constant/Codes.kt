package springkakao.helloblog.common.constant

class Codes {

    enum class BlogType(
        val code: String,
        val codeName: String
    ) {
        KAKAO("kakao", "카카오"),
        NAVER("naver", "네이버")
    }

    enum class SearchSortType(
        val kakaoCode: String,
        val naverCode: String
    ) {
        ACCURACY("accuracy", "sim"),
        RECENCY("recency", "date")
    }
}
