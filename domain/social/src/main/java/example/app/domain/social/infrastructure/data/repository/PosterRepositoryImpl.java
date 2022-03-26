package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.PosterRepository;
import example.app.domain.social.poster.PosterVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.jooq.SelectJoinStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER;
import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER_FOLLOW;

@RequiredArgsConstructor
@Repository
public class PosterRepositoryImpl implements PosterRepository {
    private final DSLContext ctx;

    @Override
    public Optional<PosterVo> findByAccount(String account) {
        return Optional.ofNullable(
                selectBuilder()
                        .where(POSTER.ACCOUNT.eq(account))
                        .fetchOneInto(PosterVo.class)
        );
    }

    @Override
    public Optional<PosterVo> findById(long id) {
        return Optional.ofNullable(
                selectBuilder()
                        .where(POSTER.ID.eq(id))
                        .fetchOneInto(PosterVo.class)
        );
    }

    @Override
    public List<PosterVo> findByIds(Set<Long> ids) {
        return selectBuilder()
                .where(POSTER.ID.in(ids))
                .fetchInto(PosterVo.class);
    }

    SelectJoinStep<Record6<Long, String, String, Long, Object, Object>> selectBuilder() {
        return ctx.select(
                        POSTER.ID,
                        POSTER.NAME,
                        POSTER.ACCOUNT,
                        POSTER.DELETED_AT,
                        ctx.selectCount()
                                .from(POSTER_FOLLOW)
                                .where(POSTER_FOLLOW.POSTER_ID.eq(POSTER.ID))
                                .asField("followerCount"),
                        ctx.selectCount()
                                .from(POSTER_FOLLOW)
                                .where(POSTER_FOLLOW.FOLLOWED_BY.eq(POSTER.ID))
                                .asField("followedCount")
                )
                .from(POSTER);
    }
}
