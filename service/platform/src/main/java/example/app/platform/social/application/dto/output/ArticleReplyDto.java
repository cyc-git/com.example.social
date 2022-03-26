package example.app.platform.social.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.reply.IArticleReplyVo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ArticleReplyDto {
    private Long id;
    private Long articleId;
    private String content;
    private PosterDto repliedBy;
    private Long repliedAt;
}
