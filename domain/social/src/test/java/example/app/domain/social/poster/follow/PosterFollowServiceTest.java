package example.app.domain.social.poster.follow;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static example.app.domain.AssertionSupport.thenThrowConstraintViolation;
import static example.app.domain.social.SocialTestFixture.posterFollowVo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterFollowServiceTest {
    @SpyBean
    private PosterFollowService unit;
    @MockBean
    private PosterFollowRepository posterFollowRepository;

    private final Random random = new Random();

    @Nested
    class when_find_by_poster_id {
        private final long posterId = random.nextLong();

        @Test
        void given_no_one_exists() {
            given(posterFollowRepository.findByPosterId(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findByPosterId(posterId))
                    .isNotNull()
                    .isEmpty();
            then(posterFollowRepository).should().findByPosterId(eq(posterId));
        }

        @Test
        void given_some_exist() {
            final var follows = Stream.generate(() -> posterFollowVo(p -> p.setPosterId(posterId)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.toList());
            given(posterFollowRepository.findByPosterId(anyLong())).willReturn(follows);
            // noinspection SuspiciousMethodCalls
            BDDAssertions.then(unit.findByPosterId(posterId))
                    .isNotEmpty()
                    .hasSize(follows.size())
                    .allMatch(follows::contains);
            then(posterFollowRepository).should().findByPosterId(eq(posterId));
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.findByPosterId(null));
        }
    }

    @Nested
    class when_find_by_followed_by {
        private final long followedBy = random.nextLong();

        @Test
        void given_no_one_exists() {
            given(posterFollowRepository.findByFollowedBy(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findByFollowedBy(followedBy))
                    .isNotNull()
                    .isEmpty();
            then(posterFollowRepository).should().findByFollowedBy(eq(followedBy));
        }

        @Test
        void given_some_exist() {
            final var follows = Stream.generate(() -> posterFollowVo(p -> p.setFollowedBy(followedBy)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.toList());
            given(posterFollowRepository.findByFollowedBy(anyLong())).willReturn(follows);
            // noinspection SuspiciousMethodCalls
            BDDAssertions.then(unit.findByFollowedBy(followedBy))
                    .isNotNull()
                    .hasSize(follows.size())
                    .allMatch(follows::contains);
            then(posterFollowRepository).should().findByFollowedBy(eq(followedBy));
        }

        @Test
        void given_null_followed_by() {
            thenThrowConstraintViolation(() -> unit.findByFollowedBy(null));
        }
    }
}