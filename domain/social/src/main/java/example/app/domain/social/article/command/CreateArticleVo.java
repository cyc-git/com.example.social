package example.app.domain.social.article.command;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateArticleVo {
    @NotNull
    private Long posterId;
    @NotBlank
    private String content;
    @Nullable
    private Long sharingArticleId;
}
