package example.app.domain.social.article.favorite;

import javax.validation.constraints.NotNull;

public interface IArticleFavoriteVo {

    @NotNull
    Long getArticleId();

    @NotNull
    Long getFavoritedBy();

    @NotNull
    Long getFavoritedAt();
}
