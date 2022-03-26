package example.app.platform.social.application.dto.input;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
public class CreateArticleDto {
    @NotBlank
    private String content;
    @Nullable
    private Long sharingArticleId;
}
