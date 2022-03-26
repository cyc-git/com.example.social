package example.app.platform.social.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.reply.ArticleReplyVo;
import example.app.domain.social.article.reply.IArticleReplyVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleReplyDto extends ArticleReplyVo {
    public ArticleReplyDto(IArticleReplyVo replyVo) {
        CachedBeanCopier.copy(replyVo, this);
    }

    @Schema(description = "id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Schema(description = "the article id which this reply replied to")
    @Override
    public Long getArticleId() {
        return super.getArticleId();
    }

    @Schema(description = "the text content")
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Schema(description = "the poster id who created this reply")
    @Override
    public Long getRepliedBy() {
        return super.getRepliedBy();
    }

    @Schema(description = "the epoch time when this reply was created")
    @Override
    public Long getRepliedAt() {
        return super.getRepliedAt();
    }
}
