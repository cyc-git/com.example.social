package example.app.domain.social.article;

import java.util.Optional;

public interface ArticleRepository {

    Optional<ArticleVo> findById(Long id);
}
