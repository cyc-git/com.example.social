package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.ArticleRepository;
import example.app.domain.social.article.ArticleVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
        fields.add(count(ARTICLE_FAVORITE.FAVORITED_BY).as("favoriteCount"));
        fields.add(count(ARTICLE_REPLY.REPLIED_BY).as("replyCount"));
        fields.add(count(ARTICLE_STAR.STARED_BY).as("starCount"));
        articleFields = Set.copyOf(fields);
    }

    @Override
    public Optional<ArticleVo> findById(Long id) {
        return Optional.ofNullable(
                ctx.select(articleFields)
                        .from(ARTICLE)
                        .leftJoin(ARTICLE_FAVORITE).on(ARTICLE.ID.eq(ARTICLE_FAVORITE.ARTICLE_ID))
                        .leftJoin(ARTICLE_REPLY).on(ARTICLE.ID.eq(ARTICLE_REPLY.ARTICLE_ID))
                        .leftJoin(ARTICLE_STAR).on(ARTICLE.ID.eq(ARTICLE_STAR.ARTICLE_ID))
                        .where(ARTICLE.ID.eq(id))
                        .fetchOneInto(ArticleVo.class)
        );
    }
}
