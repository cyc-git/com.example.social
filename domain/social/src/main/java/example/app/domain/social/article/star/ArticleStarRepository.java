package example.app.domain.social.article.star;

import java.util.List;

public interface ArticleStarRepository {

    List<ArticleStarVo> findByArticleId(long articleId);
}
