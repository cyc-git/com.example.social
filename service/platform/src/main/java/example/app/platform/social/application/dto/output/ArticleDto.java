package example.app.platform.social.application.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ArticleDto {
    private Long id;
    private PosterDto poster;
    private String content;
    private Integer favoriteCount;
    private Integer replyCount;
    private Integer starCount;
    private Long postedAt;
    private Long updatedAt;
    private ArticleDto sharingArticle;
}
