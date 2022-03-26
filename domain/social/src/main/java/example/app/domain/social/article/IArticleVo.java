package example.app.domain.social.article;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface IArticleVo {

    @NotNull
    Long getId();

    @NotNull
    Long getPosterId();

    @NotBlank
    String getContent();

    @NotNull
    Integer getReplyCount();

    @NotNull
    Integer getStarCount();

    @NotNull
    Long getPostedAt();

    @NotNull
    Long getUpdatedAt();

    @Nullable
    Long getSharingArticleId();
}
