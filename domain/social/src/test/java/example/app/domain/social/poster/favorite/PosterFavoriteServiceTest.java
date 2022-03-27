package example.app.domain.social.poster.favorite;

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
import static example.app.domain.social.SocialTestFixture.posterFavoriteVo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterFavoriteServiceTest {
    @SpyBean
    private PosterFavoriteService unit;
    @MockBean
    private PosterFavoriteRepository posterFavoriteRepository;

    private final Random random = new Random();

    @Nested
    class when_find_by_poster_id {
        private final long posterId = random.nextLong();

        @Test
        void given_no_one_exists() {
            given(posterFavoriteRepository.findByPosterId(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findByPosterId(posterId))
                    .isNotNull()
                    .isEmpty();
            then(posterFavoriteRepository).should().findByPosterId(eq(posterId));
        }

        @Test
        void given_some_exist() {
            final var favorites = Stream.generate(() -> posterFavoriteVo(f -> f.setPosterId(posterId)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.toList());
            given(posterFavoriteRepository.findByPosterId(anyLong())).willReturn(favorites);
            // noinspection SuspiciousMethodCalls
            BDDAssertions.then(unit.findByPosterId(posterId))
                    .isNotNull()
                    .hasSize(favorites.size())
                    .allMatch(favorites::contains);
            then(posterFavoriteRepository).should().findByPosterId(eq(posterId));
        }

        @Test
        void given_null_poster_id() {
            thenThrowConstraintViolation(() -> unit.findByPosterId(null));
        }
    }
}