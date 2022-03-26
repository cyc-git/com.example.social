package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.follow.PosterFollowRepository;
import example.app.domain.social.poster.follow.PosterFollowVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FOLLOW;

@RequiredArgsConstructor
@Repository
public class PosterFollowRepositoryImpl implements PosterFollowRepository {
    private final DSLContext ctx;

    @Override
    public List<PosterFollowVo> findByPosterId(long posterId) {
        return ctx.selectFrom(POSTER_FOLLOW)
                .where(POSTER_FOLLOW.POSTER_ID.eq(posterId))
                .fetchInto(PosterFollowVo.class);
    }

    @Override
    public List<PosterFollowVo> findByFollowedBy(long posterId) {
        return ctx.selectFrom(POSTER_FOLLOW)
                .where(POSTER_FOLLOW.FOLLOWED_BY.eq(posterId))
                .fetchInto(PosterFollowVo.class);
    }
}
