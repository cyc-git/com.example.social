package example.app.platform.social.application.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateArticleReplyDto {
    @NotNull
    private Long articleId;
    @NotBlank
    private String content;
}
