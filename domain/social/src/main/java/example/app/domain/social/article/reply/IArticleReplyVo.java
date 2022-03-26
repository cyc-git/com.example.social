package example.app.domain.social.article.reply;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface IArticleReplyVo {

    @NotNull
    Long getId();

    @NotNull
    Long getArticleId();

    @NotBlank
    String getContent();

    @NotNull
    Long getRepliedBy();

    @NotNull
    Long getRepliedAt();
}
