package example.app.domain.social.poster.favorite;

import lombok.Data;

@Data
public class PosterFavoriteVo implements IPosterFavoriteVo {
    private Long posterId;
    private Long articleId;
    private Long favoritedAt;
}
