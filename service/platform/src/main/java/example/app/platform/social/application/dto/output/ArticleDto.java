package example.app.platform.social.application.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.ArticleVo;
import example.app.domain.social.article.IArticleVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends ArticleVo {
    public ArticleDto(IArticleVo articleVo) {
        CachedBeanCopier.copy(articleVo, this);
    }

    @Schema(description = "id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Schema(description = "the poster id")
    @Override
    public Long getPosterId() {
        return super.getPosterId();
    }

    @Schema(description = "the text content")
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Schema(description = "the count of replies")
    @Override
    public Integer getReplyCount() {
        return super.getReplyCount();
    }

    @Schema(description = "the count of stars")
    @Override
    public Integer getStarCount() {
        return super.getStarCount();
    }

    @Schema(description = "the epoch time when the article was posted")
    @Override
    public Long getPostedAt() {
        return super.getPostedAt();
    }

    @Schema(description = "the last epoch time when the article was updated")
    @Override
    public Long getUpdatedAt() {
        return super.getUpdatedAt();
    }

    @Schema(description = "the sharing article id, null means this article does not share an article")
    @Override
    public Long getSharingArticleId() {
        return super.getSharingArticleId();
    }
}
