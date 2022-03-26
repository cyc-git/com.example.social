package example.app.domain.social.article;

import lombok.Data;

@Data
public class ArticleVo implements IArticleVo {
    private Long id;
    private Long posterId;
    private String content;
    private Integer favoriteCount;
    private Integer replyCount;
    private Integer starCount;
    private Long postedAt;
    private Long updatedAt;
    private Long sharingArticleId;
}
