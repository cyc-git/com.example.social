package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.star.ArticleStarRepository;
import example.app.domain.social.article.star.ArticleStarVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static example.app.domain.social.infrastructure.data.schema.Tables.ARTICLE_STAR;

@RequiredArgsConstructor
@Repository
public class ArticleStarRepositoryImpl implements ArticleStarRepository {
    private final DSLContext ctx;

    @Override
    public List<ArticleStarVo> findByArticleId(long articleId) {
        return ctx.selectFrom(ARTICLE_STAR)
                .where(ARTICLE_STAR.ARTICLE_ID.eq(articleId))
                .fetchInto(ArticleStarVo.class);
    }
}
