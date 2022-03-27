package example.app.domain.social.poster.follow.command;

import example.app.core.i18n.I18nAsserts;
import example.app.domain.social.poster.PosterCacheName;
import example.app.domain.social.poster.PosterService;
import example.app.domain.social.poster.follow.PosterFollowCacheName;
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
public class PosterFollowCommandService {
    private final PosterService posterService;
    private final PosterFollowCommandRepository posterFollowCommandRepository;
    private final Clock clock;

    @Caching(
            evict = {
                    @CacheEvict(
                            cacheNames = PosterFollowCacheName.MULTIPLE_BY_POSTER_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterFollowCacheName.MULTIPLE_BY_FOLLOWED_BY,
                            key = "#followedBy"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#followedBy"
                    )
            }
    )
    public void follow(
            @NotNull Long posterId,
            @NotNull Long followedBy
    ) {
        I18nAsserts.isFalse(
                Objects.equals(posterId, followedBy),
                PosterFollowCommandI18nKey.SELF_FOLLOW
        );
        I18nAsserts.isTrue(
                posterService.findById(posterId).isPresent(),
                PosterFollowCommandI18nKey.RELATED_POSTER_DOES_NOT_EXIST
        );
        I18nAsserts.isTrue(
                posterService.findById(followedBy).isPresent(),
                PosterFollowCommandI18nKey.RELATED_FOLLOWER_DOES_NOT_EXIST
        );

        posterFollowCommandRepository.follow(posterId, followedBy, clock.millis());
    }

    @Caching(
            evict = {
                    @CacheEvict(
                            cacheNames = PosterFollowCacheName.MULTIPLE_BY_POSTER_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterFollowCacheName.MULTIPLE_BY_FOLLOWED_BY,
                            key = "#followedBy"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#posterId"
                    ),
                    @CacheEvict(
                            cacheNames = PosterCacheName.SINGLE_BY_ID,
                            key = "#followedBy"
                    )
            }
    )
    public void unfollow(
            @NotNull Long posterId,
            @NotNull Long followedBy
    ) {
        if (Objects.equals(posterId, followedBy)) {
            return;
        }
        posterFollowCommandRepository.unfollow(posterId, followedBy);
    }
}
