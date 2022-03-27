package example.app.domain.social.poster.favorite.command;

import example.app.core.i18n.exception.I18nIllegalArgumentException;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.poster.PosterService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Clock;
import java.util.Optional;
import java.util.Random;

import static example.app.domain.AssertionSupport.thenThrowConstraintViolation;
import static example.app.domain.social.SocialTestFixture.articleVo;
import static example.app.domain.social.SocialTestFixture.posterVo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterFavoriteCommandServiceTest {
    @SpyBean
    private PosterFavoriteCommandService unit;
    @MockBean
    private PosterFavoriteCommandRepository posterFavoriteCommandRepository;
    @MockBean
    private PosterService posterService;
    @MockBean
    private ArticleService articleService;
    @MockBean
    private Clock clock;

    private final Random random = new Random();

    private final long posterId = random.nextLong();
    private final long articleId = random.nextLong();

    @Nested
    class when_favorite {

        @Disabled
        @Test
        void then_favorite() {
            final var time = random.nextLong();
            given(posterService.findById(anyLong())).willReturn(Optional.of(posterVo(p -> p.setId(posterId))));
            given(articleService.findById(anyLong())).willReturn(Optional.of(articleVo(a -> a.setId(articleId))));
            given(clock.millis()).willReturn(time);
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.favorite(posterId, articleId));
            then(posterService).should().findById(eq(posterId));
            then(articleService).should().findById(eq(articleId));
            then(clock).should().millis();
            then(posterFavoriteCommandRepository).should().favorite(eq(posterId), eq(articleId), eq(time));
        }

        @Disabled
        @Test
        void given_poster_not_exists() {
            given(posterService.findById(anyLong())).willReturn(Optional.empty());
            BDDAssertions.thenThrownBy(() -> unit.favorite(posterId, articleId))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFavoriteCommandI18nKey.RELATED_POSTER_DOES_NOT_EXIST);
            then(posterService).should().findById(eq(posterId));
            then(posterFavoriteCommandRepository).shouldHaveNoInteractions();
        }

        @Disabled
        @Test
        void given_article_not_exists() {
            given(posterService.findById(anyLong())).willReturn(Optional.of(posterVo(p -> p.setId(posterId))));
            given(articleService.findById(anyLong())).willReturn(Optional.empty());
            BDDAssertions.thenThrownBy(() -> unit.favorite(posterId, articleId))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFavoriteCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST);
            then(posterService).should().findById(eq(posterId));
            then(articleService).should().findById(eq(articleId));
            then(posterFavoriteCommandRepository).shouldHaveNoInteractions();
        }

        @Disabled
        @Test
        void given_favorite_self_article() {
            given(posterService.findById(anyLong())).willReturn(Optional.of(posterVo(p -> p.setId(posterId))));
            given(articleService.findById(anyLong())).willReturn(Optional.of(articleVo(a -> {
                a.setId(articleId);
                a.setPosterId(posterId);
            })));
            BDDAssertions.thenThrownBy(() -> unit.favorite(posterId, articleId))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFavoriteCommandI18nKey.SELF_FAVORITE);
            then(posterService).should().findById(eq(posterId));
            then(articleService).should().findById(eq(articleId));
            then(posterFavoriteCommandRepository).shouldHaveNoInteractions();
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.favorite(null, articleId));
        }

        @Test
        void given_null_article_id() {
            thenThrowConstraintViolation(() -> unit.favorite(posterId, null));
        }
    }

    @Nested
    class when_unfavorite {
        @Test
        void then_unfavorite() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.unfavorite(posterId, articleId));
            then(posterFavoriteCommandRepository).should().unfavorite(eq(posterId), eq(articleId));
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.unfavorite(null, articleId));
        }

        @Test
        void given_null_article_id() {
            thenThrowConstraintViolation(() -> unit.unfavorite(posterId, null));
        }
    }
}