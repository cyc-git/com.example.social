package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.poster.command.CreatePosterVo;
import example.app.domain.social.poster.command.PosterCommandRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static example.app.domain.social.infrastructure.data.schema.Tables.POSTER;

@RequiredArgsConstructor
@Repository
public class PosterCommandRepositoryImpl implements PosterCommandRepository {
    private final DSLContext ctx;

    @Override
    public void create(CreatePosterVo createPosterVo) {
        ctx.insertInto(POSTER)
                .set(POSTER.ID, createPosterVo.getId())
                .set(POSTER.NAME, createPosterVo.getName())
                .set(POSTER.ACCOUNT, createPosterVo.getAccount())
                .execute();
    }

    @Override
    public boolean isIdOrAccountUsed(long id, String account) {
        return ctx.fetchCount(
                ctx.selectFrom(POSTER)
                        .where(POSTER.ID.eq(id))
                        .or(POSTER.ACCOUNT.eq(account))
        ) != 0;
    }
}
