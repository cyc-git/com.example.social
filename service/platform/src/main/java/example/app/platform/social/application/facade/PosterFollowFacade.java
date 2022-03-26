package example.app.platform.social.application.facade;

import example.app.domain.social.poster.follow.PosterFollowService;
import example.app.domain.social.poster.follow.command.PosterFollowCommandService;
import example.app.platform.social.application.dto.output.PosterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PosterFollowFacade {
    private final PosterFollowService posterFollowService;
    private final PosterFollowCommandService posterFollowCommandService;

    public List<PosterDto> findFollower(Long id) {
        return posterFollowService.findFollowerByPosterId(id)
                .stream()
                .map(PosterDto::new)
                .collect(Collectors.toList());
    }

    public List<PosterDto> findFollowed(Long id) {
        return posterFollowService.findFollowedByPosterId(id)
                .stream()
                .map(PosterDto::new)
                .collect(Collectors.toList());
    }

    public void follow(Long posterId, Long followedBy) {
        posterFollowCommandService.follow(posterId, followedBy);
    }

    public void unfollow(Long posterId, Long followedBy) {
        posterFollowCommandService.unfollow(posterId, followedBy);
    }
}
