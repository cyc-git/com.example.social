package example.app.domain.social.article.command;

import example.app.domain.social.article.ArticleVo;

public interface ArticleCommandRepository {

    ArticleVo post(CreateArticleVo createArticleVo, long time);
}
