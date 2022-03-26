package example.app.domain.social.article.favorite.command;

import example.app.common.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleFavoriteCommandService {
    private final ArticleService articleService;
    private final ArticleFavoriteCommandRepository articleFavoriteCommandRepository;
    private final Clock clock;

    public void favorite(
            @NotNull Long articleId,
            @NotNull Long favoritedBy
    ) {
        I18nAsserts.isTrue(
                articleService.findById(articleId).isPresent(),
                ArticleFavoriteCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST
        );

        articleFavoriteCommandRepository.favorite(articleId, favoritedBy, clock.millis());
    }

    public void unfavorite(
            @NotNull Long articleId,
            @NotNull Long favoritedBy
    ) {
        articleFavoriteCommandRepository.unfavorite(articleId, favoritedBy);
    }
}
