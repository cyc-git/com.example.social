package example.app.platform.social.application.facade;

import example.app.domain.social.poster.follow.IPosterFollowVo;
import example.app.domain.social.poster.follow.PosterFollowService;
import example.app.domain.social.poster.follow.command.PosterFollowCommandService;
import example.app.platform.social.application.dto.output.PosterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PosterFollowFacade {
    private final PosterFollowService posterFollowService;
    private final PosterFollowCommandService posterFollowCommandService;
    private final PosterFacade posterFacade;

    public List<PosterDto> findFollower(@NotNull Long posterId) {
        final var followerIds = posterFollowService.findByPosterId(posterId)
                .stream()
                .map(IPosterFollowVo::getFollowedBy)
                .collect(Collectors.toSet());

        if (followerIds.isEmpty()) {
            return List.of();
        }

        return posterFacade.findByIds(followerIds);
    }

    public List<PosterDto> findFollowed(@NotNull Long posterId) {
        final var followedIds = posterFollowService.findByFollowedBy(posterId)
                .stream()
                .map(IPosterFollowVo::getPosterId)
                .collect(Collectors.toSet());

        if (followedIds.isEmpty()) {
            return List.of();
        }

        return posterFacade.findByIds(followedIds);
    }

    public void follow(Long posterId, Long followedBy) {
        posterFollowCommandService.follow(posterId, followedBy);
    }

    public void unfollow(Long posterId, Long followedBy) {
        posterFollowCommandService.unfollow(posterId, followedBy);
    }
}
