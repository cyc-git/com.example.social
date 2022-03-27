package example.app.platform.social.application.facade;

import example.app.domain.social.poster.follow.IPosterFollowVo;
import example.app.domain.social.poster.follow.PosterFollowService;
import example.app.domain.social.poster.follow.command.PosterFollowCommandService;
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

import static example.app.platform.social.SocialTestFixture.posterDto;
import static example.app.platform.social.SocialTestFixture.posterFollowVo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Disabled
@SpringJUnitConfig
class PosterFollowFacadeTest {
    @SpyBean
    private PosterFollowFacade unit;
    @MockBean
    private PosterFollowService posterFollowService;
    @MockBean
    private PosterFollowCommandService posterFollowCommandService;
    @MockBean
    private PosterFacade posterFacade;

    private final Random random = new Random();

    private final long posterId = random.nextLong();
    private final long followedBy = random.nextLong();

    @Nested
    class when_find_follower {
        @Test
        void given_no_one_exists() {
            given(posterFollowService.findByPosterId(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findFollower(posterId))
                    .isNotNull()
                    .isEmpty();
            then(posterFollowService).should().findByPosterId(eq(posterId));
            then(posterFacade).shouldHaveNoInteractions();
        }

        @Test
        void given_some_exist() {
            final var follows = Stream.generate(() -> posterFollowVo(p -> p.setPosterId(posterId)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.<IPosterFollowVo>toList());
            final var followedBys = follows.stream()
                    .map(IPosterFollowVo::getFollowedBy)
                    .collect(Collectors.toSet());
            final var followedByDtos = followedBys.stream()
                    .map(id -> posterDto(d -> d.setId(id)))
                    .collect(Collectors.toList());
            given(posterFollowService.findByPosterId(anyLong())).willReturn(follows);
            given(posterFacade.findByIds(anySet())).willReturn(followedByDtos);
            BDDAssertions.then(unit.findFollower(posterId))
                    .isNotNull()
                    .isEqualTo(followedByDtos);
            then(posterFollowService).should().findByPosterId(eq(posterId));
            then(posterFacade).should().findByIds(eq(followedBys));
        }
    }

    @Nested
    class when_find_followed {
        @Test
        void given_no_one_exists() {
            given(posterFollowService.findByFollowedBy(anyLong())).willReturn(List.of());
            BDDAssertions.then(unit.findFollowed(posterId))
                    .isNotNull()
                    .isEmpty();
            then(posterFollowService).should().findByFollowedBy(eq(posterId));
            then(posterFacade).shouldHaveNoInteractions();
        }

        @Test
        void given_some_exist() {
            final var follows = Stream.generate(() -> posterFollowVo(p -> p.setPosterId(posterId)))
                    .limit(random.nextInt(10) + 3)
                    .collect(Collectors.<IPosterFollowVo>toList());
            final var posterIds = follows.stream()
                    .map(IPosterFollowVo::getPosterId)
                    .collect(Collectors.toSet());
            final var posterDtos = posterIds.stream()
                    .map(id -> posterDto(d -> d.setId(id)))
                    .collect(Collectors.toList());
            given(posterFollowService.findByFollowedBy(anyLong())).willReturn(follows);
            given(posterFacade.findByIds(anySet())).willReturn(posterDtos);
            BDDAssertions.then(unit.findFollowed(posterId))
                    .isNotNull()
                    .isEqualTo(posterDtos);
            then(posterFollowService).should().findByFollowedBy(eq(posterId));
            then(posterFacade).should().findByIds(eq(posterIds));
        }
    }

    @Nested
    class when_follow {
        @Test
        void then_follow() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.follow(posterId, followedBy));
            then(posterFollowCommandService).should().follow(eq(posterId), eq(followedBy));
        }
    }

    @Nested
    class when_unfollow {
        @Test
        void then_unfollow() {
            BDDAssertions.thenNoException()
                    .isThrownBy(() -> unit.unfollow(posterId, followedBy));
            then(posterFollowCommandService).should().unfollow(eq(posterId), eq(followedBy));
        }
    }
}