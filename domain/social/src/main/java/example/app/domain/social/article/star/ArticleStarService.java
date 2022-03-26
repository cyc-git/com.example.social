package example.app.domain.social.article.star;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleStarService {
    private final ArticleStarRepository articleStarRepository;

    public List<IArticleStarVo> findByArticleId(@NotNull Long articleId) {
        return Collections.unmodifiableList(articleStarRepository.findByArticleId(articleId));
    }
}
