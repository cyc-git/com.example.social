package example.app.platform.social.application.facade;

import example.app.domain.social.poster.IPosterVo;
import example.app.domain.social.poster.PosterService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static example.app.platform.social.SocialTestFixture.posterVo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Disabled
@SpringJUnitConfig
class PosterFacadeTest {
    @SpyBean
    private PosterFacade unit;
    @MockBean
    private PosterService posterService;

    private final Random random = new Random();

    @Nested
    class when_find {
        private final long id = random.nextLong();

        @Test
        void given_exists() {
            final var poster = posterVo(p -> p.setId(id));
            given(posterService.findById(anyLong())).willReturn(Optional.of(poster));
            BDDAssertions.then(unit.find(id))
                    .isNotNull()
                    .isPresent()
                    .get()
                    .usingRecursiveComparison()
                    .isEqualTo(poster);
            then(posterService).should().findById(eq(id));
        }

        @Test
        void given_not_exist() {
            given(posterService.findById(anyLong())).willReturn(Optional.empty());
            BDDAssertions.then(unit.find(id))
                    .isNotNull()
                    .isEmpty();
            then(posterService).should().findById(eq(id));
        }
    }

    @Nested
    class when_find_by_ids {
        private final Set<Long> ids = Stream.generate(random::nextLong)
                .limit(random.nextInt(10) + 3)
                .collect(Collectors.toSet());

        @Test
        void given_no_one_exists() {
            given(posterService.findByIds(anySet())).willReturn(List.of());
            BDDAssertions.then(unit.findByIds(ids))
                    .isNotNull()
                    .isEmpty();
            then(posterService).should().findByIds(eq(ids));
        }

        @Test
        void given_some_exist() {
            final var posters = ids.stream()
                    .map(id -> posterVo(p -> p.setId(id)))
                    .collect(Collectors.<IPosterVo>toList());
            given(posterService.findByIds(anySet())).willReturn(posters);
            BDDAssertions.then(unit.findByIds(ids))
                    .isNotNull()
                    .hasSize(posters.size())
                    .allMatch(d -> posters.stream().anyMatch(
                            p -> Objects.equals(d.getId(), p.getId())
                                    && Objects.equals(d.getAccount(), p.getAccount())
                                    && Objects.equals(d.getName(), p.getName())
                                    && Objects.equals(d.getFavoritedCount(), p.getFavoritedCount())
                                    && Objects.equals(d.getFollowedCount(), p.getFollowedCount())
                                    && Objects.equals(d.getFollowerCount(), p.getFollowerCount())
                    ));
            then(posterService).should().findByIds(eq(ids));
        }
    }
}