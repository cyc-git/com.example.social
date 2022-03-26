package example.app.platform.social.application.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema
@Data
public class CreateArticleReplyDto {
    @Schema(description = "the article id to reply to", required = true)
    @NotNull
    private Long articleId;
    @Schema(description = "the text content", required = true)
    @NotBlank
    private String content;
}
