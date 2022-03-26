package example.app.domain.social.article.reply.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateArticleReplyVo {
    @NotNull
    private Long articleId;
    @NotBlank
    private String content;
    @NotNull
    private Long repliedBy;
}
