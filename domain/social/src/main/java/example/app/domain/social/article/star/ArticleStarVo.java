package example.app.domain.social.article.star;

import lombok.Data;

@Data
public class ArticleStarVo implements IArticleStarVo {
    private Long articleId;
    private Long staredBy;
    private Long staredAt;
}
