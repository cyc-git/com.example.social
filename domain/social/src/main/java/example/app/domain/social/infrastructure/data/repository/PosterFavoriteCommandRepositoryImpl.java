package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.favorite.command.PosterFavoriteCommandRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FAVORITE;

@RequiredArgsConstructor
@Repository
public class PosterFavoriteCommandRepositoryImpl implements PosterFavoriteCommandRepository {
    private final DSLContext ctx;

    @Override
    public void favorite(long posterId, long articleId, long time) {
        ctx.insertInto(POSTER_FAVORITE)
                .set(POSTER_FAVORITE.POSTER_ID, posterId)
                .set(POSTER_FAVORITE.ARTICLE_ID, articleId)
                .set(POSTER_FAVORITE.FAVORITED_AT, time)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public void unfavorite(long posterId, long articleId) {
        ctx.deleteFrom(POSTER_FAVORITE)
                .where(POSTER_FAVORITE.POSTER_ID.eq(posterId))
                .and(POSTER_FAVORITE.ARTICLE_ID.eq(articleId))
                .execute();
    }
}
