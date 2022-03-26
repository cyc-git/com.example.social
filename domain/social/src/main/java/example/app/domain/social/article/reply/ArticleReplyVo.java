package example.app.domain.social.article.reply;

import lombok.Data;

@Data
public class ArticleReplyVo implements IArticleReplyVo {
    private Long id;
    private Long articleId;
    private String content;
    private Long repliedBy;
    private Long repliedAt;
}
