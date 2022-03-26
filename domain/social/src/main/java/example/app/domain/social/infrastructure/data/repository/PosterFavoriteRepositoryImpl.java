package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.favorite.IPosterFavoriteVo;
import example.app.domain.social.poster.favorite.PosterFavoriteRepository;
import example.app.domain.social.poster.favorite.PosterFavoriteVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FAVORITE;

@RequiredArgsConstructor
@Repository
public class PosterFavoriteRepositoryImpl implements PosterFavoriteRepository {
    private final DSLContext ctx;

    @Override
    public List<PosterFavoriteVo> findByPosterId(long posterId) {
        return ctx.selectFrom(POSTER_FAVORITE)
                .where(POSTER_FAVORITE.POSTER_ID.eq(posterId))
                .fetchInto(PosterFavoriteVo.class);
    }
}
