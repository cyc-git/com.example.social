package example.app.domain.social.poster.follow.command;

import example.app.core.i18n.exception.I18nIllegalArgumentException;
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
import static example.app.domain.social.SocialTestFixture.posterVo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterFollowCommandServiceTest {
    @SpyBean
    private PosterFollowCommandService unit;
    @MockBean
    private PosterService posterService;
    @MockBean
    private PosterFollowCommandRepository posterFollowCommandRepository;
    @MockBean
    private Clock clock;

    private final Random random = new Random();

    private final long posterId = random.nextLong();
    private final long followedBy = random.nextLong();

    @Nested
    class when_follow {
        @Disabled
        @Test
        void then_follow() {
            final var time = random.nextLong();
            given(posterService.findById(anyLong()))
                    .willReturn(Optional.of(posterVo(p -> p.setId(posterId))))
                    .willReturn(Optional.of(posterVo(p -> p.setId(followedBy))));
            given(clock.millis()).willReturn(time);
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.follow(posterId, followedBy));
            then(posterService).should().findById(eq(posterId));
            then(posterService).should().findById(eq(followedBy));
            then(clock).should().millis();
            then(posterFollowCommandRepository).should().follow(eq(posterId), eq(followedBy), eq(time));
        }

        @Disabled
        @Test
        void given_same_poster_id_and_followed_by() {
            final var id = random.nextLong();
            BDDAssertions.thenThrownBy(() -> unit.follow(id, id))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFollowCommandI18nKey.SELF_FOLLOW);
            then(posterService).shouldHaveNoInteractions();
            then(clock).shouldHaveNoInteractions();
            then(posterFollowCommandRepository).shouldHaveNoInteractions();
        }

        @Disabled
        @Test
        void given_poster_id_not_exists() {
            given(posterService.findById(anyLong())).willReturn(Optional.empty());
            BDDAssertions.thenThrownBy(() -> unit.follow(posterId, followedBy))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFollowCommandI18nKey.RELATED_POSTER_DOES_NOT_EXIST);
            then(posterService).should().findById(eq(posterId));
            then(posterService).shouldHaveNoMoreInteractions();
            then(clock).shouldHaveNoInteractions();
            then(posterFollowCommandRepository).shouldHaveNoInteractions();
        }

        @Disabled
        @Test
        void given_followed_by_not_exists() {
            given(posterService.findById(anyLong()))
                    .willReturn(Optional.of(posterVo(p -> p.setId(posterId))))
                    .willReturn(Optional.empty());
            BDDAssertions.thenThrownBy(() -> unit.follow(posterId, followedBy))
                    .isInstanceOf(I18nIllegalArgumentException.class)
                    .hasMessage(PosterFollowCommandI18nKey.RELATED_FOLLOWER_DOES_NOT_EXIST);
            then(posterService).should().findById(eq(posterId));
            then(posterService).should().findById(eq(followedBy));
            then(clock).shouldHaveNoInteractions();
            then(posterFollowCommandRepository).shouldHaveNoInteractions();
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.follow(null, followedBy));
        }

        @Test
        void given_null_followed_by() {
            thenThrowConstraintViolation(() -> unit.follow(posterId, null));
        }
    }

    @Nested
    class when_unfollow {
        @Test
        void then_unfollow() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.unfollow(posterId, followedBy));
            then(posterFollowCommandRepository).should().unfollow(eq(posterId), eq(followedBy));
        }

        @Test
        void given_same_poster_id_and_followed_by() {
            final var id = random.nextLong();
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.unfollow(id, id));
            then(posterFollowCommandRepository).shouldHaveNoInteractions();
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.unfollow(null, followedBy));
        }

        @Test
        void given_null_followed_by() {
            thenThrowConstraintViolation(() -> unit.unfollow(posterId, null));
        }
    }
}