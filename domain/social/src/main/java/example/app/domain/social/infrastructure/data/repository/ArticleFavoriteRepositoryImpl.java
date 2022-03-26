package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.favorite.ArticleFavoriteRepository;
import example.app.domain.social.article.favorite.ArticleFavoriteVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static example.app.domain.social.infrastructure.data.schema.Tables.ARTICLE_FAVORITE;

@RequiredArgsConstructor
@Repository
public class ArticleFavoriteRepositoryImpl implements ArticleFavoriteRepository {
    private final DSLContext ctx;

    @Override
    public List<ArticleFavoriteVo> findByArticleId(long articleId) {
        return ctx.selectFrom(ARTICLE_FAVORITE)
                .where(ARTICLE_FAVORITE.ARTICLE_ID.eq(articleId))
                .fetchInto(ArticleFavoriteVo.class);
    }
}
