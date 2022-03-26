package example.app.domain.social.poster;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PosterRepository {

    Optional<PosterVo> findByAccount(String account);

    Optional<PosterVo> findById(long id);

    List<PosterVo> findByIds(Set<Long> ids);
}
