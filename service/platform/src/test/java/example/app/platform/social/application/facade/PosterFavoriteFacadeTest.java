package example.app.platform.social.application.facade;

import example.app.domain.social.poster.favorite.IPosterFavoriteVo;
import example.app.domain.social.poster.favorite.PosterFavoriteService;
import example.app.domain.social.poster.favorite.command.PosterFavoriteCommandService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static example.app.platform.social.SocialTestFixture.articleDto;
import static example.app.platform.social.SocialTestFixture.posterFavoriteVo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Disabled
@SpringJUnitConfig
class PosterFavoriteFacadeTest {
    @SpyBean
    private PosterFavoriteFacade unit;
    @MockBean
    private PosterFavoriteService posterFavoriteService;
    @MockBean
    private PosterFavoriteCommandService posterFavoriteCommandService;
    @MockBean
    private ArticleFacade articleFacade;

    private final Random random = new Random();

    private final long posterId = random.nextLong();
    private final long articleId = random.nextLong();

    @Nested
    class when_find_favorite_articles {
        @Test
        void given_non_empty_favorites() {
            final var favorites = Stream.generate(() -> posterFavoriteVo(f -> f.setPosterId(posterId)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.<IPosterFavoriteVo>toList());
            final var articleIds = favorites.stream().map(IPosterFavoriteVo::getArticleId).collect(Collectors.toSet());
            final var articles = articleIds.stream()
                    .map(id -> articleDto(d -> d.setId(id)))
                    .collect(Collectors.toList());
            given(posterFavoriteService.findByPosterId(anyLong())).willReturn(favorites);
            given(articleFacade.findByIds(anySet())).willReturn(articles);
            BDDAssertions.then(unit.findFavoriteArticles(posterId))
                    .isNotNull()
                    .isEqualTo(articles);
            then(posterFavoriteService).should().findByPosterId(eq(posterId));
            then(articleFacade).should().findByIds(eq(articleIds));
        }

        @Test
        void given_empty_favorite() {
            given(posterFavoriteService.findByPosterId(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findFavoriteArticles(posterId))
                    .isNotNull()
                    .isEmpty();
            then(posterFavoriteService).should().findByPosterId(eq(posterId));
            then(articleFacade).shouldHaveNoInteractions();
        }
    }

    @Nested
    class when_favorite {
        @Test
        void then_favorite() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.favorite(posterId, articleId));
            then(posterFavoriteCommandService).should().favorite(eq(posterId), eq(articleId));
        }
    }

    @Nested
    class when_unfavorite {
        @Test
        void then_unfavorite() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.unfavorite(posterId, articleId));
            then(posterFavoriteCommandService).should().unfavorite(eq(posterId), eq(articleId));
        }
    }
}