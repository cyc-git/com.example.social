package example.app.domain.social.article.favorite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleFavoriteService {
    private final ArticleFavoriteRepository articleFavoriteRepository;

    public List<IArticleFavoriteVo> findByArticleId(@NotNull Long articleId) {
        return Collections.unmodifiableList(articleFavoriteRepository.findByArticleId(articleId));
    }
}
