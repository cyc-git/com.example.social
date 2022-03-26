package example.app.domain.social.poster.favorite;

import java.util.List;

public interface PosterFavoriteRepository {

    List<PosterFavoriteVo> findByPosterId(long posterId);
}
