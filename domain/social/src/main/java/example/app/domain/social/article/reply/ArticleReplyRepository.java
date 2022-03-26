package example.app.domain.social.article.reply;

import java.util.List;

public interface ArticleReplyRepository {

    List<ArticleReplyVo> findByArticleId(long articleId);
}
