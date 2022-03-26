package example.app.platform.social.application.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ArticleAggregateDto {
    @Schema(description = "the article")
    private ArticleDto article;
    @Schema(description = "the sharing article")
    private ArticleDto sharingArticle;
    @Schema(description = "the posters of the article and the shared article")
    private List<PosterDto> posters;
}
