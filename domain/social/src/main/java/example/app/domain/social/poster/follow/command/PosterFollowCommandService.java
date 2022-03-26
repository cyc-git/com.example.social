package example.app.domain.social.poster.follow.command;

import example.app.core.i18n.I18nAsserts;
import example.app.domain.social.poster.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class PosterFollowCommandService {
    private final PosterService posterService;
    private final PosterFollowCommandRepository posterFollowCommandRepository;
    private final Clock clock;

    public void follow(
            @NotNull Long posterId,
            @NotNull Long followedBy
    ) {
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

    public void unfollow(
            @NotNull Long posterId,
            @NotNull Long followedBy
    ) {
        posterFollowCommandRepository.unfollow(posterId, followedBy);
    }
}
