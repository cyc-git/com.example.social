package example.app.domain.social.article.star;

import javax.validation.constraints.NotNull;

public interface IArticleStarVo {

    @NotNull
    Long getArticleId();

    @NotNull
    Long getStaredBy();

    @NotNull
    Long getStaredAt();
}
