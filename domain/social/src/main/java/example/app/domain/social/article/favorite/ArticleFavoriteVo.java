package example.app.domain.social.article.favorite;

import lombok.Data;

@Data
public class ArticleFavoriteVo implements IArticleFavoriteVo {
    private Long articleId;
    private Long favoritedBy;
    private Long favoritedAt;
}
