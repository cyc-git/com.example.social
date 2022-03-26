package example.app.domain.social.article.reply.command;

import example.app.domain.social.article.reply.ArticleReplyVo;

public interface ArticleReplyCommandRepository {

    ArticleReplyVo reply(CreateArticleReplyVo createArticleReplyVo, long time);
}
