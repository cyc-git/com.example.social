package example.app.domain.social.infrastructure.data.repository;

import example.app.common.core.util.CachedBeanCopier;
import example.app.domain.social.article.ArticleVo;
import example.app.domain.social.article.command.ArticleCommandRepository;
import example.app.domain.social.article.command.CreateArticleVo;
import example.app.domain.social.infrastructure.data.schema.tables.daos.ArticleDao;
import example.app.domain.social.infrastructure.data.schema.tables.pojos.Article;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Repository
public class ArticleCommandRepositoryImpl extends ArticleDao implements ArticleCommandRepository {
    private final DSLContext ctx;

    @PostConstruct
    void init() {
        this.setConfiguration(ctx.configuration());
    }

    @Override
    public ArticleVo post(CreateArticleVo createArticleVo, long time) {
        final var article = new Article();
        CachedBeanCopier.copy(createArticleVo, article);
        article.setPostedAt(time);
        article.setUpdatedAt(time);
        this.insert(article);
        final var vo = new ArticleVo();
        CachedBeanCopier.copy(article, vo);
        vo.setFavoriteCount(0);
        vo.setReplyCount(0);
        vo.setStarCount(0);
        return vo;
    }
}
