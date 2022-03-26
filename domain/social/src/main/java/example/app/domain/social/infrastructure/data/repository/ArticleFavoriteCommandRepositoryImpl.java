package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.favorite.command.ArticleFavoriteCommandRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static example.app.domain.social.infrastructure.data.schema.Tables.ARTICLE_FAVORITE;

@RequiredArgsConstructor
@Repository
public class ArticleFavoriteCommandRepositoryImpl implements ArticleFavoriteCommandRepository {
    private final DSLContext ctx;

    @Override
    public void favorite(long articleId, long favoritedBy, long time) {
        ctx.insertInto(ARTICLE_FAVORITE)
                .set(ARTICLE_FAVORITE.ARTICLE_ID, articleId)
                .set(ARTICLE_FAVORITE.FAVORITED_BY, favoritedBy)
                .set(ARTICLE_FAVORITE.FAVORITED_AT, time)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public void unfavorite(long articleId, long favoritedBy) {
        ctx.deleteFrom(ARTICLE_FAVORITE)
                .where(ARTICLE_FAVORITE.ARTICLE_ID.eq(articleId))
                .and(ARTICLE_FAVORITE.FAVORITED_BY.eq(favoritedBy))
                .execute();
    }
}
