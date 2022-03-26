package example.app.domain.social.article.favorite;

import java.util.List;

public interface ArticleFavoriteRepository {

    List<ArticleFavoriteVo> findByArticleId(long articleId);
}
