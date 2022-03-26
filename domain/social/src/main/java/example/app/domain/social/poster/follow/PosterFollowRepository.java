package example.app.domain.social.poster.follow;

import java.util.List;

public interface PosterFollowRepository {

    List<PosterFollowVo> findByPosterId(long posterId);

    List<PosterFollowVo> findByFollowedBy(long posterId);
}
