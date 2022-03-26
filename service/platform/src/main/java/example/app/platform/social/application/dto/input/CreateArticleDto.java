package example.app.platform.social.application.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Schema
@Data
public class CreateArticleDto {
    @Schema(description = "the text content", required = true)
    @NotBlank
    private String content;
    @Schema(description = "the article id that want to share, may be null.")
    @Nullable
    private Long sharingArticleId;
}
