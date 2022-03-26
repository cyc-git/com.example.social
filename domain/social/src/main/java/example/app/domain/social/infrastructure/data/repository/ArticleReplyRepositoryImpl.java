package example.app.domain.social.infrastructure.data.repository;

import example.app.domain.social.article.reply.ArticleReplyRepository;
import example.app.domain.social.article.reply.ArticleReplyVo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static example.app.domain.social.infrastructure.data.schema.Tables.ARTICLE_REPLY;

@RequiredArgsConstructor
@Repository
public class ArticleReplyRepositoryImpl implements ArticleReplyRepository {
    private final DSLContext ctx;

    @Override
    public List<ArticleReplyVo> findByArticleId(long articleId) {
        return ctx.selectFrom(ARTICLE_REPLY)
                .where(ARTICLE_REPLY.ARTICLE_ID.eq(articleId))
                .fetchInto(ArticleReplyVo.class);
    }
}
