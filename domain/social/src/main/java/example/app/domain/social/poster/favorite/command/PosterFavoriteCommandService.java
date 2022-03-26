package example.app.domain.social.poster.favorite.command;

import example.app.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.poster.PosterCacheName;
import example.app.domain.social.poster.PosterService;
import example.app.domain.social.poster.favorite.PosterFavoriteCacheName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.util.Objects;

@RequiredArgsConstructor
@Validated
@Service
public class PosterFavoriteCommandService {
    private final PosterFavoriteCommandRepository posterFavoriteCommandRepository;
    private final PosterService posterService;
    private final ArticleService articleService;
    private final Clock clock;

    @Caching(
            evict = {
                    @CacheEvict(
                            cacheNames = PosterFavoriteCacheName.MULTIPLE_BY_POSTER_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#posterId"
                    )
            }
    )
    public void favorite(
            @NotNull Long posterId,
            @NotNull Long articleId
    ) {
        I18nAsserts.isTrue(
                posterService.findById(posterId).isPresent(),
                PosterFavoriteCommandI18nKey.RELATED_POSTER_DOES_NOT_EXIST
        );
        final var article = articleService.findById(articleId).orElse(null);
        I18nAsserts.notNull(
                article,
                PosterFavoriteCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST
        );
        I18nAsserts.isFalse(
                Objects.equals(posterId, article.getPosterId()),
                PosterFavoriteCommandI18nKey.SELF_FAVORITE
        );

        posterFavoriteCommandRepository.favorite(posterId, articleId, clock.millis());
    }

    @Caching(
            evict = {
                    @CacheEvict(
                            cacheNames = PosterFavoriteCacheName.MULTIPLE_BY_POSTER_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#posterId"
                    )
            }
    )
    public void unfavorite(
            @NotNull Long posterId,
            @NotNull Long articleId
    ) {
        posterFavoriteCommandRepository.unfavorite(posterId, articleId);
    }
}
