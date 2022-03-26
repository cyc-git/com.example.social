package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.star.command.ArticleStarCommandRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static example.app.domain.social.infrastructure.data.schema.Tables.ARTICLE_STAR;

@RequiredArgsConstructor
@Repository
public class ArticleStarCommandRepositoryImpl implements ArticleStarCommandRepository {
    private final DSLContext ctx;

    @Override
    public void star(long articleId, long staredBy, long time) {
        ctx.insertInto(ARTICLE_STAR)
                .set(ARTICLE_STAR.ARTICLE_ID, articleId)
                .set(ARTICLE_STAR.STARED_BY, staredBy)
                .set(ARTICLE_STAR.STARED_AT, time)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public void unstar(long articleId, long staredBy) {
        ctx.deleteFrom(ARTICLE_STAR)
                .where(ARTICLE_STAR.ARTICLE_ID.eq(articleId))
                .and(ARTICLE_STAR.STARED_BY.eq(staredBy))
                .execute();
    }
}
