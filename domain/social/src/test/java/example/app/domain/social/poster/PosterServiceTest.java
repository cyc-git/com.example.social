package example.app.domain.social.poster;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static example.app.domain.AssertionSupport.thenThrowConstraintViolation;
import static example.app.domain.social.SocialTestFixture.posterVo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
class PosterServiceTest {
    @SpyBean
    private PosterService unit;
    @MockBean
    private PosterRepository posterRepository;

    private final Random random = new Random();

    @Nested
    class when_find {
        private final long id = random.nextLong();

        @Test
        void given_not_exists() {
            given(posterRepository.findById(anyLong())).willReturn(Optional.empty());
            BDDAssertions.then(unit.findById(id))
                    .isNotNull()
                    .matches(Optional::isEmpty);
            then(posterRepository).should().findById(eq(id));
        }

        @Test
        void given_exists() {
            final var poster = posterVo(p -> p.setId(id));
            given(posterRepository.findById(anyLong())).willReturn(Optional.of(poster));
            BDDAssertions.then(unit.findById(id))
                    .isNotNull()
                    .matches(Optional::isPresent)
                    .get()
                    .isEqualTo(poster);
            then(posterRepository).should().findById(eq(id));
        }

        @Test
        void given_null_id() {
            thenThrowConstraintViolation(() -> unit.findById(null));
        }
    }

    @Nested
    class when_find_by_ids {
        private final Set<Long> ids = Stream.generate(random::nextLong)
                .limit(random.nextInt(10) + 3)
                .collect(Collectors.toSet());

        @Test
        void given_no_one_exists() {
            given(posterRepository.findByIds(anySet())).willReturn(List.of());
            BDDAssertions.then(unit.findByIds(ids))
                    .isNotNull()
                    .isEmpty();
            then(posterRepository).should().findByIds(eq(ids));
        }

        @Test
        void given_some_exists() {
            final var posters = ids.stream()
                    .map(id -> posterVo(p -> p.setId(id)))
                    .collect(Collectors.toList());
            given(posterRepository.findByIds(anySet())).willReturn(posters);
            // noinspection SuspiciousMethodCalls
            BDDAssertions.then(unit.findByIds(ids))
                    .isNotNull()
                    .hasSize(posters.size())
                    .allMatch(posters::contains);
            then(posterRepository).should().findByIds(eq(ids));
        }

        @Test
        void given_empty_ids() {
            thenThrowConstraintViolation(() -> unit.findByIds(null));
            thenThrowConstraintViolation(() -> unit.findByIds(Set.of()));
            thenThrowConstraintViolation(() -> unit.findByIds(new HashSet<>(Arrays.asList(1L, null, 2L))));
        }
    }
}