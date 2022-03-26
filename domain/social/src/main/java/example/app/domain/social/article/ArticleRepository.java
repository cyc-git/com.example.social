package example.app.domain.social.article;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleRepository {

    Optional<ArticleVo> findById(Long id);

    List<ArticleVo> findByIds(Set<Long> ids);
}
