package example.app.platform.social.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.reply.ArticleReplyVo;
import example.app.domain.social.article.reply.IArticleReplyVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleReplyDto extends ArticleReplyVo {
    public ArticleReplyDto(IArticleReplyVo replyVo) {
        CachedBeanCopier.copy(replyVo, this);
    }
}
