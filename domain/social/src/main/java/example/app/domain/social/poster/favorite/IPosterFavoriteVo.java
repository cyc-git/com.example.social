package example.app.domain.social.poster.favorite;

import javax.validation.constraints.NotNull;

public interface IPosterFavoriteVo {

    @NotNull
    Long getPosterId();

    @NotNull
    Long getArticleId();

    @NotNull
    Long getFavoritedAt();
}
