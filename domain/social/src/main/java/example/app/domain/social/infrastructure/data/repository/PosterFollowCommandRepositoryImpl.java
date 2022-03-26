package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.follow.command.PosterFollowCommandRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FOLLOW;

@RequiredArgsConstructor
@Repository
public class PosterFollowCommandRepositoryImpl implements PosterFollowCommandRepository {
    private final DSLContext ctx;

    @Override
    public void follow(long posterId, long followedBy, long time) {
        ctx.insertInto(POSTER_FOLLOW)
                .set(POSTER_FOLLOW.POSTER_ID, posterId)
                .set(POSTER_FOLLOW.FOLLOWED_BY, followedBy)
                .set(POSTER_FOLLOW.FOLLOWED_AT, time)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public void unfollow(long posterId, long followedBy) {
        ctx.deleteFrom(POSTER_FOLLOW)
                .where(POSTER_FOLLOW.POSTER_ID.eq(posterId))
                .and(POSTER_FOLLOW.FOLLOWED_BY.eq(followedBy))
                .execute();
    }
}
