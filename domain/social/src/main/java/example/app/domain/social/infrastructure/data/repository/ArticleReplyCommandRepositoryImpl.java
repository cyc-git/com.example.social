package example.app.domain.social.infrastructure.data.repository;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.reply.command.ArticleReplyCommandRepository;
import example.app.domain.social.article.reply.ArticleReplyVo;
import example.app.domain.social.article.reply.command.CreateArticleReplyVo;
import example.app.domain.social.infrastructure.data.schema.tables.daos.ArticleReplyDao;
import example.app.domain.social.infrastructure.data.schema.tables.pojos.ArticleReply;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Repository
public class ArticleReplyCommandRepositoryImpl extends ArticleReplyDao implements ArticleReplyCommandRepository {
    private final DSLContext ctx;

    @PostConstruct
    void init() {
        this.setConfiguration(ctx.configuration());
    }

    @Override
    public ArticleReplyVo reply(CreateArticleReplyVo createArticleReplyVo, long time) {
        final var reply = new ArticleReply();
        CachedBeanCopier.copy(createArticleReplyVo, reply);
        reply.setRepliedAt(time);
        this.insert(reply);
        final var vo = new ArticleReplyVo();
        CachedBeanCopier.copy(reply, vo);
        return vo;
    }
}
