package springkakao.helloblog.mvc.domain.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.Named
import springkakao.helloblog.common.constant.Codes.BlogType
import springkakao.helloblog.mvc.domain.external.KakaoSearchDocument
import springkakao.helloblog.mvc.domain.external.KakaoSearchResultVo
import springkakao.helloblog.mvc.domain.external.NaverSearchItem
import springkakao.helloblog.mvc.domain.external.NaverSearchResultVo
import springkakao.helloblog.mvc.presentation.request.BlogSearchGetVo
import springkakao.helloblog.mvc.presentation.response.BlogSearchItem
import springkakao.helloblog.mvc.presentation.response.BlogSearchVo

@Mapper
interface BlogSearchConverter {

    @Mappings(
        Mapping(source = "blogType", target = "blogType"),
        Mapping(
            source = "kakaoSearchResultVo.documents",
            target = "result",
            qualifiedByName = ["kakaoDocumentToResult"]
        ),
        Mapping(source = "blogSearchGetVo.pageNumber", target = "pageNumber"),
        Mapping(source = "blogSearchGetVo.pageSize", target = "pageSize"),
        Mapping(source = "kakaoSearchResultVo.meta.totalCount", target = "totalCount")
    )
    fun kakaoResultVoToSearchVo(
        kakaoSearchResultVo: KakaoSearchResultVo,
        blogSearchGetVo: BlogSearchGetVo,
        blogType: BlogType
    ): BlogSearchVo

    @Named("kakaoDocumentToResult")
    @Mappings(
        Mapping(source = "blogname", target = "blogName"),
        Mapping(source = "title", target = "title"),
        Mapping(source = "contents", target = "contents"),
        Mapping(source = "datetime", target = "postDate"),
        Mapping(source = "url", target = "link")
    )
    fun kakaoDocumentsToItems(kakaoSearchDocuments: KakaoSearchDocument): BlogSearchItem

    @Mappings(
        Mapping(source = "blogType", target = "blogType"),
        Mapping(source = "naverSearchResultVo.items", target = "result", qualifiedByName = ["naverItemsToResult"]),
        Mapping(source = "blogSearchGetVo.pageNumber", target = "pageNumber"),
        Mapping(source = "blogSearchGetVo.pageSize", target = "pageSize"),
        Mapping(source = "naverSearchResultVo.total", target = "totalCount"),
    )
    fun naverResultVoToSearchVo(
        naverSearchResultVo: NaverSearchResultVo,
        blogSearchGetVo: BlogSearchGetVo,
        blogType: BlogType
    ): BlogSearchVo

    @Named("naverItemsToResult")
    @Mappings(
        Mapping(source = "bloggername", target = "blogName"),
        Mapping(source = "title", target = "title"),
        Mapping(source = "description", target = "contents"),
        Mapping(source = "postdate", target = "postDate"),
        Mapping(source = "link", target = "link")
    )
    fun naverItemToResult(naverSearchItem: NaverSearchItem): BlogSearchItem
}
