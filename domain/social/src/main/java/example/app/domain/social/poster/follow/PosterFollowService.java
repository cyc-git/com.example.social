package example.app.domain.social.poster.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class PosterFollowService {
    private final PosterFollowRepository posterFollowRepository;

    @Cacheable(PosterFollowCacheName.MULTIPLE_BY_POSTER_ID)
    public List<IPosterFollowVo> findByPosterId(@NotNull Long posterId) {
        return Collections.unmodifiableList(posterFollowRepository.findByPosterId(posterId));
    }

    @Cacheable(PosterFollowCacheName.MULTIPLE_BY_FOLLOWED_BY)
    public List<IPosterFollowVo> findByFollowedBy(@NotNull Long posterId) {
        return Collections.unmodifiableList(posterFollowRepository.findByFollowedBy(posterId));
    }
}
