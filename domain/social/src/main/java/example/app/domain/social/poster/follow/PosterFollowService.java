package example.app.domain.social.poster.follow;

import example.app.domain.social.poster.IPosterVo;
import example.app.domain.social.poster.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Validated
@Service
public class PosterFollowService {
    private final PosterFollowRepository posterFollowRepository;
    private final PosterService posterService;

    public List<IPosterFollowVo> findByPosterId(@NotNull Long posterId) {
        return Collections.unmodifiableList(posterFollowRepository.findByPosterId(posterId));
    }

    public List<IPosterFollowVo> findByFollowedBy(@NotNull Long posterId) {
        return Collections.unmodifiableList(posterFollowRepository.findByFollowedBy(posterId));
    }

    public List<IPosterVo> findFollowerByPosterId(@NotNull Long posterId) {
        final var followerIds = this.findByPosterId(posterId)
                .stream()
                .map(IPosterFollowVo::getFollowedBy)
                .collect(Collectors.toSet());

        if (followerIds.isEmpty()) {
            return List.of();
        }

        return posterService.findByIds(followerIds);
    }

    public List<IPosterVo> findFollowedByPosterId(@NotNull Long posterId) {
        final var followedIds = this.findByFollowedBy(posterId)
                .stream()
                .map(IPosterFollowVo::getPosterId)
                .collect(Collectors.toSet());

        if (followedIds.isEmpty()) {
            return List.of();
        }

        return posterService.findByIds(followedIds);
    }
}
