package example.app.platform.social.application.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ArticleAggregateDto {
    private ArticleDto article;
    private ArticleDto sharingArticle;
    private List<PosterDto> posters;
}
