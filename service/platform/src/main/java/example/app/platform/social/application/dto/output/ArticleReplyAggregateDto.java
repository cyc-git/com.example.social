package example.app.platform.social.application.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class ArticleReplyAggregateDto {
    private List<ArticleReplyDto> replies;
    private List<PosterDto> posters;
}
