package example.app.platform.social.application.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema
@Data
public class ArticleReplyAggregateDto {
    @Schema(description = "the replies of article")
    private List<ArticleReplyDto> replies;
    @Schema(description = "the posters of the replies")
    private List<PosterDto> posters;
}
