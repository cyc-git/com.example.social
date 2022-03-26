package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.PosterRepository;
import example.app.domain.social.poster.PosterVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER;
import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FOLLOW;

@RequiredArgsConstructor
@Repository
public class PosterRepositoryImpl implements PosterRepository {
    private final DSLContext ctx;

    @Override
    public Optional<PosterVo> findByAccount(String account) {
        return Optional.ofNullable(
                        ctx.selectFrom(POSTER)
                                .where(POSTER.ACCOUNT.eq(account))
                                .fetchOneInto(PosterVo.class)
                )
                .map(this::retrieveFollowCount);
    }

    @Override
    public Optional<PosterVo> findById(long id) {
        return Optional.ofNullable(
                        ctx.selectFrom(POSTER)
                                .where(POSTER.ID.eq(id))
                                .fetchOneInto(PosterVo.class)
                )
                .map(this::retrieveFollowCount);
    }

    @Override
    public List<PosterVo> findByIds(Set<Long> ids) {
        return ctx.selectFrom(POSTER)
                .where(POSTER.ID.in(ids))
                .fetchInto(PosterVo.class)
                .stream()
                .map(this::retrieveFollowCount)
                .collect(Collectors.toList());

    }

    PosterVo retrieveFollowCount(PosterVo posterVo) {
        final var followerCount = ctx.selectCount()
                .from(POSTER_FOLLOW)
                .where(POSTER_FOLLOW.POSTER_ID.eq(posterVo.getId()))
                .fetchOne();
        final var followedCount = ctx.selectCount()
                .from(POSTER_FOLLOW)
                .where(POSTER_FOLLOW.FOLLOWED_BY.eq(posterVo.getId()))
                .fetchOne();
        posterVo.setFollowerCount(followerCount == null ? 0 : followerCount.value1());
        posterVo.setFollowedCount(followedCount == null ? 0 : followedCount.value1());
        return posterVo;
    }
}
