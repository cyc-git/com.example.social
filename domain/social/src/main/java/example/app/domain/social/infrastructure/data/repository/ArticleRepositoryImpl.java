package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.ArticleRepository;
import example.app.domain.social.article.ArticleVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static example.app.domain.social.infrastructure.data.schema.Tables.*;
import static org.jooq.impl.DSL.count;

@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final DSLContext ctx;

    private static final Set<Field<?>> articleFields;

    static {
        final var fields = Arrays.stream(ARTICLE.fields()).collect(Collectors.toList());
        fields.add(count(ARTICLE_REPLY.REPLIED_BY).as("replyCount"));
        fields.add(count(ARTICLE_STAR.STARED_BY).as("starCount"));
        articleFields = Set.copyOf(fields);
    }

    @Override
    public Optional<ArticleVo> findById(Long id) {
        return Optional.ofNullable(
                ctx.select(articleFields)
                        .from(ARTICLE)
                        .leftJoin(ARTICLE_REPLY).on(ARTICLE.ID.eq(ARTICLE_REPLY.ARTICLE_ID))
                        .leftJoin(ARTICLE_STAR).on(ARTICLE.ID.eq(ARTICLE_STAR.ARTICLE_ID))
                        .where(ARTICLE.ID.eq(id))
                        .groupBy(ARTICLE.ID)
                        .fetchOneInto(ArticleVo.class)
        );
    }

    @Override
    public List<ArticleVo> findByIds(Set<Long> ids) {
        return ctx.select(articleFields)
                .from(ARTICLE)
                .leftJoin(ARTICLE_REPLY).on(ARTICLE.ID.eq(ARTICLE_REPLY.ARTICLE_ID))
                .leftJoin(ARTICLE_STAR).on(ARTICLE.ID.eq(ARTICLE_STAR.ARTICLE_ID))
                .where(ARTICLE.ID.in(ids))
                .groupBy(ARTICLE.ID)
                .fetchInto(ArticleVo.class);
    }
}
